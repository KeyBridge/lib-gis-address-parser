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
import org.caulfield.wsif.enumerated.Enum_State_US;

/**
 * Test harness to exercise the Parser, Formatter and AddressParse classes.
 * <p/>
 * @author jesse
 */
public class TEST_AddressParser {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws Exception {

    System.out.println("TEST_AddressParser main");

    String addr1 = "123 6th street\nphiladelphia pa";
//    String addr1 = "8000 towers    crescent drive, suite 1100,\n mclean, virginia  22102";
//    String addr1 = "8000 Towers Crescent Drive, Suite 1100, Mclean, VA 22102";

    Map<AddressComponentKey, String> m = Parser.parse(addr1);

    System.out.println("parsed");
    for (AddressComponentKey addressComponent : m.keySet()) {
      System.out.println(addressComponent.toString() + " : " + m.get(addressComponent));
    }

    System.out.println("\nnormalized");
    Map<AddressComponentKey, String> foo = Formatter.normalizeParsedAddress(m);

    for (AddressComponentKey addressComponent : foo.keySet()) {
      System.out.println(addressComponent.toString() + " : " + foo.get(addressComponent));
    }
    System.out.println("\n double line ");
    System.out.println(Formatter.toFormattedAddress(foo, false));

    System.out.println("\n single line ");
    System.out.println(Formatter.toFormattedAddress(foo, true));

    Address a = new Address();
    a.setAddress("8000 towers    crescent drive, suite 1100");
    a.setCity("mclean");
    a.setState("va");
    a.setPostalCode("22102");

    System.out.println("wsif\n" + a.getAddressFormatted());
    a.setAddressFormatted(null);
    System.out.println("wsif singleLine \n" + a.getAddressFormatted(true));

    AddressParser ap = new AddressParser();
    Address b = ap.cleanUp(a);
    System.out.println("Cleaned up " + b);
  }
}
