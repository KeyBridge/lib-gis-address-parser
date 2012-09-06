package org.caulfield.geotools.address.us;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.caulfield.geotools.address.us.enumerated.AddressComponent;
import org.caulfield.geotools.address.us.enumerated.CityNameException;
import org.caulfield.geotools.address.us.regex.AddressComponentPattern;
import org.caulfield.geotools.address.us.regex.NumberAndOrdinalPattern;
import org.caulfield.geotools.address.us.regex.StateSpellingCorrector;

/**
 * Class to parse a free-text address into its components.
 * <p/>
 * @author jesse
 * @author jliang
 * <p/>
 */
public class AddressParser {

  /**
   * Remove extra white space from within the address: extra spaces, etc.
   * <p/>
   * @param rawAddrString
   * @return
   */
  private static String getCleanSttring(String rawAddrString) {
    Pattern CLEANUP = Pattern.compile("^\\W+|\\W+$|[\\s\\p{Punct}&&[^\\)\\(#&,:;@'`-]]");
    return CLEANUP.matcher(rawAddrString).replaceAll(" ").replaceAll("\\s+", " ").trim();
  }

  /**
   * Parses a raw address string
   * <p/>
   * @param rawAddr
   * @param autoCorrectStateSpelling swith on/off auto correction on state
   *                                 mis-spelling
   * @return a map of parsed address components
   */
  public static Map<AddressComponent, String> parseAddress(String rawAddr, boolean autoCorrectStateSpelling) {
    rawAddr = getCleanSttring(rawAddr);
    if (autoCorrectStateSpelling) {
      rawAddr = StateSpellingCorrector.correctStateSpelling(rawAddr);
    }
    /**
     * Match the street address
     */
    Pattern STREET_ADDRESS = Pattern.compile(AddressComponentPattern.P_STREET_ADDRESS.getRegex());
    Matcher m = STREET_ADDRESS.matcher(rawAddr);
    Map<AddressComponent, String> ret = null;
    if (m.matches()) {
      ret = getAddrMap(m, AddressComponentPattern.P_STREET_ADDRESS.getNamedGroupMap());
      postProcess(ret);
      String splitRawAddr = null;
      String line12sep = ret.get(AddressComponent.TLID);//HACK!
      if (!line12sep.contains(",")
              && (splitRawAddr = designatorConfusingCitiesCorrection(ret, rawAddr)) != null) {
        m = STREET_ADDRESS.matcher(splitRawAddr);
        if (m.matches()) {
          ret = getAddrMap(m, AddressComponentPattern.P_STREET_ADDRESS.getNamedGroupMap());
          ret.remove(AddressComponent.TLID);//HACK!
          return ret;
        }
      }
      ret.remove(AddressComponent.TLID);//HACK!
    }
    /**
     * Match the corner
     */
    m = Pattern.compile(AddressComponentPattern.P_CORNER.getRegex()).matcher(rawAddr);
    if (ret == null && m.find()) {
      /**
       * Match an intersection
       */
      m = Pattern.compile(AddressComponentPattern.P_INTERSECTION.getRegex()).matcher(rawAddr);
      if (m.matches()) {
        ret = getAddrMap(m, AddressComponentPattern.P_INTERSECTION.getNamedGroupMap());
      }
    }

    if (ret == null) {
      /**
       * Match the last line
       */
      m = Pattern.compile(AddressComponentPattern.P_CSZ.getRegex()).matcher(rawAddr);
      if (m.matches()) {
        ret = getAddrMap(m, AddressComponentPattern.P_CSZ.getNamedGroupMap());
      }
    }
    return ret;
  }

  /**
   * Parses a raw address string, this delegates to
   * {@linkplain AddressParser#parseAddress(String, boolean)} with
   * autoCorrectStateSpelling set to false
   * <p/>
   * @param rawAddr
   * @return a map of parsed address components
   */
  public static Map<AddressComponent, String> parseAddress(String rawAddr) {
    return parseAddress(rawAddr, true);
  }

  private static void postProcess(Map<AddressComponent, String> m) {
    //these are (temporary?) hacks...
    if (m.get(AddressComponent.TYPE) == null && m.get(AddressComponent.STREET) != null
            && Pattern.compile(NumberAndOrdinalPattern.STREET_DESIGNATOR).matcher(m.get(AddressComponent.STREET).toUpperCase()).matches()) {
      m.put(AddressComponent.TYPE, m.get(AddressComponent.STREET));
      m.put(AddressComponent.STREET, m.get(AddressComponent.PREDIR));
      m.put(AddressComponent.PREDIR, null);
    }
    if (m.get(AddressComponent.STATE) == null && m.get(AddressComponent.LINE2) != null
            && Pattern.compile(NumberAndOrdinalPattern.US_STATES).matcher(m.get(AddressComponent.LINE2).toUpperCase()).matches()) {
      m.put(AddressComponent.STATE, m.get(AddressComponent.LINE2));
      m.put(AddressComponent.LINE2, null);
    }
  }

  private static Map<AddressComponent, String> getAddrMap(Matcher m, Map<Integer, String> groupMap) {
    Map<AddressComponent, String> ret = new EnumMap<AddressComponent, String>(AddressComponent.class);
    for (int i = 1; i <= m.groupCount(); i++) {
      String name = groupMap.get(i);
      AddressComponent comp = AddressComponent.valueOf(name);
      if (ret.get(comp) == null) {
        putIfNotNull(ret, comp, m.group(i));
      }
    }
    return ret;
  }

  private static void putIfNotNull(Map<AddressComponent, String> m, AddressComponent ac, String v) {
    if (v != null) {
      m.put(ac, v);
    }
  }

  private static String designatorConfusingCitiesCorrection(Map<AddressComponent, String> parsedLocation, String input) {
    String street = parsedLocation.get(AddressComponent.STREET);
    String type = parsedLocation.get(AddressComponent.TYPE);
    String line2 = parsedLocation.get(AddressComponent.LINE2);
    if (street == null || type == null || line2 != null || street.split(" ").length < 2) {
      return null;
    }
    /**
     * Match the street designator
     */
    Matcher m = Pattern.compile("\\b(?i:(?:" + NumberAndOrdinalPattern.STREET_DESIGNATOR + "))\\b").matcher(street);
    if (m.find()) {
      String parsedstate = parsedLocation.get(AddressComponent.STATE);
      if (parsedstate == null) {
        String parsedcity = parsedLocation.get(AddressComponent.CITY);
        if (parsedcity != null && parsedcity.length() == 2) {
          parsedstate = parsedcity;
        }
      }
      String normalizedState = AddressFormatter.normalizeState(StringUtils.upperCase(parsedstate));
      String inputUpper = input.toUpperCase();
      String ret = null;
      Set<String> stateSet = new HashSet<String>();
      if (normalizedState != null) {
        stateSet.add(normalizedState);
      } else {
        /**
         * if no state hat been found this needs to work much harder
         */
        stateSet.addAll(CityNameException.C_MAP.keySet());
      }
      int stateIdx = parsedstate == null ? input.length() : input.lastIndexOf(parsedstate);
      for (String state : stateSet) {
        for (String s : CityNameException.C_MAP.get(state)) {
          int idx = -1;
          if ((idx = inputUpper.lastIndexOf(s)) != -1) {
            /**
             * If the input has one of the city names that can confuse the
             * parser this almost guaranteed to break the parser, help the
             * parser by putting a comma separator before the city
             */
            if (idx + s.length() >= stateIdx - 2) {
              return input.substring(0, idx) + "," + input.substring(idx);
            }
          }
        }
      }
      return ret;
    }
    return null;

  }
}