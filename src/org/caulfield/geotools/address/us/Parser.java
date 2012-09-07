package org.caulfield.geotools.address.us;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.caulfield.geotools.address.us.enumerated.AddressComponentKey;
import org.caulfield.geotools.address.us.enumerated.CityNameException;
import org.caulfield.geotools.address.us.enumerated.EnumeratedLookup;
import org.caulfield.geotools.address.us.regex.AddressComponentPattern;
import org.caulfield.geotools.address.us.regex.NumberAndOrdinalPattern;
import org.caulfield.geotools.address.us.regex.StateSpellingCorrector;
import org.caulfield.wsif.entity.Address;

/**
 * Class to parse a free-text address into its components.
 * <p/>
 * @author jesse
 */
public class Parser {

//  public Map<AddressComponentKey, String> parseStreet(String rawAddr) throws Exception {
//    if (rawAddr == null || rawAddr.isEmpty()) {
//      throw new Exception("Address is empty or null");
//    }
//    rawAddr = getCleanSttring(rawAddr);
//    System.out.println("debug parseStreet " + rawAddr);
//    return null;
//  }

  /**
   * Parses a raw address string, this delegates to
   * {@linkplain Parser#parse(String, boolean)} with autoCorrectStateSpelling
   * set to false
   * <p/>
   * @param rawAddr
   * @return a map of parsed address components
   */
  public Map<AddressComponentKey, String> parse(String rawAddr) throws Exception {
    return parse(rawAddr, true);
  }

  /**
   * Parses a raw address string
   * <p/>
   * @param rawAddr
   * @param autoCorrectStateSpelling swith on/off auto correction on state
   *                                 mis-spelling
   * @return a map of parsed address components
   */
  public Map<AddressComponentKey, String> parse(String rawAddr, boolean autoCorrectStateSpelling) throws Exception {
    if (rawAddr == null || rawAddr.isEmpty()) {
      throw new Exception("Address is empty or null");
    }
    rawAddr = getCleanSttring(rawAddr);
    if (autoCorrectStateSpelling) {
      rawAddr = StateSpellingCorrector.nameToAbbreviation(rawAddr);
    }
    /**
     * Match the street address
     */
    Pattern STREET_ADDRESS = Pattern.compile(AddressComponentPattern.P_STREET_ADDRESS.getRegex());
    Matcher m = STREET_ADDRESS.matcher(rawAddr);
    Map<AddressComponentKey, String> ret = null;
    if (m.matches()) {
      ret = getAddrMap(m, AddressComponentPattern.P_STREET_ADDRESS.getNamedGroupMap());
      postProcess(ret);
      String splitRawAddr = null;
      String line12sep = ret.get(AddressComponentKey.TLID);//HACK!
      if (!line12sep.contains(",")
              && (splitRawAddr = designatorConfusingCitiesCorrection(ret, rawAddr)) != null) {
        m = STREET_ADDRESS.matcher(splitRawAddr);
        if (m.matches()) {
          ret = getAddrMap(m, AddressComponentPattern.P_STREET_ADDRESS.getNamedGroupMap());
          ret.remove(AddressComponentKey.TLID);//HACK!
          return ret;
        }
      }
      ret.remove(AddressComponentKey.TLID);//HACK!
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

  //<editor-fold defaultstate="collapsed" desc="Private Parsing Methods">
  /**
   * Remove extra white space from within the address: extra spaces, etc.
   * <p/>
   * @param rawAddrString
   * @return
   */
  private String getCleanSttring(String rawAddrString) {
    Pattern CLEANUP = Pattern.compile("^\\W+|\\W+$|[\\s\\p{Punct}&&[^\\)\\(#&,:;@'`-]]");
    return CLEANUP.matcher(rawAddrString).replaceAll(" ").replaceAll("\\s+", " ").trim();
  }

  private void postProcess(Map<AddressComponentKey, String> m) {
    //these are (temporary?) hacks...
    if (m.get(AddressComponentKey.TYPE) == null && m.get(AddressComponentKey.STREET) != null
            && Pattern.compile(NumberAndOrdinalPattern.STREET_DESIGNATOR).matcher(m.get(AddressComponentKey.STREET).toUpperCase()).matches()) {
      m.put(AddressComponentKey.TYPE, m.get(AddressComponentKey.STREET));
      m.put(AddressComponentKey.STREET, m.get(AddressComponentKey.PREDIR));
      m.put(AddressComponentKey.PREDIR, null);
    }
    if (m.get(AddressComponentKey.STATE) == null && m.get(AddressComponentKey.LINE2) != null
            && Pattern.compile(NumberAndOrdinalPattern.US_STATES).matcher(m.get(AddressComponentKey.LINE2).toUpperCase()).matches()) {
      m.put(AddressComponentKey.STATE, m.get(AddressComponentKey.LINE2));
      m.put(AddressComponentKey.LINE2, null);
    }
  }

  private Map<AddressComponentKey, String> getAddrMap(Matcher m, Map<Integer, String> groupMap) {
    Map<AddressComponentKey, String> ret = new EnumMap<AddressComponentKey, String>(AddressComponentKey.class);
    for (int i = 1; i <= m.groupCount(); i++) {
      String name = groupMap.get(i);
      AddressComponentKey comp = AddressComponentKey.valueOf(name);
      if (ret.get(comp) == null) {
        putIfNotNull(ret, comp, m.group(i));
      }
    }
    return ret;
  }

  private void putIfNotNull(Map<AddressComponentKey, String> m, AddressComponentKey ac, String v) {
    if (v != null) {
      m.put(ac, v);
    }
  }

  private String designatorConfusingCitiesCorrection(Map<AddressComponentKey, String> parsedLocation, String input) {
    String street = parsedLocation.get(AddressComponentKey.STREET);
    String type = parsedLocation.get(AddressComponentKey.TYPE);
    String line2 = parsedLocation.get(AddressComponentKey.LINE2);
    if (street == null || type == null || line2 != null || street.split(" ").length < 2) {
      return null;
    }
    /**
     * Match the street designator
     */
    Matcher m = Pattern.compile("\\b(?i:(?:" + NumberAndOrdinalPattern.STREET_DESIGNATOR + "))\\b").matcher(street);
    if (m.find()) {
      String parsedstate = parsedLocation.get(AddressComponentKey.STATE);
      if (parsedstate == null) {
        String parsedcity = parsedLocation.get(AddressComponentKey.CITY);
        if (parsedcity != null && parsedcity.length() == 2) {
          parsedstate = parsedcity;
        }
      }
      String normalizedState = Formatter.normalizeState(StringUtils.upperCase(parsedstate));
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
  //</editor-fold>
}
