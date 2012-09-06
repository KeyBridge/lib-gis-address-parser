package org.caulfield.geotools.address.us;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.caulfield.geotools.address.us.enumerated.AddressComponentKey;
import org.caulfield.geotools.address.us.enumerated.CityNameAlias;
import org.caulfield.geotools.address.us.enumerated.EnumeratedLookup;
import org.caulfield.geotools.address.us.regex.AddressComponentPattern;
import org.caulfield.geotools.address.us.regex.NumberAndOrdinalPattern;

/**
 * Class to output well-formed, formated address strings from a parse address
 * object.
 * <p/>
 * @TODO synonym resolutions for common city names
 * @author jesse
 */
public class AddressFormatter {

  /**
   * Convert a parsed address (map) into one a one-line address formatted as:
   * {name, num predir street type postdir, line2, city, state, zip}
   * <p/>
   * @param parsedAddr
   * @return
   */
  public static String toSingleLine(Map<AddressComponentKey, String> parsedAddr) {
    if (parsedAddr == null) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.NAME), ", ");
    appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.NUMBER), " ");
    appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.PREDIR), " ");
    appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.STREET), " ");
    if (parsedAddr.get(AddressComponentKey.STREET2) != null) {
      appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.TYPE2), " ");
      appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.POSTDIR2), " ");
      sb.append("& ");
      appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.PREDIR2), " ");
      appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.STREET2), " ");
    }
    appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.TYPE), " ");
    appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.POSTDIR), " ");
    if (StringUtils.isNotBlank(sb.toString())) {
      sb.append(", ");
    }
    appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.LINE2), ", ");
    appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.CITY), ", ");
    appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.STATE), " ");
    appendIfNotNull(sb, parsedAddr.get(AddressComponentKey.ZIP), " ");

    return sb.toString().replaceAll(" ,", ",");
  }

  /**
   * Normalize the input parsedAddr map into standardized format
   * <p/>
   * @param parsedAddr
   * @return normalized address in a map
   */
  public static Map<AddressComponentKey, String> normalizeParsedAddress(Map<AddressComponentKey, String> parsedAddr) {
    Map<AddressComponentKey, String> ret = new EnumMap<AddressComponentKey, String>(AddressComponentKey.class);
    /**
     * Developer note: Just take the digits from the number component
     */
    for (Map.Entry<AddressComponentKey, String> e : parsedAddr.entrySet()) {
      String v = StringUtils.upperCase(e.getValue());
      switch (e.getKey()) {
        case PREDIR:
          ret.put(AddressComponentKey.PREDIR, normalizeDirection(v));
          break;
        case POSTDIR:
          ret.put(AddressComponentKey.POSTDIR, normalizeDirection(v));
          break;
        case TYPE:
          ret.put(AddressComponentKey.TYPE, toProperCase(normalizeStreetType(v)));
          break;
        case PREDIR2:
          ret.put(AddressComponentKey.PREDIR2, normalizeDirection(v));
          break;
        case POSTDIR2:
          ret.put(AddressComponentKey.POSTDIR2, normalizeDirection(v));
          break;
        case TYPE2:
          ret.put(AddressComponentKey.TYPE2, toProperCase(normalizeStreetType(v)));
          break;
        case NUMBER:
          ret.put(AddressComponentKey.NUMBER, normalizeNumber(v));
          break;
        case STATE:
          ret.put(AddressComponentKey.STATE, normalizeState(v));
          break;
        case ZIP:
          ret.put(AddressComponentKey.ZIP, normalizeZip(v));
          break;
        case LINE2:
          ret.put(AddressComponentKey.LINE2, toProperCase(normalizeLine2(v)));
          break;
        case CITY:
          ret.put(AddressComponentKey.CITY, toProperCase(saintAbbrExpansion(v)));
          break;
        case STREET:
          ret.put(AddressComponentKey.STREET, toProperCase(normalizeOrdinal(saintAbbrExpansion(v))));
          break;
        case STREET2:
          ret.put(AddressComponentKey.STREET2, toProperCase(normalizeOrdinal(saintAbbrExpansion(v))));
          break;
        default:
          ret.put(e.getKey(), v);
          break;
      }
    }
    ret.put(AddressComponentKey.CITY, resolveCityAlias(ret.get(AddressComponentKey.CITY), ret.get(AddressComponentKey.STATE)));
    return ret;
  }

  //<editor-fold defaultstate="collapsed" desc="Private Formatting Methods">
  /**
   * Internal method to normalize a number entry
   * <p/>
   * @param numberString number as string
   * @return
   */
  private static String normalizeNumber(String numberString) {
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
  private static String normalizeDirection(String directionWord) {
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
  private static String normalizeStreetType(String streetType) {
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
   * @param line2
   * @return
   */
  private static String normalizeLine2(String line2) {
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
  private static String normalizeZip(String zipPlus4) {
    return StringUtils.length(zipPlus4) > 5 ? zipPlus4.substring(0, 5) : zipPlus4;
  }

  /**
   * Get the REAL city name by checking the provided city/state pair against a
   * list of known aliases.
   * <p/>
   * @param city
   * @param state
   * @return
   */
  private static String resolveCityAlias(String city, String state) {
    return CityNameAlias.getRealCityName(city, state);
  }

  //TODO: document this craziness
  /**
   * Expand a city name containing the prefix 'st' to the word 'saint'
   * <p/>
   * @param cityName
   * @return
   */
  private static String saintAbbrExpansion(String cityName) {
    String cityNameExpanded;
    if ((cityNameExpanded = EnumeratedLookup.getSAINT_CITY().get(cityName)) != null) {
      return cityNameExpanded;
    }
    return cityName;
  }

  /**
   * Convert a spelled ordinal to the numerical equivalent. e.g. first to 1st.
   * <p/>
   * @param ordinalWord
   * @return
   */
  private static String normalizeOrdinal(String ordinalWord) {
    String ordinalNumber;
    if ((ordinalNumber = EnumeratedLookup.getNUMBER_ORDINAL().get(ordinalWord)) != null) {
      return ordinalNumber;
    }
    return ordinalWord;
  }

  /**
   * Internal string builder method to append items only if they are not null.
   * <p/>
   * @param sb     the string builder (not null)
   * @param s      the string under construction (not null)
   * @param suffix the text to be appended (OK if null)
   */
  private static void appendIfNotNull(StringBuilder sb, String s, String suffix) {
    if (s != null) {
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
    //    System.out.println("debug toProper " + string);
    //    return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
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
  }
  //</editor-fold>
}
