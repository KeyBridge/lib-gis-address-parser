package ch.keybridge.lib.addressparser.us.regex;

import java.util.*;
import ch.keybridge.lib.addressparser.us.enumerated.EnumeratedLookup;
import ch.keybridge.lib.wsif.enumerated.reference.ECountry;

/**
 * Some common Regex patterns used for address parsing. These are not for
 * validating English: some common spelling mistakes are intentionally included
 * <p/>
 * @author jesse
 */
public class NumberAndOrdinalPattern {

  /**
   * Numbers
   */
  public static final String TXT_NUM_0_9 = "zero|one|two|three|four|five|six|seven|eight|nine";
  public static final String TXT_NUM_10_19 = "ten|eleven|twelve|thirteen|fourteen|fifteen|sixteen|seventeen|eighteen|nineteen";
  public static final String TXT_NUM_0_19 = "(?:" + TXT_NUM_0_9 + ")|(?:" + TXT_NUM_10_19 + ")";
  /**
   * * Ordinal Numbers
   */
  private static final String ORDINAL_0_9 = "0[-]?th|1[-]?st|2[-]?nd|3[-]?rd|[0[4-9]][-]?th|1[0-9][-]?th";
  private static final String TXT_ORDINAL_1_9 = "first|second|third|fourth|forth|fifth|sixth|seventh|eighth|ninth|nineth";
  private static final String TXT_ORDINAL_10_19 = "tenth|eleventh|twelfth|twelveth|twelvth|thirteenth|fourteenth|fifteenth|sixteenth|seventeenth|enghteenth|nineteenth";
  public static final String TXT_ORDINAL_0_19 = "zeroth|(?:" + TXT_ORDINAL_1_9 + ")|(?:" + TXT_ORDINAL_10_19 + ")";
  /**
   * Not necessary valid English grammar, but it's okay
   */
  public static final String ORDINAL_ALL = "(?:[0-9]*(?:" + ORDINAL_0_9 + "))";
  /**
   * US Address Components
   */
  public static final String STREET_DESIGNATOR = UsAddressesData.getStreetDesignatorRegex();
  public static final String US_STATES = UsAddressesData.getStateRegex();
  public static final String DIRECTIONS = UsAddressesData.getDirectionRegex();
  public static final String ADDR_UNIT = UsAddressesData.getUnitDesignatorRegex();
//  public static final String COUNTRIES = UsAddressesData.getCountryIso2Regex();

  /**
   * Address Data for United States Components
   */
  private static class UsAddressesData {

    public static String getDirectionRegex() {
      String abbrv = "N[ ]?E|S[ ]?E|S[ ]?W|N[ ]?W|N|S|E|W";
      return join("|", EnumeratedLookup.getDIRECTION().keySet()) + "|" + abbrv;
    }

    public static String getStateRegex() {
      return join("|", EnumeratedLookup.getSTATE().values(), EnumeratedLookup.getSTATE().keySet());
    }

    public static String getCountryIso2Regex() {
      /**
       * Get the list of countries as a HashMap of Key:CountryName, Value:Iso2
       * <p/>
       * @return
       */
      Map<String, String> countryMap = new HashMap<>();
      for (ECountry country : ECountry.values()) {
        countryMap.put(country.getIso2(), country.getName_en());
      }
      return join("|", countryMap.values(), countryMap.keySet());
    }

    public static String getStreetDesignatorRegex() {
      return join("|", EnumeratedLookup.getSTREET_TYPE().values(), EnumeratedLookup.getSTREET_TYPE().keySet());
    }

    public static String getUnitDesignatorRegex() {
      return join("|", EnumeratedLookup.getADDRESS_UNIT().values(), EnumeratedLookup.getADDRESS_UNIT().keySet());
    }

    /**
     * Join the collection of strings on the indicated separator.
     * <p/>
     * The SafeVarargs annotation asserts that the body of the annotated method
     * or constructor does not perform potentially unsafe operations on its
     * varargs parameter.
     * <p/>
     * @param separator   the separator string
     * @param collections the collection(s) of Strings
     * @return
     */
    @SafeVarargs
    private static String join(String separator, Collection<String>... collections) {
      Set<String> union = new HashSet<>();
      for (Collection<String> c : collections) {
        union.addAll(c);
      }
      String[] set = new String[union.size()];
      List<String> lst = Arrays.asList(union.toArray(set));
      Collections.sort(lst, new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
          return Integer.valueOf(o2.length()).compareTo(o1.length());
        }
      });
      StringBuilder sb = new StringBuilder();
      boolean first = true;
      for (String string : lst) {
        if (first) {
          sb.append(string);
          first = false;
        } else {
          sb.append(separator).append(string);
        }
      }
      return sb.toString();
    }
  }
}
