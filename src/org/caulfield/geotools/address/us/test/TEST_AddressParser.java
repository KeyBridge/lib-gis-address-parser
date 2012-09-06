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
import org.caulfield.geotools.address.us.AddressFormatter;
import org.caulfield.geotools.address.us.AddressParser;
import org.caulfield.geotools.address.us.enumerated.AddressComponentKey;

/**
 *
 * @author jesse
 */
public class TEST_AddressParser {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws Exception {
    // TODO code application logic here

    System.out.println("TEST_AddressParser main");

//    String addr1 = "123 6th street\nphiladelphia pa";
    String addr1 = "8000 towers    crescent drive, suite 1100,\n mclean, virginia  22102";
    Map<AddressComponentKey, String> m = AddressParser.parseAddress(addr1.toUpperCase());

    System.out.println("parsed");
    for (AddressComponentKey addressComponent : m.keySet()) {
      System.out.println(addressComponent.toString() + " : " + m.get(addressComponent));
    }

    System.out.println("\nnormalized");
    Map<AddressComponentKey, String> foo = AddressFormatter.normalizeParsedAddress(m);

    for (AddressComponentKey addressComponent : foo.keySet()) {
      System.out.println(addressComponent.toString() + " : " + foo.get(addressComponent));
    }
    String addr = AddressFormatter.toSingleLine(foo);
    System.out.println("\n single line ");
    System.out.println(addr);
  }
}
