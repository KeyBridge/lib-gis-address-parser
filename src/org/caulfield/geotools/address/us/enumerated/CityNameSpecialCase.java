package org.caulfield.geotools.address.us.enumerated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CityNameSpecialCase {

  public static final Map<String, List<String>> C_MAP = new HashMap<>();

  static {
    BufferedReader r = null;
    try {
      r = new BufferedReader(new InputStreamReader(CityNameSpecialCase.class.getClassLoader().getResourceAsStream("META-INF/resources/address/exception-city.txt")));
      String line;
      /**
       * Read and parse each line into an array of alternate city names. Lines
       * are formatted thus:
       * <p/>
       * NY -> WATKINS GLEN|CENTRAL BRIDGE|HOAG CORNERS|BEMIS HEIGHTS|ONTARIO ..
       */
      while ((line = r.readLine()) != null) {
        /**
         * Strip the state from the city names.
         */
        String[] items = line.split("\\s*->\\s*");
        String state = items[0];
        /**
         * Split the city names into an array.
         */
        String[] cities = items[1].split("[|]");
        /**
         * Set the city map for the selected state.
         */
        C_MAP.put(state, Arrays.asList(cities));
      }
    } catch (Exception e) {
      throw new Error("Unable to initalize exception_city", e);
    } finally {
      if (r != null) {
        try {
          r.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
