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

  private Formatter formatter;
  private Parser parser;

  private Formatter getFormatter() {
    if (formatter == null) {
      formatter = new Formatter();
    }
    return formatter;
  }

  private Parser getParser() {
    if (parser == null) {
      parser = new Parser();
    }
    return parser;
  }

  /**
   * Clean up a WSIF address by parsing the contents and re-populating the WSIF
   * address fields. Returns a new object - does not edit the passed object.
   * <p/>
   * @param address a raw, unformatted WSIF address object
   * @return a well-formed, formatted WSIF address object
   * @throws Exception if the address is null or empty
   */
  public Address cleanUp(Address address) throws Exception {
    if (address == null || address.getAddressFormatted() == null || address.getAddressFormatted().isEmpty()) {
      throw new Exception("Address is not usable");
    }
    /**
     * Do not parse non-US addresses or PO BOX addresses. Instead just try to
     * clean up and reformat the address components.
     */
    if (!Enum_Country.UNITED_STATES_OF_AMERICA.equals(Enum_Country.findByIso2Code(address.getCountry()))
      || address.getAddress().toUpperCase().contains("BOX")) {
      if (address.getAddress() != null) {
        address.setAddress(Formatter.toProperCase(address.getAddress().
          toUpperCase().
          replace("PO ", "POST OFFICE ").
          replace("P.O.", "POST OFFICE").
          replace("P. O.", "POST OFFICE")).trim());
      }
      /**
       * Clean up the Address fields.
       */
      address.setCity(Formatter.toProperCase(address.getCity()));
      address.setCounty(Formatter.toProperCase(address.getCounty()));
      address.setState(address.getState() == null ? null : address.getState().toUpperCase());
      address.setPostalCode(address.getPostalCode() == null ? null : address.getPostalCode().toUpperCase());
      address.setAddressFormatted(null);
      return address;
    }
    /**
     * The address is in the United States and is not a P.O. Box.
     */
    return parse(address.getAddressFormatted());
  }

  /**
   * Convert a raw address string into a well-formed WSIF address object.
   * <p/>
   * Valid only for US addresses. This method manually sets the country code to
   * Enum_Country.UNITED_STATES_OF_AMERICA ('US').
   * <p/>
   * @param address
   * @return
   * @throws Exception if the address is null or empty
   */
  public Address parse(String address) throws Exception {
    if (address == null || address.isEmpty()) {
      throw new Exception("Address is not usable");
    }
    /**
     * Build a WSIF address based upon the normalized, parsed address
     * components.
     * <p/>
     * If all the address components cannot be parsed then discard the address.
     * This is because the parser is somewhat fragile and may incorrectly assign
     * city to street names, states to cities, etc. Requiring that all fields
     * are present ensures that the address was more likely to be correctly
     * parsed.
     */
    Address addressNew = buildAddress(getFormatter().normalizeParsedAddress(getParser().parse(address)));
    if (addressNew.getAddress() != null
      && addressNew.getCity() != null
      && addressNew.getState() != null
      && addressNew.getPostalCode() != null) {
      return addressNew;
    }
    throw new Exception("Address could not be successfully parsed");
  }

  /**
   * Internal method to build a well-formed WSIF address from a parsed address
   * map.
   * <p/>
   * @param parsedAddressMap
   * @return a non-null, well formed WSIF address
   */
  private Address buildAddress(Map<AddressComponentKey, String> parsedAddressMap) {
    Address address = new Address();
    address.setAddress(getFormatter().toStreetAddress(parsedAddressMap));
    address.setCity(parsedAddressMap.get(AddressComponentKey.CITY));
    address.setState(parsedAddressMap.get(AddressComponentKey.STATE));
    address.setPostalCode(parsedAddressMap.get(AddressComponentKey.ZIP));
    address.setCountryEnum(Enum_Country.UNITED_STATES_OF_AMERICA);
    return address;
  }
}
