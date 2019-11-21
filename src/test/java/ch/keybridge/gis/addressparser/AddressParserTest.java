/*
 *  Copyright (C) 2015 Caulfield IP Holdings (Caulfield) and/or its affiliates.
 *  All rights reserved. Use is subject to license terms.
 *
 *  Software Code is protected by Caulfield Copyrights. Caulfield hereby reserves
 *  all rights in and to Caulfield Copyrights and no license is granted under
 *  Caulfield Copyrights in this Software License Agreement. Caulfield generally
 *  licenses Caulfield Copyrights for commercialization pursuant to the terms of
 *  either Caulfield's Standard Software Source Code License Agreement or
 *  Caulfield's Standard Product License Agreement.
 *
 *  A copy of either License Agreement can be obtained on request by email from:
 *  info@caufield.org.
 */
package ch.keybridge.gis.addressparser;

import ch.keybridge.gis.addressparser.us.Formatter;
import ch.keybridge.gis.addressparser.us.Parser;
import ch.keybridge.gis.addressparser.us.enumerated.AddressComponentKey;
import ch.keybridge.gis.addressparser.us.enumerated.ECountry;
import ch.keybridge.lib.gis.dto.Address;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author Jesse Caulfield <jesse@caulfield.org>
 */
public class AddressParserTest extends TestCase {

  private static String addressString = "8000 Towers Crescent Drive, Suite 1100, McLean, VA  22102";

  private final Formatter formatter;
  private final Parser parser;
  private final AddressParser addressParser;

  public AddressParserTest(String testName) {
    super(testName);
    formatter = new Formatter();
    parser = new Parser();
    addressParser = new AddressParser();
  }

  public void testNoNumber() {
    try {
      Address a = Address.getInstance("Wagner Annex", "University Park", "PA", "16802", ECountry.UNITED_STATES_OF_AMERICA.getIso2());
//      String address = "Wagner Annex, University Park, PA 16802]";

      Address parsed = addressParser.parseAddress(a);
      System.out.println("  clean up no-number OK " + parsed);
    } catch (Exception ex) {
      Logger.getLogger(AddressParserTest.class.getName()).log(Level.SEVERE, null, ex);
      fail(ex.getMessage());
    }
  }

  public void testParseAddress() {
    try {
      Map<AddressComponentKey, String> parseMap = parser.parse(addressString);
      assertNotNull(parseMap);
      assertTrue(parseMap.size() > 0);
//      System.out.println("Address output parsed ");
//      for (AddressComponentKey addressComponent : parseMap.keySet()) {        System.out.println("   " + addressComponent.toString() + " : " + parseMap.get(addressComponent));      }
      System.out.println("  parser into " + parseMap.size() + " components OK");
    } catch (Exception ex) {
      Logger.getLogger(AddressParserTest.class.getName()).log(Level.SEVERE, null, ex);
      fail(ex.getMessage());
    }
  }

  public void testNormalizer() {
    try {
      Map<AddressComponentKey, String> parseMap = parser.parse(addressString);
      assertNotNull(parseMap);
      assertTrue(parseMap.size() > 0);

      Map<AddressComponentKey, String> normalizeMap = formatter.normalizeParsedAddress(parseMap);
      assertNotNull(normalizeMap);
      assertTrue(normalizeMap.size() > 0);

//      for (AddressComponentKey addressComponent : normalizeMap.keySet()) {        System.out.println("   " + addressComponent.toString() + " : " + normalizeMap.get(addressComponent));      }
      System.out.println("  normalizer into " + normalizeMap.size() + " components OK");
    } catch (Exception ex) {
      Logger.getLogger(AddressParserTest.class.getName()).log(Level.SEVERE, null, ex);
      fail(ex.getMessage());
    }
  }

  public void testFormatter() {
    try {
      Map<AddressComponentKey, String> outMap = parser.parse(addressString);
      assertNotNull(outMap);
      assertTrue(outMap.size() > 0);

      Map<AddressComponentKey, String> normalizeMap = formatter.normalizeParsedAddress(outMap);
      assertNotNull(normalizeMap);
      assertTrue(normalizeMap.size() > 0);

      for (AddressComponentKey addressComponent : normalizeMap.keySet()) {
        System.out.println("    " + addressComponent.toString() + " : " + normalizeMap.get(addressComponent));
      }

      String formatted = formatter.toFormattedAddress(normalizeMap, false);
      assertNotNull(formatted);
      assertTrue(formatted.length() > 0);
      System.out.println("  formatter address to 2-line OK\n" + formatted);

      formatted = formatter.toFormattedAddress(normalizeMap, true);
      assertNotNull(formatted);
      assertTrue(formatted.length() > 0);
      System.out.println("  formatter address to 1-line OK\n" + formatted);

    } catch (Exception ex) {
      Logger.getLogger(AddressParserTest.class.getName()).log(Level.SEVERE, null, ex);
      fail(ex.getMessage());
    }
  }

}
