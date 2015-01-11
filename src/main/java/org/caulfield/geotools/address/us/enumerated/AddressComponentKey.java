package org.caulfield.geotools.address.us.enumerated;

/**
 * Enumerated list of address map keys.
 * <p/>
 * @author jesse
 */
public enum AddressComponentKey {

  NAME, PREDIR, NUMBER, STREET, POSTDIR, TYPE, LINE2, CITY, STATE, ZIP,
  //intersections
  PREDIR2, STREET2, POSTDIR2, TYPE2,
  //geocode populated
  LAT, LON, COUNTY, TLID;
}