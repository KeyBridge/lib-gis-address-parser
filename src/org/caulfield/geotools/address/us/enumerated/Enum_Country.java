/*
 *  Copyright (C) 2011 Caulfield IP Holdings (Caulfield) and/or its affiliates.
 *  All rights reserved. Use is subject to license terms.
 *
 *  Software Code is protected by Caulfield Copyrights. Caulfield hereby
 *  reserves all rights in and to Caulfield Copyrights and no license is
 *  granted under Caulfield Copyrights in this Software License Agreement.
 *  Caulfield generally licenses Caulfield Copyrights for commercialization
 *  pursuant to the terms of either Caulfield's Standard Software Source Code
 *  License Agreement or Caulfield's Standard Product License Agreement.
 *  A copy of Caulfield's either License Agreement can be obtained on request
 *  by email from: info@caufield.org.
 */
package org.caulfield.geotools.address.us.enumerated;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Enumerated list of ISO 3166-1 alpha-3 and alpha-2 Country codes.
 * <p/>
 * See
 * {@link http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3 ISO 3166-1 alpha-3}
 * <p/>
 * Three-letter iso3 codes defined in ISO 3166-1, part of the ISO 3166 standard
 * published by the International Organization for Standardization (ISO), to
 * represent countries, dependent territories, and special areas of geographical
 * interest. They allow a better visual association between the codes and the
 * iso3 names than the two-letter alpha-2 codes.
 * <p/>
 * Some descriptions have been edited slightly to improved readability.
 * <p/>
 * See the United Nations statistics division for an authoritative list of
 * recognized countries (and ISO-3 codes) at
 * {@link http://unstats.un.org/unsd/methods/m49/m49alpha.htm Countries or areas, codes and abbreviations}
 * <p/>
 * @author jesse
 */
public enum Enum_Country {

  AFGHANISTAN("AFG", "AF", "Afghanistan"),
  ALBANIA("ALB", "AL", "Albania"),
  ALGERIA("DZA", "DZ", "Algeria"),
  AMERICAN_SAMOA("ASM", "AS", "American Samoa"),
  ANDORRA("AND", "AD", "Andorra"),
  ANGOLA("AGO", "AO", "Angola"),
  ANGUILLA("AIA", "AI", "Anguilla"),
  ANTARCTICA("", "AQ", "Antarctica"),
  ANTIGUA_AND_BARBUDA("ATG", "AG", "Antigua and Barbuda"),
  ARGENTINA("ARG", "AR", "Argentina"),
  ARMENIA("ARM", "AM", "Armenia"),
  ARUBA("ABW", "AW", "Aruba"),
  AUSTRALIA("AUS", "AU", "Australia"),
  AUSTRIA("AUT", "AT", "Austria"),
  AZERBAIJAN("AZE", "AZ", "Azerbaijan"),
  BAHAMAS("BHS", "BS", "Bahamas"),
  BAHRAIN("BHR", "BH", "Bahrain"),
  BANGLADESH("BGD", "BD", "Bangladesh"),
  BARBADOS("BRB", "BB", "Barbados"),
  BELARUS("BLR", "BY", "Belarus"),
  BELGIUM("BEL", "BE", "Belgium"),
  BELIZE("BLZ", "BZ", "Belize"),
  BENIN("BEN", "BJ", "Benin"),
  BERMUDA("BMU", "BM", "Bermuda"),
  BHUTAN("BTN", "BT", "Bhutan"),
  BOLIVIA("BOL", "BO", "Bolivia"),
  BOSNIA_AND_HERZEGOVINA("BIH", "BA", "Bosnia and Herzegovina"),
  BOTSWANA("BWA", "BW", "Botswana"),
  BOUVET_ISLAND("", "BV", "Bouvet Island"),
  BRAZIL("BRA", "BR", "Brazil"),
  BRITISH_INDIAN_OCEAN_TERRITORY("", "IO", "British Indian Ocean Territory"),
  BRUNEI_DARUSSALAM("BRN", "BN", "Brunei Darussalam"),
  BULGARIA("BGR", "BG", "Bulgaria"),
  BURKINA_FASO("BFA", "BF", "Burkina Faso"),
  BURUNDI("BDI", "BI", "Burundi"),
  CAMBODIA("KHM", "KH", "Cambodia"),
  CAMEROON("CMR", "CM", "Cameroon"),
  CANADA("CAN", "CA", "Canada"),
  CAPE_VERDE("CPV", "CV", "Cape Verde"),
  CAYMAN_ISLANDS("CYM", "KY", "Cayman Islands"),
  CENTRAL_AFRICAN_REPUBLIC("CAF", "CF", "Central African Republic"),
  CHAD("TCD", "TD", "Chad"),
  CHILE("CHL", "CL", "Chile"),
  CHINA("CHN", "CN", "China"),
  CHRISTMAS_ISLAND("", "CX", "Christmas Island"),
  COCOS_KEELING_ISLANDS("", "CC", "Cocos (Keeling) Islands"),
  COLOMBIA("COL", "CO", "Colombia"),
  COMOROS("COM", "KM", "Comoros"),
  CONGO("COG", "CG", "Congo"),
  CONGO_THE_DEMOCRATIC_REPUBLIC_OF_THE("COD", "CD", "The Democratic Republic of the Congo"),
  COOK_ISLANDS("COK", "CK", "Cook Islands"),
  COSTA_RICA("CRI", "CR", "Costa Rica"),
  COTE_DIVOIRE("CIV", "CI", "Cote D'Ivoire"),
  CROATIA("HRV", "HR", "Croatia"),
  CUBA("CUB", "CU", "Cuba"),
  CYPRUS("CYP", "CY", "Cyprus"),
  CZECH_REPUBLIC("CZE", "CZ", "Czech Republic"),
  DENMARK("DNK", "DK", "Denmark"),
  DJIBOUTI("DJI", "DJ", "Djibouti"),
  DOMINICA("DMA", "DM", "Dominica"),
  DOMINICAN_REPUBLIC("DOM", "DO", "Dominican Republic"),
  ECUADOR("ECU", "EC", "Ecuador"),
  EGYPT("EGY", "EG", "Egypt"),
  EL_SALVADOR("SLV", "SV", "El Salvador"),
  EQUATORIAL_GUINEA("GNQ", "GQ", "Equatorial Guinea"),
  ERITREA("ERI", "ER", "Eritrea"),
  ESTONIA("EST", "EE", "Estonia"),
  ETHIOPIA("ETH", "ET", "Ethiopia"),
  FALKLAND_ISLANDS("FLK", "FK", "Falkland Islands"),
  FAROE_ISLANDS("FRO", "FO", "Faroe Islands"),
  FIJI("FJI", "FJ", "Fiji"),
  FINLAND("FIN", "FI", "Finland"),
  FRANCE("FRA", "FR", "France"),
  FRENCH_GUIANA("GUF", "GF", "French Guiana"),
  FRENCH_POLYNESIA("PYF", "PF", "French Polynesia"),
  FRENCH_SOUTHERN_TERRITORIES("", "TF", "French Southern Territories"),
  GABON("GAB", "GA", "Gabon"),
  GAMBIA("GMB", "GM", "Gambia"),
  GEORGIA("GEO", "GE", "Georgia"),
  GERMANY("DEU", "DE", "Germany"),
  GHANA("GHA", "GH", "Ghana"),
  GIBRALTAR("GIB", "GI", "Gibraltar"),
  GREECE("GRC", "GR", "Greece"),
  GREENLAND("GRL", "GL", "Greenland"),
  GRENADA("GRD", "GD", "Grenada"),
  GUADELOUPE("GLP", "GP", "Guadeloupe"),
  GUAM("GUM", "GU", "Guam"),
  GUATEMALA("GTM", "GT", "Guatemala"),
  GUINEA_BISSAU("GNB", "GW", "Guinea-Bissau"),
  GUINEA("GIN", "GN", "Guinea"),
  GUYANA("GUY", "GY", "Guyana"),
  HAITI("HTI", "HT", "Haiti"),
  HEARD_ISLAND_AND_MCDONALD_ISLANDS("", "HM", "Heard Island and McDonald Islands"),
  HOLY_SEE_VATICAN_CITY_STATE("VAT", "VA", "Holy See (Vatican City State)"),
  HONDURAS("HND", "HN", "Honduras"),
  HONG_KONG("HKG", "HK", "Hong Kong"),
  HUNGARY("HUN", "HU", "Hungary"),
  ICELAND("ISL", "IS", "Iceland"),
  INDIA("IND", "IN", "India"),
  INDONESIA("IDN", "ID", "Indonesia"),
  IRAN("IRN", "IR", "Islamic Republic of Iran"),
  IRAQ("IRQ", "IQ", "Iraq"),
  IRELAND("IRL", "IE", "Ireland"),
  ISRAEL("ISR", "IL", "Israel"),
  ITALY("ITA", "IT", "Italy"),
  JAMAICA("JAM", "JM", "Jamaica"),
  JAPAN("JPN", "JP", "Japan"),
  JORDAN("JOR", "JO", "Jordan"),
  KAZAKHSTAN("KAZ", "KZ", "Kazakhstan"),
  KENYA("KEN", "KE", "Kenya"),
  KIRIBATI("KIR", "KI", "Kiribati"),
  KOREA("KOR", "KR", "Republic of Korea"),
  KOREA_NORTH("PRK", "KP", "Democratic People's Republic of Korea"),
  KUWAIT("KWT", "KW", "Kuwait"),
  KYRGYZSTAN("KGZ", "KG", "Kyrgyzstan"),
  LAO_PEOPLES_DEMOCRATIC_REPUBLIC("LAO", "LA", "Lao People's Democratic Republic"),
  LATVIA("LVA", "LV", "Latvia"),
  LEBANON("LBN", "LB", "Lebanon"),
  LESOTHO("LSO", "LS", "Lesotho"),
  LIBERIA("LBR", "LR", "Liberia"),
  LIBYAN_ARAB_JAMAHIRIYA("LBY", "LY", "Libyan Arab Jamahiriya"),
  LIECHTENSTEIN("LIE", "LI", "Liechtenstein"),
  LITHUANIA("LTU", "LT", "Lithuania"),
  LUXEMBOURG("LUX", "LU", "Luxembourg"),
  MACAO("MAC", "MO", "Macao"),
  MACEDONIA("MKD", "MK", "Macedonia"),
  MADAGASCAR("MDG", "MG", "Madagascar"),
  MALAWI("MWI", "MW", "Malawi"),
  MALAYSIA("MYS", "MY", "Malaysia"),
  MALDIVES("MDV", "MV", "Maldives"),
  MALI("MLI", "ML", "Mali"),
  MALTA("MLT", "MT", "Malta"),
  MARSHALL_ISLANDS("MHL", "MH", "Marshall Islands"),
  MARTINIQUE("MTQ", "MQ", "Martinique"),
  MAURITANIA("MRT", "MR", "Mauritania"),
  MAURITIUS("MUS", "MU", "Mauritius"),
  MAYOTTE("", "YT", "Mayotte"),
  MEXICO("MEX", "MX", "Mexico"),
  MICRONESIA("FSM", "FM", "Federated States of Micronesia"),
  MOLDOVA_REPUBLIC_OF("MDA", "MD", "Republic of Moldova"),
  MONACO("MCO", "MC", "Monaco"),
  MONGOLIA("MNG", "MN", "Mongolia"),
  MONTSERRAT("MSR", "MS", "Montserrat"),
  MOROCCO("MAR", "MA", "Morocco"),
  MOZAMBIQUE("MOZ", "MZ", "Mozambique"),
  MYANMAR("MMR", "MM", "Myanmar"),
  NAMIBIA("NAM", "NA", "Namibia"),
  NAURU("NRU", "NR", "Nauru"),
  NEPAL("NPL", "NP", "Nepal"),
  NETHERLANDS_ANTILLES("ANT", "AN", "Netherlands Antilles"),
  NETHERLANDS("NLD", "NL", "Netherlands"),
  NEW_CALEDONIA("NCL", "NC", "New Caledonia"),
  NEW_ZEALAND("NZL", "NZ", "New Zealand"),
  NICARAGUA("NIC", "NI", "Nicaragua"),
  NIGERIA("NGA", "NG", "Nigeria"),
  NIGER("NER", "NE", "Niger"),
  NIUE("NIU", "NU", "Niue"),
  NORFOLK_ISLAND("NFK", "NF", "Norfolk Island"),
  NORTHERN_MARIANA_ISLANDS("MNP", "MP", "Northern Mariana Islands"),
  NORWAY("NOR", "NO", "Norway"),
  OMAN("OMN", "OM", "Oman"),
  PAKISTAN("PAK", "PK", "Pakistan"),
  PALAU("PLW", "PW", "Palau"),
  PALESTINIAN_TERRITORY("", "PS", "Palestinian Territory"),
  PANAMA("PAN", "PA", "Panama"),
  PAPUA_NEW_GUINEA("PNG", "PG", "Papua New Guinea"),
  PARAGUAY("PRY", "PY", "Paraguay"),
  PERU("PER", "PE", "Peru"),
  PHILIPPINES("PHL", "PH", "Philippines"),
  PITCAIRN("PCN", "PN", "Pitcairn"),
  POLAND("POL", "PL", "Poland"),
  PORTUGAL("PRT", "PT", "Portugal"),
  PUERTO_RICO("PRI", "PR", "Puerto Rico"),
  QATAR("QAT", "QA", "Qatar"),
  REUNION("REU", "RE", "Reunion"),
  ROMANIA("ROM", "RO", "Romania"),
  RUSSIAN_FEDERATION("RUS", "RU", "Russian Federation"),
  RWANDA("RWA", "RW", "Rwanda"),
  SAINT_HELENA("SHN", "SH", "Saint Helena"),
  SAINT_KITTS_AND_NEVIS("KNA", "KN", "Saint Kitts and Nevis"),
  SAINT_LUCIA("LCA", "LC", "Saint Lucia"),
  SAINT_PIERRE_AND_MIQUELON("SPM", "PM", "Saint Pierre and Miquelon"),
  SAINT_VINCENT_AND_THE_GRENADINES("VCT", "VC", "Saint Vincent and the Grenadines"),
  SAMOA("WSM", "WS", "Samoa"),
  SAN_MARINO("SMR", "SM", "San Marino"),
  SAO_TOME_AND_PRINCIPE("STP", "ST", "Sao Tome and Principe"),
  SAUDI_ARABIA("SAU", "SA", "Saudi Arabia"),
  SENEGAL("SEN", "SN", "Senegal"),
  SERBIA_AND_MONTENEGRO("", "CS", "Serbia and Montenegro"),
  SEYCHELLES("SYC", "SC", "Seychelles"),
  SIERRA_LEONE("SLE", "SL", "Sierra Leone"),
  SINGAPORE("SGP", "SG", "Singapore"),
  SLOVAKIA("SVK", "SK", "Slovakia"),
  SLOVENIA("SVN", "SI", "Slovenia"),
  SOLOMON_ISLANDS("SLB", "SB", "Solomon Islands"),
  SOMALIA("SOM", "SO", "Somalia"),
  SOUTH_AFRICA("ZAF", "ZA", "South Africa"),
  SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS("", "GS", "South Georgia and the South Sandwich Islands"),
  SPAIN("ESP", "ES", "Spain"),
  SRI_LANKA("LKA", "LK", "Sri Lanka"),
  SUDAN("SDN", "SD", "Sudan"),
  SURINAME("SUR", "SR", "Suriname"),
  SVALBARD_AND_JAN_MAYEN("SJM", "SJ", "Svalbard and Jan Mayen"),
  SWAZILAND("SWZ", "SZ", "Swaziland"),
  SWEDEN("SWE", "SE", "Sweden"),
  SWITZERLAND("CHE", "CH", "Switzerland"),
  SYRIAN_ARAB_REPUBLIC("SYR", "SY", "Syrian Arab Republic"),
  TAIWAN_PROVINCE_OF_CHINA("TWN", "TW", "Taiwan, Province of China"),
  TAJIKISTAN("TJK", "TJ", "Tajikistan"),
  TANZANIA_UNITED_REPUBLIC_OF("TZA", "TZ", "United Republic of Tanzania"),
  THAILAND("THA", "TH", "Thailand"),
  TIMOR_LESTE("", "TL", "Timor-Leste"),
  TOGO("TGO", "TG", "Togo"),
  TOKELAU("TKL", "TK", "Tokelau"),
  TONGA("TON", "TO", "Tonga"),
  TRINIDAD_AND_TOBAGO("TTO", "TT", "Trinidad and Tobago"),
  TUNISIA("TUN", "TN", "Tunisia"),
  TURKEY("TUR", "TR", "Turkey"),
  TURKMENISTAN("TKM", "TM", "Turkmenistan"),
  TURKS_AND_CAICOS_ISLANDS("TCA", "TC", "Turks and Caicos Islands"),
  TUVALU("TUV", "TV", "Tuvalu"),
  UGANDA("UGA", "UG", "Uganda"),
  UKRAINE("UKR", "UA", "Ukraine"),
  UNITED_ARAB_EMIRATES("ARE", "AE", "United Arab Emirates"),
  UNITED_KINGDOM("GBR", "GB", "United Kingdom"),
  UNITED_STATES_MINOR_OUTLYING_ISLANDS("", "UM", "United States Minor Outlying Islands"),
  UNITED_STATES_OF_AMERICA("USA", "US", "United States of America"),
  URUGUAY("URY", "UY", "Uruguay"),
  UZBEKISTAN("UZB", "UZ", "Uzbekistan"),
  VANUATU("VUT", "VU", "Vanuatu"),
  VENEZUELA("VEN", "VE", "Venezuela"),
  VIET_NAM("VNM", "VN", "Viet Nam"),
  VIRGIN_ISLANDS_BRITISH("VGB", "VG", "British Virgin Islands"),
  VIRGIN_ISLANDS_US("VIR", "VI", "U.S. Virgin Islands"),
  WALLIS_AND_FUTUNA("WLF", "WF", "Wallis and Futuna"),
  WESTERN_SAHARA("ESH", "EH", "Western Sahara"),
  YEMEN("YEM", "YE", "Yemen"),
  ZAMBIA("ZMB", "ZM", "Zambia"),
  ZIMBABWE("ZWE", "ZW", "Zimbabwe");
  /**
   * The 3-character ISO country code. e.g. 'USA'
   */
  private String iso3;
  /**
   * The 2-character ISO country code. e.g. 'US'
   */
  private String iso2;
  /**
   * The country proper name. e.g. 'United States of America'
   */
  private String description;

  private Enum_Country(String name, String iso2, String description) {
    this.iso3 = name;
    this.iso2 = iso2;
    this.description = description;
  }

  // <editor-fold defaultstate="collapsed" desc="Getter">
  /**
   * The country proper name. e.g. 'United States of America'
   */
  public String getDescription() {
    return description;
  }

  /**
   * The 2-character ISO country code. e.g. 'US'
   */
  public String getIso2() {
    return iso2;
  }

  /**
   * The 3-character ISO country code. e.g. 'USA'
   */
  public String getIso3() {
    return iso3;
  }// </editor-fold>

  /**
   * Search by 2-character ISO country code. e.g. 'US'. Returns the indicated
   * country code. if not found, returns the default value of UNITED_STATES.
   * <p/>
   * @param iso2Code
   * @return
   */
  public static Enum_Country findByIso2Code(String iso2Code) {
    for (Enum_Country enum_CountryCode : Enum_Country.values()) {
      if (enum_CountryCode.iso2.equalsIgnoreCase(iso2Code)) {
        return enum_CountryCode;
      }
    }
    // if not found return null
    return null;
  }

  /**
   * Search by the country proper name. e.g. 'United States of America'. Returns
   * the first country code where the enumerated description CONTAINS the
   * indicated description. For example, "United States" will match 'United
   * States of America'.
   * <p/>
   * This is NOT case sensitive
   * <p/>
   * @param description e.g. 'United States of America'
   * @return UNITED_STATES if null, empty or no match is found.
   */
  public static Enum_Country findByDescription(String description) {
    if (description == null || description.isEmpty()) {
      return UNITED_STATES_OF_AMERICA;
    }
    for (Enum_Country enum_CountryCode : Enum_Country.values()) {
      if (enum_CountryCode.description.toLowerCase().contains(description.toLowerCase())) {
        return enum_CountryCode;
      }
    }
    // if not found return null
    return null;
  }

  /**
   * Returns the country proper name. e.g. 'United States of America'
   * <p/>
   * @return
   */
  @Override
  public String toString() {
    return description;
  }

  /**
   * Convenience method to get all the country codes as a list
   * <p/>
   * @return
   */
  public static List<Enum_Country> getList() {
    return Arrays.asList(Enum_Country.values());
  }

  /**
   * Get the list of countries as a HashMap of Key:CountryName, Value:Iso2
   * <p/>
   * @return
   */
  public static Map<String, String> getAsHashMap() {
    Map<String, String> countryMap = new HashMap<String, String>();
    for (Enum_Country country : Enum_Country.values()) {
      countryMap.put(country.iso2, country.description);
    }
    return countryMap;
  }

  /**
   * Scratchbox
   * <p/>
   * @param arg
   */
  public static void main(String[] arg) {
    Enum_Country us = Enum_Country.findByIso2Code("sr");
    System.out.println(us);
    System.out.println(Enum_Country.findByDescription("United States of America"));

  }
}
