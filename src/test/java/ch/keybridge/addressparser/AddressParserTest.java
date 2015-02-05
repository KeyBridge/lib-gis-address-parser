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
package ch.keybridge.lib.addressparser;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import ch.keybridge.lib.addressparser.us.Formatter;
import ch.keybridge.lib.addressparser.us.Parser;
import ch.keybridge.lib.addressparser.us.enumerated.AddressComponentKey;

/**
 *
 * @author Jesse Caulfield <jesse@caulfield.org>
 */
public class AddressParserTest extends TestCase {

  private static String addressString = "8000 Towers Crescent Drive, Suite 1100, McLean, VA  22102";

  private Formatter formatter;
  private Parser parser;

  public Formatter getFormatter() {
    if (formatter == null) {
      formatter = new Formatter();
    }
    return formatter;
  }

  public Parser getParser() {
    if (parser == null) {
      parser = new Parser();
    }
    return parser;
  }

  public AddressParserTest(String testName) {
    super(testName);
  }

  public void testParse() {
    try {
      Map<AddressComponentKey, String> parseMap = getParser().parse(addressString);
      assertNotNull(parseMap);
      assertTrue(parseMap.size() > 0);
      System.out.println("Address output parsed ");
//      for (AddressComponentKey addressComponent : parseMap.keySet()) {        System.out.println("   " + addressComponent.toString() + " : " + parseMap.get(addressComponent));      }
      System.out.println("test parser into " + parseMap.size() + " components OK");
    } catch (Exception ex) {
      Logger.getLogger(AddressParserTest.class.getName()).log(Level.SEVERE, null, ex);
      fail(ex.getMessage());
    }
  }

  public void testNormalizer() {
    try {
      Map<AddressComponentKey, String> parseMap = getParser().parse(addressString);
      assertNotNull(parseMap);
      assertTrue(parseMap.size() > 0);

      Map<AddressComponentKey, String> normalizeMap = getFormatter().normalizeParsedAddress(parseMap);
      assertNotNull(normalizeMap);
      assertTrue(normalizeMap.size() > 0);

//      for (AddressComponentKey addressComponent : normalizeMap.keySet()) {        System.out.println("   " + addressComponent.toString() + " : " + normalizeMap.get(addressComponent));      }
      System.out.println("test normalizer into " + normalizeMap.size() + " components OK");
    } catch (Exception ex) {
      Logger.getLogger(AddressParserTest.class.getName()).log(Level.SEVERE, null, ex);
      fail(ex.getMessage());
    }
  }

  public void testFormatter() {
    try {
      Map<AddressComponentKey, String> outMap = getParser().parse(addressString);
      assertNotNull(outMap);
      assertTrue(outMap.size() > 0);

      Map<AddressComponentKey, String> normalizeMap = getFormatter().normalizeParsedAddress(outMap);
      assertNotNull(normalizeMap);
      assertTrue(normalizeMap.size() > 0);

      for (AddressComponentKey addressComponent : normalizeMap.keySet()) {
        System.out.println(" " + addressComponent.toString() + " : " + normalizeMap.get(addressComponent));
      }

      String formatted = getFormatter().toFormattedAddress(normalizeMap, false);
      assertNotNull(formatted);
      assertTrue(formatted.length() > 0);
      System.out.println("test formatter address to 2-line OK\n" + formatted);

      formatted = getFormatter().toFormattedAddress(normalizeMap, true);
      assertNotNull(formatted);
      assertTrue(formatted.length() > 0);
      System.out.println("test formatter address to 1-line OK\n" + formatted);

    } catch (Exception ex) {
      Logger.getLogger(AddressParserTest.class.getName()).log(Level.SEVERE, null, ex);
      fail(ex.getMessage());
    }
  }

}
