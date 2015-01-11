package org.caulfield.geotools.address.us.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A REGEX pattern group factory builder. This class builds a REGEX pattern
 * group and populates it with the correct compound pattern.
 * <p/>
 * @author jesse
 */
public class RegexPatternFactory {

  /**
   * Assumes all capturing groups are named
   */
  private static final Pattern NAMED_GROUP_PATTERN = Pattern.compile("\\(\\?P<(.*?)>");

  /**
   * Compile a regex pattern into a named group pattern object.
   * <p/>
   * @param regex the regex pattern.
   * @return
   */
  public static RegexPatternGroup compile(String regex) {
    Matcher m = NAMED_GROUP_PATTERN.matcher(regex);
    Map<Integer, String> namedGroupMap = new HashMap<>();
    int i = 1;
    while (m.find()) {
      namedGroupMap.put(i, m.group(1).toUpperCase());
      i++;
    }
    return new RegexPatternGroup(m.replaceAll("("), namedGroupMap);
  }

  /**
   * Helper class to contain named REGEX patterns
   */
  public static class RegexPatternGroup {

    private final String _regex;
    private final Map<Integer, String> _namedGroupMap;

    public RegexPatternGroup(String regex, Map<Integer, String> namedGroupMap) {
      _regex = regex;
      _namedGroupMap = namedGroupMap;
    }

    public String getRegex() {
      return _regex;
    }

    public Map<Integer, String> getNamedGroupMap() {
      return _namedGroupMap;
    }
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
  public static <T> T returnNotNull(T candidate, T replacement) {
    return candidate == null ? replacement : candidate;
  }
}