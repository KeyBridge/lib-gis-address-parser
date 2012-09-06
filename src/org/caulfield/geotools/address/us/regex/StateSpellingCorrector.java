package org.caulfield.geotools.address.us.regex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.caulfield.geotools.address.us.enumerated.EnumeratedLookup;

/**
 * Class to correct the spelling of a US state.
 * <p/>
 * @author jesse
 */
public class StateSpellingCorrector {

  private static final Map<Integer, Set<String>> STATE_TOKENS = new HashMap<Integer, Set<String>>();
  private static final Pattern DIGIT = Pattern.compile("^\\d+$");

  static {
    for (String s : EnumeratedLookup.getSTATE().keySet()) {
      int size = s.split("\\s+").length;
      Set<String> set = STATE_TOKENS.get(size);
      if (set == null) {
        STATE_TOKENS.put(size, new HashSet<String>());
      }
      STATE_TOKENS.get(size).add(s);
    }
  }

  /**
   * Attempts to correct possible state mis-spellings
   * <p/>
   * @param rawAddress
   * @return rawAddress or spelling corrected address if a state mis-spelling is
   *         found
   */
  public static String correctStateSpelling(String rawAddress) {
    String[] originalTokens = rawAddress.split("\\s+");
    String[] tokens = rawAddress.toUpperCase().split("\\s+");
    int end = tokens.length - 1;
    for (int i = end; i > 0; i--) {
      if (DIGIT.matcher(tokens[i]).matches()) {
        end--;
      } else {
        break; //end is the index of the last non-all-digits token
      }
    }
    if (tokens[end].length() <= 2) { //short word
      return rawAddress;  //this almost never works so just skip it
    }
    for (int i = 1; i <= 4; i++) {
      if (end >= i - 1) {
        for (String s : STATE_TOKENS.get(i)) {
          StringBuilder sb = new StringBuilder();
          int newEnd = end - i + 1;
          for (int j = 0; j < i; j++) {
            sb.append(tokens[newEnd + j]).append(" ");
          }
          float metrics = getNormalizedSimilarity(s, sb.toString().trim());
          if (metrics == 1f) {
            return rawAddress;
          } else if (metrics >= 0.75f) { //assume mis-spelling
            if (i != 1) {
              for (int j = 0; j < i - 1; j++) {
                originalTokens[newEnd + j] = "";
              }
            }
            originalTokens[end] = s;
            return StringUtils.join(originalTokens, " ");
          }
        }
      }
    }
    return rawAddress;
  }

  private static float getNormalizedSimilarity(String s, String t) {
    return 1f - StringUtils.getLevenshteinDistance(s, t) / (float) Math.max(s.length(), t.length());
  }
}