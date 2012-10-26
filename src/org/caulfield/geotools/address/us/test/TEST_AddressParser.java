/*
 *   Copyright (C) 2012 Caulfield IP Holdings (Caulfield)
 *   and/or its affiliates.
 *   All rights reserved. Use is subject to license terms.
 *
 *   Software Code is protected by Caulfield Copyrights. Caulfield hereby
 *   reserves all rights in and to Caulfield Copyrights and no license is
 *   granted under Caulfield Copyrights in this Software License Agreement.
 *   Caulfield generally licenses Caulfield Copyrights for commercialization
 *   pursuant to the terms of either Caulfield's Standard Software Source Code
 *   License Agreement or Caulfield's Standard Product License Agreement.
 *
 *   A copy of Caulfield's either License Agreement can be obtained on request
 *   by email from: info@caufield.org.
 */
package org.caulfield.geotools.address.us.test;

import java.util.Map;
import org.caulfield.geotools.address.AddressParser;
import org.caulfield.geotools.address.us.Formatter;
import org.caulfield.geotools.address.us.Parser;
import org.caulfield.geotools.address.us.enumerated.AddressComponentKey;
import org.caulfield.wsif.entity.Address;
import org.caulfield.wsif.enumerated.Enum_Country;

/**
 * Test harness to exercise the Parser, Formatter and AddressParse classes.
 * <p/>
 * @author jesse
 */
public class TEST_AddressParser {

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

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws Exception {

    System.out.println("TEST_AddressParser main");

//    String addr1 = "123 6th street\nphiladelphia pa";
//    String addr1 = "Mr. AI, 123 Some Street, Atlanta, GA 12345";
//    String addr1 = "Some Person 123 Fake Street New York New York 12345";
//    String addr1 = "Jo Shmo 123 Fake Parkway Notacity guam";
    /**
     * The following example fails on a mis-spelled state and comma in the name
     */
//    String addr1 = "Apple Computer, Inc. 1 Infinite Loop Cupertino Caifrnia 95014";
//    String addr1 = "Apple Computer Inc. 1 Infinite Loop Cupertino California 95014"; // works
//    String addr1 = "Apple Computer Inc. 1 Infinite Loop Cupertino CA"; // works
//    String addr1 = "8000 towers    crescent drive, suite 1100,\n mclean, virginia  22102";
//    String addr1 = "8000 Towers Crescent Drive, Suite 1100, Mclean, VA 22102";
    String addr1 = "8000 Towers Crescent Drive, Suite 1100, HILTON HEAD ISLAND, SC 22102";



    TEST_AddressParser t = new TEST_AddressParser();

    Map<AddressComponentKey, String> outMap = t.getParser().parse(addr1);

    System.out.println("Address output parsed ");
    for (AddressComponentKey addressComponent : outMap.keySet()) {
      System.out.println("   " + addressComponent.toString() + " : " + outMap.get(addressComponent));
    }

    System.out.println("\nAddress output normalized");
    Map<AddressComponentKey, String> foo = t.getFormatter().normalizeParsedAddress(outMap);

    for (AddressComponentKey addressComponent : foo.keySet()) {
      System.out.println("   " + addressComponent.toString() + " : " + foo.get(addressComponent));
    }
    System.out.println("\n double line ");
    System.out.println(t.getFormatter().toFormattedAddress(foo, false));

    System.out.println("\n single line ");
    System.out.println(t.getFormatter().toFormattedAddress(foo, true));

    Address a = new Address();
    a.setAddress("8000 towers    crescent drive, suite 1100");
    a.setCity("mclean");
    a.setState("va");
    a.setPostalCode("22102");
    a.setCountryEnum(Enum_Country.UNITED_STATES_OF_AMERICA);
    a.setAddressFormatted(addr1);

    System.out.println("wsif\n" + a.getAddressFormatted());

    System.out.println("TEST Address Parser ------------------------------------");
    AddressParser ap = new AddressParser();
    Address b = ap.cleanUp(a);
    System.out.println("From AddressParser: " + b);


  }
}
