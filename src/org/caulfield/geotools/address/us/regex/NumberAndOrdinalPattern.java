package org.caulfield.geotools.address.us.regex;

import java.util.*;
import org.apache.commons.lang.StringUtils;
import static org.caulfield.geotools.address.us.enumerated.EnumeratedLookup.*;

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

  /**
   * Address Data for United States Components
   */
  private static class UsAddressesData {

    public static String getDirectionRegex() {
      String abbrv = "N[ ]?E|S[ ]?E|S[ ]?W|N[ ]?W|N|S|E|W";
      return join("|", getDIRECTION().keySet()) + "|" + abbrv;
    }

    public static String getStateRegex() {
      return join("|", getSTATE().values(), getSTATE().keySet());
    }

    public static String getStreetDesignatorRegex() {
      return join("|", getSTREET_TYPE().values(), getSTREET_TYPE().keySet());
    }

    public static String getUnitDesignatorRegex() {
      return join("|", getADDRESS_UNIT().values(), getADDRESS_UNIT().keySet());
    }

    private static String join(String separator, Collection<String>... collections) {
      Set<String> union = new HashSet<String>();
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
      return StringUtils.join(lst, separator);
    }
  }
}