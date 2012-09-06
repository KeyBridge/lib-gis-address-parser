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
package org.caulfield.geotools.address;

import java.util.Map;
import org.caulfield.geotools.address.us.Formatter;
import org.caulfield.geotools.address.us.Parser;
import org.caulfield.geotools.address.us.enumerated.AddressComponentKey;
import org.caulfield.wsif.entity.Address;
import org.caulfield.wsif.enumerated.Enum_Country;

/**
 *
 * @author jesse
 */
public class AddressParser {

  /**
   * Clean up a WSIF address by parsing the contents and re-populating the WSIF
   * address fields. Returns a new object - does not edit the passed object.
   * <p/>
   * @param address a raw, unformatted WSIF address object
   * @return a well-formed, formatted WSIF address object
   * @throws Exception if the address is null or empty
   */
  public Address cleanUp(Address address) throws Exception {
    if (address == null || !address.isUsable()) {
      throw new Exception("Address is not usable");
    }

    System.out.println("debug clean up " + address.getAddressFormatted());

    return parse(address.getAddressFormatted());
  }

  /**
   * Convert a raw address string into a well-formed WSIF address object.
   * <p/>
   * Valid only for US addresses. This method sets the country code to US.
   * <p/>
   * @param address
   * @return
   * @throws Exception if the address is null or empty
   */
  public Address parse(String address) throws Exception {
    if (address == null || address.isEmpty()) {
      throw new Exception("Address is not usable");
    }

    System.out.println("debug parse " + address);
    Map<AddressComponentKey, String> p = Parser.parse(address);
    for (AddressComponentKey addressComponent : p.keySet()) {
      System.out.println(addressComponent.toString() + " : " + p.get(addressComponent));
    }
    /**
     * Build a WSIF address based upon the normalized, parsed address
     * components.
     */
    return buildAddress(Formatter.normalizeParsedAddress(Parser.parse(address)));
  }

  /**
   * Internal method to build a well-formed WSIF address from a parsed address
   * map.
   * <p/>
   * @param parsedAddressMap
   * @return a well formed WSIF address
   */
  private Address buildAddress(Map<AddressComponentKey, String> parsedAddressMap) {
    Address a = new Address();
    a.setAddress(Formatter.toStreetAddress(parsedAddressMap, false));
    a.setCity(parsedAddressMap.get(AddressComponentKey.CITY));
    a.setState(parsedAddressMap.get(AddressComponentKey.STATE));
    a.setPostalCode(parsedAddressMap.get(AddressComponentKey.ZIP));
    a.setCountry(Enum_Country.UNITED_STATES_OF_AMERICA);
    a.setAddressFormatted(Formatter.toFormattedAddress(parsedAddressMap, true));
    return a;
  }
}
