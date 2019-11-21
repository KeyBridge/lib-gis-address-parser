package ch.keybridge.gis.addressparser.us.regex;

import ch.keybridge.gis.addressparser.us.enumerated.EnumeratedLookup;

/**
 * Class to correct the spelling of a US state.
 *
 * @author jesse
 */
public class StateSpellingCorrector {

  /**
   * Attempts to substitute 2-character abbreviations for spelled state names
   *
   * @param rawAddress
   * @return rawAddress or spelling corrected address if a state mis-spelling is
   *         found
   */
  public static String nameToAbbreviation(String rawAddress) {
    /**
     * Convert the address to upper case so the state names will match. Then
     * iterate through the pre-configured state name permutations. If there is a
     * match then replace it with the corresponding abbreviation and return.
     */
    String rawAddressUpper = rawAddress.toUpperCase();
    for (String stateName : EnumeratedLookup.getSTATE().keySet()) {
      if (rawAddressUpper.contains(stateName)) {
        return rawAddressUpper.replace(stateName, EnumeratedLookup.getSTATE().get(stateName));
      }
    }
    return rawAddress;
  }
}
