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
import org.caulfield.wsif.enumerated.reference.Enum_Country;

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
    /**
     * Validate that the address has a minimum configuration.
     */
    address.isUsable();
    /**
     * Do not parse non-US addresses or PO BOX addresses. Instead just try to
     * clean up and reformat the address components.
     */
    if (!Enum_Country.UNITED_STATES_OF_AMERICA.equals(address.getCountry()) || address.getAddress().toUpperCase().contains("BOX")) {
      address.setAddress(Formatter.toProperCase(address.getAddress().
        toUpperCase().
        replace("PO ", "POST OFFICE ").
        replace("P.O.", "POST OFFICE").
        replace("P. O.", "POST OFFICE")).trim());
      address.setCity(Formatter.toProperCase(address.getCity()));
      address.setCounty(Formatter.toProperCase(address.getCounty()));
      address.setState(address.getState() == null ? null : address.getState().toUpperCase());
      address.setPostalCode(address.getPostalCode() == null ? null : address.getPostalCode().toUpperCase());
      return address;
    }
    /**
     * The address is in the United States and is not a P.O. Box.
     */
    Address addressClean = parse(address.getAddressFormatted());
    addressClean.setId(address.getId());
    /**
     * Dump NULL street address fields.
     */
    if (addressClean.getAddress().equalsIgnoreCase("null")) {
      addressClean.setAddress(null);
    }
    return addressClean;
  }

  /**
   * Convert a raw address string into a well-formed WSIF address object.
   * <p/>
   * Valid only for US addresses. This method manually sets the country code to
   * Enum_Country.UNITED_STATES_OF_AMERICA ('US').
   * <p/>
   * @param addressRaw
   * @return
   * @throws Exception if the address is null or empty
   */
  public Address parse(String addressRaw) throws Exception {
    if (addressRaw == null || addressRaw.isEmpty()) {
      throw new Exception("Provided raw address is empty or null");
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
     * <p/>
     * The zip code is rarely misidentified and so is not required here. Also,
     * FCC ULS records do not provide a postal code, so not requiring one here
     * allows those addresses to be cleaned up and a postal code added later.
     */
    Address address = buildAddress(getFormatter().normalizeParsedAddress(getParser().parse(addressRaw)));
    if (address.getAddress() != null
      && address.getCity() != null
      && address.getState() != null) {
      return address;
    }
    throw new Exception("Address parsing could not produce a usable address");
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
    address.setCountry(Enum_Country.UNITED_STATES_OF_AMERICA);
    return address;
  }
}
