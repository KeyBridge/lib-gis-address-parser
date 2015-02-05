package ch.keybridge.lib.addressparser.us;

import ch.keybridge.lib.addressparser.us.enumerated.AddressComponentKey;
import ch.keybridge.lib.addressparser.us.enumerated.EnumeratedLookup;
import ch.keybridge.lib.addressparser.us.regex.AddressComponentPattern;
import ch.keybridge.lib.addressparser.us.regex.NumberAndOrdinalPattern;
import ch.keybridge.lib.addressparser.us.regex.RegexPatternFactory;
import ch.keybridge.lib.addressparser.us.Formatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to output well-formed, formated address strings from a parse address
 * object.
 * <p/>
 * @TODO synonym resolutions for common city names
 * @author jesse
 */
public class Formatter {

  private static final Map<String, Map<String, String>> CITY_ALIAS_MAP = new HashMap<>();

  public Formatter() {
    init();
  }

  private void init() {
    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new InputStreamReader(Formatter.class.getClassLoader().getResourceAsStream("META-INF/address/city-alias.txt")));
      String line;
      Map<String, Set<String>> allRealCitiesMap = new HashMap<>();
      while ((line = bufferedReader.readLine()) != null) {
        String[] items = line.split("\\s*=\\s*");
        String[] cs = items[0].split("<b>")[1].split("\\s*,\\s*");
        String city = cs[0], state = cs[1];
        String[] alias = items[1].split("[|]");

        Map<String, String> aliasMap = CITY_ALIAS_MAP.get(state);
        if (aliasMap == null) {
          aliasMap = new HashMap<>();
          CITY_ALIAS_MAP.put(state, aliasMap);
        }

        for (String a : alias) {
          String aa = a.split("\\s*,\\s*")[0];
          String realCity = city.intern();

          Set<String> allRealCities = allRealCitiesMap.get(state);
          if (allRealCities == null) {
            allRealCities = new HashSet<>();
            allRealCitiesMap.put(state, allRealCities);
          }
          allRealCities.add(realCity);
          if (!allRealCities.contains(aa)) {
            aliasMap.put(aa.replaceAll("\\s+", "").intern(), city.intern());
          }
        }
      }
      allRealCitiesMap.clear();
    } catch (IOException e) {
      throw new Error("Unable to initalize City Alias Resolver", e);
    } finally {
      if (bufferedReader != null) {
        try {
          bufferedReader.close();
        } catch (IOException e) {
        }
      }
    }
  }

  /**
   * Convert a parsed address (map) into one a one-line address formatted
   * according to the following pattern: {name, num predir street type postdir,
   * line2, city, state, zip}
   * <p/>
   * @param parsedAddressMap a map of parsed address values
   * @param singleLine       whether to insert a new line between the line2 and
   *                         city field (see the pattern above).
   * @return a formatted address string
   */
  public String toFormattedAddress(Map<AddressComponentKey, String> parsedAddressMap, boolean singleLine) {
    if (parsedAddressMap == null) {
      return null;
    }
    /**
     * Initialize a new string builder and begin assembling the address.
     */
    StringBuilder sb = new StringBuilder();
    /**
     * If a multi-line address then include the name. Otherwise omit it.
     */
    if (!singleLine && parsedAddressMap.get(AddressComponentKey.NAME) != null) {
      appendIfNotNull(sb, toProperCase(parsedAddressMap.get(AddressComponentKey.NAME)) + "\n", "");
    }
    /**
     * Add the street address. Set the singleLine flag to false to ensure a
     * trailing comma is added to the line-2 element, if present.
     */
    sb.append(toStreetAddress(parsedAddressMap));
    /**
     * Add a new line if desired. If single line then add a space since the
     * toStreetAddress method trims its output.
     */
    sb.append(singleLine ? ", " : "\n");
    appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.CITY), ", ");
    appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.STATE), " ");
    appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.ZIP), " ");

    return sb.toString().replaceAll(" ,", ",").trim();
  }

  /**
   * Build a formatted street address from the parsedAddress map.
   * <p/>
   * @param parsedAddressMap a map of parsed address values
   * @return
   */
  public String toStreetAddress(Map<AddressComponentKey, String> parsedAddressMap) {
    if (parsedAddressMap == null) {
      return null;
    }
    /**
     * Developer note: Old version supported a boolean appendComma parameter to
     * append a comma after the line-2 element. set to false if you only want
     * the street address.
     */
    StringBuilder sb = new StringBuilder();
    appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.NUMBER), " ");
    appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.PREDIR), " ");
    appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.STREET), " ");
    /**
     * Add the second address component (apt, suite, etc.)
     */
    if (parsedAddressMap.get(AddressComponentKey.STREET2) != null) {
      appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.TYPE2), " ");
      appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.POSTDIR2), " ");
      sb.append("& ");
      appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.PREDIR2), " ");
      appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.STREET2), " ");
    }
    /**
     * Add the direction
     */
    appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.TYPE), " ");
    appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.POSTDIR), " ");
    /**
     * Insert a comma between the first address and the second address
     */
    if (!sb.toString().isEmpty() && parsedAddressMap.get(AddressComponentKey.LINE2) != null) {
      sb.append(", ");
    }
    appendIfNotNull(sb, parsedAddressMap.get(AddressComponentKey.LINE2), "");

    return sb.toString().replaceAll(" ,", ",").trim();
  }

  /**
   * Normalize the input parsedAddr map into standardized format
   * <p/>
   * @param parsedAddr
   * @return normalized address in a map
   */
  public Map<AddressComponentKey, String> normalizeParsedAddress(Map<AddressComponentKey, String> parsedAddr) {
    Map<AddressComponentKey, String> addressComponentMap = new EnumMap<>(AddressComponentKey.class);
    /**
     * Null check. If the address failed to parse then return an empty component
     * map.
     */
    if (parsedAddr == null) {
      return addressComponentMap;
    }
    /**
     * Developer note: Just take the digits from the number component
     */
    for (Map.Entry<AddressComponentKey, String> e : parsedAddr.entrySet()) {
      String v = e.getValue().toUpperCase(Locale.getDefault());
      switch (e.getKey()) {
        case PREDIR:
          addressComponentMap.put(AddressComponentKey.PREDIR, normalizeDirection(v));
          break;
        case POSTDIR:
          addressComponentMap.put(AddressComponentKey.POSTDIR, normalizeDirection(v));
          break;
        case TYPE:
          addressComponentMap.put(AddressComponentKey.TYPE, toProperCase(normalizeStreetType(v)));
          break;
        case PREDIR2:
          addressComponentMap.put(AddressComponentKey.PREDIR2, normalizeDirection(v));
          break;
        case POSTDIR2:
          addressComponentMap.put(AddressComponentKey.POSTDIR2, normalizeDirection(v));
          break;
        case TYPE2:
          addressComponentMap.put(AddressComponentKey.TYPE2, toProperCase(normalizeStreetType(v)));
          break;
        case NUMBER:
          addressComponentMap.put(AddressComponentKey.NUMBER, normalizeNumber(v));
          break;
        case STATE:
          addressComponentMap.put(AddressComponentKey.STATE, normalizeState(v));
          break;
        case ZIP:
          addressComponentMap.put(AddressComponentKey.ZIP, normalizeZip(v));
          break;
        case LINE2:
          addressComponentMap.put(AddressComponentKey.LINE2, toProperCase(normalizeLine2(v)));
          break;
        case CITY:
          addressComponentMap.put(AddressComponentKey.CITY, toProperCase(saintAbbrExpansion(v)));
          break;
        case STREET:
          addressComponentMap.put(AddressComponentKey.STREET, toProperCase(normalizeOrdinal(saintAbbrExpansion(v))));
          break;
        case STREET2:
          addressComponentMap.put(AddressComponentKey.STREET2, toProperCase(normalizeOrdinal(saintAbbrExpansion(v))));
          break;
        default:
          addressComponentMap.put(e.getKey(), v);
          break;
      }
    }
    addressComponentMap.put(AddressComponentKey.CITY,
                            resolveCityAlias(addressComponentMap.get(AddressComponentKey.CITY),
                                             addressComponentMap.get(AddressComponentKey.STATE)));
    return addressComponentMap;
  }

  //<editor-fold defaultstate="collapsed" desc="Private Formatting Methods">
  /**
   * Internal method to normalize a number entry
   * <p/>
   * @param numberString number as string
   * @return
   */
  private String normalizeNumber(String numberString) {
    if (numberString == null || numberString.isEmpty()) {
      return null;
    }
    /**
     * Match a number as text pattern
     */
    Matcher m = Pattern.compile("^\\W*(" + NumberAndOrdinalPattern.TXT_NUM_0_19 + ")\\W*").matcher(numberString);
    String ret = null;
    if (m.matches()) {
      ret = m.group(1);
      if (ret.contains("-") || ret.contains(" ")) {//it's a 2 part number
        String[] pair = ret.split("[ -]");
        String pre = EnumeratedLookup.getNUMBER().get(pair[0]).substring(0, 1);
        ret = pre + EnumeratedLookup.getNUMBER().get(pair[1]);
      } else {
        ret = EnumeratedLookup.getNUMBER().get(ret);
      }
    } else {
      /**
       * Match a number as digit pattern
       */
      m = Pattern.compile("(.*?\\d+)\\W*(.+)?").matcher(numberString);
      if (m.matches()) {
        ret = m.group(2) == null ? m.group(1) : m.group(1) + "-" + m.group(2);
      }
    }
    return returnNotNull(ret, numberString);
  }

  /**
   * Normalize the street direction word to its respective abbreviation.
   * <p/>
   * @param directionWord
   * @return
   */
  @SuppressWarnings("AssignmentToMethodParameter")
  private String normalizeDirection(String directionWord) {
    if (directionWord == null || directionWord.isEmpty()) {
      return null;
    }
    directionWord = directionWord.replace(" ", "");
    return directionWord.length() > 2 ? EnumeratedLookup.getDIRECTION().get(directionWord) : directionWord;
  }

  /**
   * Normalize the street type, converting street type words (road, alley,
   * circle...) to their respective abbreviation
   * <p/>
   * @param streetType
   * @return
   */
  private String normalizeStreetType(String streetType) {
    return returnNotNull(EnumeratedLookup.getSTREET_TYPE().get(streetType), streetType);
  }

  /**
   * Get a known US state.
   * <p/>
   * @param state
   * @return the 2-character state abbreviation
   */
  public static String normalizeState(String state) {
    return returnNotNull(EnumeratedLookup.getSTATE().get(state), state);
  }

  /**
   * Normalize line 2 of an address containing address unit plus unit number.
   * <p/>
   * @param line2 address line 2
   * @return normalized address line 2 containing address unit plus unit number
   */
  @SuppressWarnings("AssignmentToMethodParameter")
  private String normalizeLine2(String line2) {
    if (line2 == null || line2.isEmpty()) {
      return null;
    }
    Matcher m = Pattern.compile("\\W*(?:" + AddressComponentPattern.LINE2A_GROUPED + ")\\W*").matcher(line2);
    if (m.matches()) {
      for (Map.Entry<String, String> e : EnumeratedLookup.getADDRESS_UNIT().entrySet()) {
        if (line2.startsWith(e.getKey() + " ")) {
          line2 = line2.replaceFirst(e.getKey() + " ", e.getValue() + " ");
          break;
        }
      }
    }
    return line2;
  }

  /**
   * Normalize the zip code field to a 5-character zip code string
   * <p/>
   * @param zipPlus4
   * @return 5-character zip code
   */
  private String normalizeZip(String zipPlus4) {
    return zipPlus4.length() > 5 ? zipPlus4.substring(0, 5) : zipPlus4;
  }

  /**
   * Get the REAL city name by checking the provided city/state pair against a
   * list of known aliases.
   * <p>
   * Get the REAL city name by checking the provided city/state pair against a
   * list of known aliases.
   * <p/>
   * This method checks the city-alias data set and is only valid for US cities.
   * <p/>
   * @param cityAliasName
   * @param state
   * @return the real city if the input {@code city} is an recognized alias,
   *         otherwise returns the original input
   */
  private String resolveCityAlias(String cityAliasName, String state) {
    if (cityAliasName.isEmpty() || state.isEmpty()) {
      return cityAliasName;
    }
    return CITY_ALIAS_MAP.containsKey(state)
      ? RegexPatternFactory.returnNotNull(CITY_ALIAS_MAP.get(state).get(cityAliasName.replaceAll("\\s+", "")),
                                          cityAliasName)
      : cityAliasName;
  }

  /**
   * Expand a city name containing the prefix 'st' to the word 'saint'
   * <p/>
   * @param cityName
   * @return
   */
  private String saintAbbrExpansion(String cityName) {
    return EnumeratedLookup.getSAINT_CITY().get(cityName) != null
      ? EnumeratedLookup.getSAINT_CITY().get(cityName)
      : cityName;
  }

  /**
   * Convert a spelled ordinal to the numerical equivalent. e.g. first to 1st.
   * <p/>
   * @param ordinalWord
   * @return
   */
  private String normalizeOrdinal(String ordinalWord) {
    return EnumeratedLookup.getNUMBER_ORDINAL().get(ordinalWord) != null
      ? EnumeratedLookup.getNUMBER_ORDINAL().get(ordinalWord)
      : ordinalWord;
  }

  /**
   * Internal string builder method to append items only if they are not null
   * and not empty.
   * <p/>
   * @param sb     the string builder (not null)
   * @param s      the string under construction (not null)
   * @param suffix the text to be appended (OK if null)
   */
  private void appendIfNotNull(StringBuilder sb, String s, String suffix) {
    if (s != null && !s.isEmpty()) {
      sb.append(s).append(suffix);
    }
  }

  /**
   * Set a string to proper case - lower case with the first character
   * uppercase. Credit to
   * http://www.theeggeadventure.com/wikimedia/index.php/Java_Proper_Case
   * <p/>
   * @param string
   * @return string formatted to proper case
   */
  public static String toProperCase(String string) {
    if (string == null || string.isEmpty()) {
      return null;
    }
    Pattern p = Pattern.compile("(^|\\W)([a-z])");
    Matcher m = p.matcher(string.toLowerCase());
    StringBuffer sb = new StringBuffer(string.length());
    while (m.find()) {
      m.appendReplacement(sb, m.group(1) + m.group(2).toUpperCase());
    }
    m.appendTail(sb);
    return sb.toString();
  }

  /**
   * Method to return a non-null object instance. This method returns the first
   * object if it is not null, otherwise it returns the second object.
   * <p/>
   * @param <T>         the object type
   * @param candidate   the candidate object to be returned if it is not null
   * @param replacement the object to be returned if the first candidate is null
   * @return
   */
  private static <T> T returnNotNull(T candidate, T replacement) {
    return candidate == null ? replacement : candidate;
  }//</editor-fold>
}
