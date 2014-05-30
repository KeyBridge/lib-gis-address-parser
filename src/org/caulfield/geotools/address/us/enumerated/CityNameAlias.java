package org.caulfield.geotools.address.us.enumerated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.caulfield.geotools.address.us.regex.RegexPatternFactory;

public class CityNameAlias {

  private static final Map<String, Map<String, String>> CITY_ALIAS_MAP = new HashMap<>();

  static {
    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/resources/address/city-alias.txt")));
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
  public static String getRealCityName(String cityAliasName, String state) {
    if (StringUtils.isBlank(cityAliasName) || StringUtils.isBlank(state)) {
      return cityAliasName;
    }
    Map<String, String> aliasMap = CITY_ALIAS_MAP.get(state);
    if (aliasMap == null) {
      return cityAliasName;
    }
    String realCity = aliasMap.get(cityAliasName.replaceAll("\\s+", ""));
    return RegexPatternFactory.returnNotNull(realCity, cityAliasName);
  }
}
