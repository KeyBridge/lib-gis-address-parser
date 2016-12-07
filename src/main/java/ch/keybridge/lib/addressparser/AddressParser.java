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
package ch.keybridge.lib.addressparser;

import ch.keybridge.lib.addressparser.us.Formatter;
import ch.keybridge.lib.addressparser.us.Parser;
import ch.keybridge.lib.addressparser.us.enumerated.AddressComponentKey;
import ch.keybridge.lib.gis.dto.GISAddress;
import java.util.Map;

/**
 *
 * @author jesse
 */
public class AddressParser {

  private final Formatter formatter;
  private final Parser parser;

  public AddressParser() {
    this.formatter = new Formatter();
    this.parser = new Parser();
  }

  /**
   * Clean up a WSIF address by parsing the contents and re-populating the WSIF
   * address fields. Returns a new object - does not edit the passed object.
   *
   * @param address a raw, unformatted WSIF address object
   * @return a well-formed, formatted WSIF address object
   * @throws Exception if the address is null or empty
   */
  public GISAddress parse(GISAddress address) throws Exception {
    /**
     * Validate that the address has a minimum configuration.
     */
    if (address.getStreet() == null || address.getStreet().isEmpty()) {
      throw new Exception("Cannot parse the partial address: " + address);
    }
    /**
     * If the country is not set the force it to USA.
     */
    if (address.getCountry() == null) {
      address.setCountry("US");
    }
    /**
     * Do not parse non-US addresses or PO BOX addresses or street addresses
     * without numbers. Parser does not handle streets without numbers.
     * <p>
     * Instead just try to clean up and reformat the address components.
     */
    if ("US".equalsIgnoreCase(address.getCountry())
            || address.getStreet().toUpperCase().contains("BOX")
            || !address.getStreet().matches("\\d")) {
      address.setStreet(Formatter.toProperCase(address.getStreet().
              toUpperCase().
              replace("PO ", "POST OFFICE ").
              replace("P.O.", "POST OFFICE").
              replace("P. O.", "POST OFFICE")).trim());
      address.setCity(Formatter.toProperCase(address.getCity()));
      address.setCounty(Formatter.toProperCase(address.getCounty()));
      address.setState(Formatter.toUpperCase(address.getState()));
      address.setPostalCode(Formatter.toUpperCase(address.getPostalCode()));
      return address;
    }
    /**
     * The address is in the United States and is not a P.O. Box.
     */
    GISAddress addressClean = parse(address.format());
    /**
     * Dump NULL street address fields.
     */
    if (addressClean.getStreet().trim().equalsIgnoreCase("null")) {
      addressClean.setStreet(Formatter.toProperCase(address.getStreet()));
    }
    return addressClean;
  }

  /**
   * Convert a raw address string into a well-formed WSIF address object.
   * <p>
   * Valid only for US addresses. This method manually sets the country code to
   * ECountry.UNITED_STATES_OF_AMERICA ('US').
   *
   * @param addressRaw a free-text address String
   * @return a formatted address WSIF component
   * @throws Exception if the address is null or empty or fails to parse
   */
  public GISAddress parse(String addressRaw) throws Exception {
    if (addressRaw == null || addressRaw.isEmpty()) {
      throw new Exception("Provided raw address is empty or null");
    }
    /**
     * Build a WSIF address based upon the normalized, parsed address
     * components.
     * <p>
     * If all the address components cannot be parsed then discard the address.
     * This is because the parser is somewhat fragile and may incorrectly assign
     * city to street names, states to cities, etc. Requiring that all fields
     * are present ensures that the address was more likely to be correctly
     * parsed.
     * <p>
     * The zip code is rarely misidentified and so is not required here. Also,
     * FCC ULS records do not provide a postal code, so not requiring one here
     * allows those addresses to be cleaned up and a postal code added later.
     */
    return buildAddress(formatter.normalizeParsedAddress(parser.parse(addressRaw)));
//    GISAddress address = buildGISAddress(formatter.normalizeParsedGISAddress(parser.parse(addressRaw)));
//    if (!address.isComplete()) {
//      throw new Exception("GISAddress parsing could not produce a usable address");
//    }
//    return address;
  }

  /**
   * Internal method to build a well-formed WSIF address from a parsed address
   * map.
   *
   * @param parsedGISAddressMap
   * @return a non-null, well formed WSIF address
   */
  private GISAddress buildAddress(Map<AddressComponentKey, String> parsedGISAddressMap) {
    return new GISAddress(formatter.toStreetAddress(parsedGISAddressMap),
                          parsedGISAddressMap.get(AddressComponentKey.CITY),
                          parsedGISAddressMap.get(AddressComponentKey.STATE),
                          parsedGISAddressMap.get(AddressComponentKey.ZIP),
                          "US");
  }
}
