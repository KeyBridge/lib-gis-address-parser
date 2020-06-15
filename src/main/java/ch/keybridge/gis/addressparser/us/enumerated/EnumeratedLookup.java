package ch.keybridge.gis.addressparser.us.enumerated;

import java.util.HashMap;
import java.util.Map;

/**
 * Pre-configured lookup mapping of defined key-value pairs. This class contains
 * key/value lookup pairs for spelled numbers, ordinals, directions, street
 * types, states, units, city names containing the prefix 'st', etc.
 *
 * @author jesse
 */
public class EnumeratedLookup {

  /**
   * A mapping of spelled numbers to their integer value
   */
  private static final Map<String, String> NUMBER = new HashMap<>();
  /**
   * A mapping of direction words (north, south ...) to their abbreviation (N,
   * S, ....)
   */
  private static final Map<String, String> DIRECTION = new HashMap<>();
  /**
   * A mapping of street type words (road, alley, circle...) to their respective
   * abbreviation
   */
  private static final Map<String, String> STREET_TYPE = new HashMap<>();
  /**
   * A mapping of US state names to their respective iso3166 (iso2) abbreviation
   */
  private static final Map<String, String> STATE = new HashMap<>();
  /**
   * A mapping of living unit words (apartment, suite, etc.) to their respective
   * abbreviation
   */
  private static final Map<String, String> ADDRESS_UNIT = new HashMap<>();
  /**
   * A mapping of cities containing the prefix 'St' to the expanded name prefix
   * 'Saint'
   */
  private static final Map<String, String> SAINT_CITY = new HashMap<>();
  /**
   * A mapping of spelled numbers to their integer value
   */
  private static final Map<String, String> NUMBER_ORDINAL = new HashMap<>();

  /**
   * A mapping of direction words (north, south ...) to their abbreviation (N,
   * S, ....)
   *
   * @return mapping of direction words
   */
  public static Map<String, String> getDIRECTION() {
    return DIRECTION;
  }

  /**
   * A mapping of street type words (road, alley, circle...) to their respective
   * abbreviation
   *
   * @return mapping of street type words
   */
  public static Map<String, String> getSTREET_TYPE() {
    return STREET_TYPE;
  }

  /**
   * A mapping of US state names to their respective iso3166 (iso2) abbreviation
   *
   * @return mapping of US state names
   */
  public static Map<String, String> getSTATE() {
    return STATE;
  }

  /**
   * A mapping of living unit words (apartment, suite, etc.) to their respective
   * abbreviation
   *
   * @return mapping of living unit words
   */
  public static Map<String, String> getADDRESS_UNIT() {
    return ADDRESS_UNIT;
  }

  /**
   * A mapping of spelled numbers to their integer value
   *
   * @return mapping of spelled numbers
   */
  public static Map<String, String> getNUMBER() {
    return NUMBER;
  }

  /**
   * A mapping of cities containing the prefix 'St' to the expanded name prefix
   * 'Saint'
   *
   * @return mapping of cities
   */
  public static Map<String, String> getSAINT_CITY() {
    return SAINT_CITY;
  }

  /**
   * A mapping of spelled numbers to their integer value
   *
   * @return spelled numbers to their integer value
   */
  public static Map<String, String> getNUMBER_ORDINAL() {
    return NUMBER_ORDINAL;
  }

  static {
    NUMBER_ORDINAL.put("ZEROTH", "0TH");
    NUMBER_ORDINAL.put("FIRST", "1ST");
    NUMBER_ORDINAL.put("SECOND", "2ND");
    NUMBER_ORDINAL.put("THIRD", "3RD");
    NUMBER_ORDINAL.put("FORTH", "4TH");
    NUMBER_ORDINAL.put("FOURTH", "4TH");
    NUMBER_ORDINAL.put("FIFTH", "5TH");
    NUMBER_ORDINAL.put("SIXTH", "6TH");
    NUMBER_ORDINAL.put("SEVENTH", "7TH");
    NUMBER_ORDINAL.put("EIGHTH", "8TH");
    NUMBER_ORDINAL.put("NINTH", "9TH");
    NUMBER_ORDINAL.put("NINETH", "9TH");
    NUMBER_ORDINAL.put("TENTH", "10TH");
    NUMBER_ORDINAL.put("ELEVENTH", "11TH");
    NUMBER_ORDINAL.put("TWELFTH", "12TH");
    NUMBER_ORDINAL.put("TWELVETH", "12TH");
    NUMBER_ORDINAL.put("THIRTEENTH", "13TH");
    NUMBER_ORDINAL.put("FOURTEENTH", "14TH");
    NUMBER_ORDINAL.put("FIFTEENTH", "15TH");
    NUMBER_ORDINAL.put("SIXTEENTH", "16TH");
    NUMBER_ORDINAL.put("SEVENTEENTH", "17TH");
    NUMBER_ORDINAL.put("EIGHTEENTH", "18TH");
    NUMBER_ORDINAL.put("NINETEENTH", "19TH");
    NUMBER_ORDINAL.put("NINTEENTH", "19TH");

    NUMBER.put("ZERO", "0");
    NUMBER.put("ONE", "1");
    NUMBER.put("TWO", "2");
    NUMBER.put("THREE", "3");
    NUMBER.put("FOUR", "4");
    NUMBER.put("FIVE", "5");
    NUMBER.put("SIX", "6");
    NUMBER.put("SEVEN", "7");
    NUMBER.put("EIGHT", "8");
    NUMBER.put("NINE", "9");
    NUMBER.put("TEN", "0");
    NUMBER.put("ELEVEN", "11");
    NUMBER.put("TWELVE", "12");
    NUMBER.put("THIRTEEN", "13");
    NUMBER.put("FOURTEEN", "14");
    NUMBER.put("FIFTEEN", "15");
    NUMBER.put("SIXTEEN", "16");
    NUMBER.put("SEVENTEEN", "17");
    NUMBER.put("EIGHTTEEN", "18");
    NUMBER.put("NINETEEN", "19");
    NUMBER.put("TWENTY", "20");
    NUMBER.put("THIRTY", "30");
    NUMBER.put("FOURTY", "40");
    NUMBER.put("FORTY", "40");
    NUMBER.put("FIFTY", "50");
    NUMBER.put("SIXTY", "60");
    NUMBER.put("SEVENTY", "70");
    NUMBER.put("EIGHTY", "80");
    NUMBER.put("NINETY", "90");
    NUMBER.put("NINTY", "90");

    ADDRESS_UNIT.put("APARTMENT", "APT");
    ADDRESS_UNIT.put("APMT", "APT");
    ADDRESS_UNIT.put("BASEMENT", "BSMT");
    ADDRESS_UNIT.put("BUILDING", "BLDG");
    ADDRESS_UNIT.put("DEPARTMENT", "DEPT");
    ADDRESS_UNIT.put("FLOOR", "FL");
    ADDRESS_UNIT.put("F", "FL");
    ADDRESS_UNIT.put("FRONG", "FRNT");
    ADDRESS_UNIT.put("HANGAR", "HNGR");
    ADDRESS_UNIT.put("LOBBY", "LBBY");
    ADDRESS_UNIT.put("LBY", "LBBY");
    ADDRESS_UNIT.put("LOT", "LOT");
    ADDRESS_UNIT.put("LT", "LOT");
    ADDRESS_UNIT.put("LOWER", "LOWR");
    ADDRESS_UNIT.put("NUMBER", "#");
    ADDRESS_UNIT.put("#", "#");
    ADDRESS_UNIT.put("NO", "#");
    ADDRESS_UNIT.put("OFFICE", "OFC");
    ADDRESS_UNIT.put("OFIC", "OFC");
    ADDRESS_UNIT.put("OFFC", "OFC");
    ADDRESS_UNIT.put("PIER", "PIER");
    ADDRESS_UNIT.put("PENTHOUSE", "PH");
    ADDRESS_UNIT.put("PBOX", "PO BOX");
    ADDRESS_UNIT.put("PB", "PO BOX");
    ADDRESS_UNIT.put("PBX", "PO BOX");
    ADDRESS_UNIT.put("P O BOX", "PO BOX");
    ADDRESS_UNIT.put("POBX", "PO BOX");
    ADDRESS_UNIT.put("POBOX", "PO BOX");
    ADDRESS_UNIT.put("BOX", "BX");
    ADDRESS_UNIT.put("REAR", "REAR");
    ADDRESS_UNIT.put("ROOM", "RM");
    ADDRESS_UNIT.put("SIDE", "SIDE");
    ADDRESS_UNIT.put("SLIP", "SLIP");
    ADDRESS_UNIT.put("SPACE", "SPC");
    ADDRESS_UNIT.put("STOP", "STOP");
    ADDRESS_UNIT.put("SUITE", "STE");
    ADDRESS_UNIT.put("SUIT", "STE");
    ADDRESS_UNIT.put("TRAILER", "TRLR");
    ADDRESS_UNIT.put("UNIT", "UNT");
    ADDRESS_UNIT.put("UPPER", "UPPR");

    DIRECTION.put("NORTH", "N");
    DIRECTION.put("NORTHEAST", "NE");
    DIRECTION.put("EAST", "E");
    DIRECTION.put("SOUTHEAST", "SE");
    DIRECTION.put("SOUTH", "S");
    DIRECTION.put("SOUTHWEST", "SW");
    DIRECTION.put("WEST", "W");
    DIRECTION.put("NORTHWEST", "NW");

    //7/25/08
    STREET_TYPE.put("PKWAY", "PKY");
    STREET_TYPE.put("PKWYS", "PKY");
    STREET_TYPE.put("PKWY", "PKY");
//    STREET_TYPE.put("ROW", "ROW");
//    STREET_TYPE.put("RAMP", "RAMP");
//    STREET_TYPE.put("RUN", "RUN");
//    STREET_TYPE.put("RUE", "RUE");
    STREET_TYPE.put("MALL", "MAL");
//    STREET_TYPE.put("MART", "MART");
//    STREET_TYPE.put("PASS", "PASS");
    STREET_TYPE.put("WALKWAY", "WKWY");
    //?? What are these the abbrv of?
//    STREET_TYPE.put("GRD", "GRD"); //XXX:this is not garden is it?
//    STREET_TYPE.put("THWY", "THWY"); // throughway
//    STREET_TYPE.put("UNP", "UNP");
    STREET_TYPE.put("ALLEE", "ALY");
    STREET_TYPE.put("ALLEY", "ALY");
    STREET_TYPE.put("ALLY", "ALY");
    STREET_TYPE.put("ANEX", "ANX");
    STREET_TYPE.put("ANNEX", "ANX");
    STREET_TYPE.put("ANNX", "ANX");
    STREET_TYPE.put("ARCADE", "ARC");
    STREET_TYPE.put("AV", "AVE");
    STREET_TYPE.put("AVEN", "AVE");
    STREET_TYPE.put("AVENU", "AVE");
    STREET_TYPE.put("AVENUE", "AVE");
    STREET_TYPE.put("AVN", "AVE");
    STREET_TYPE.put("AVNUE", "AVE");
    STREET_TYPE.put("BAYOO", "BYU");
    STREET_TYPE.put("BAYOU", "BYU");
    STREET_TYPE.put("BEACH", "BCH");
    STREET_TYPE.put("BEND", "BND");
    STREET_TYPE.put("BLUF", "BLF");
    STREET_TYPE.put("BLUFF", "BLF");
    STREET_TYPE.put("BLUFFS", "BLFS");
    STREET_TYPE.put("BOT", "BTM");
    STREET_TYPE.put("BOTTM", "BTM");
    STREET_TYPE.put("BOTTOM", "BTM");
    STREET_TYPE.put("BOUL", "BLVD");
    STREET_TYPE.put("BOULEVARD", "BLVD");
    STREET_TYPE.put("BOULV", "BLVD");
    STREET_TYPE.put("BRANCH", "BR");
    STREET_TYPE.put("BRDGE", "BRG");
    STREET_TYPE.put("BRIDGE", "BRG");
    STREET_TYPE.put("BRNCH", "BR");
    STREET_TYPE.put("BROOK", "BRK");
    STREET_TYPE.put("BROOKS", "BRKS");
    STREET_TYPE.put("BURG", "BG");
    STREET_TYPE.put("BURGS", "BGS");
    STREET_TYPE.put("BYPA", "BYP");
    STREET_TYPE.put("BYPAS", "BYP");
    STREET_TYPE.put("BYPASS", "BYP");
    STREET_TYPE.put("BYPS", "BYP");
    STREET_TYPE.put("CAMP", "CP");
    STREET_TYPE.put("CANYN", "CYN");
    STREET_TYPE.put("CANYON", "CYN");
    STREET_TYPE.put("CAPE", "CPE");
    STREET_TYPE.put("CAUSEWAY", "CSWY");
    STREET_TYPE.put("CAUSWAY", "CSWY");
    STREET_TYPE.put("CEN", "CTR");
    STREET_TYPE.put("CENT", "CTR");
    STREET_TYPE.put("CENTER", "CTR");
    STREET_TYPE.put("CENTERS", "CTRS");
    STREET_TYPE.put("CENTR", "CTR");
    STREET_TYPE.put("CENTRE", "CTR");
    STREET_TYPE.put("CIRC", "CIR");
    STREET_TYPE.put("CIRCL", "CIR");
    STREET_TYPE.put("CIRCLE", "CIR");
    STREET_TYPE.put("CIRCLES", "CIRS");
    STREET_TYPE.put("CK", "CRK");
    STREET_TYPE.put("CLIFF", "CLF");
    STREET_TYPE.put("CLIFFS", "CLFS");
    STREET_TYPE.put("CLUB", "CLB");
    STREET_TYPE.put("CMP", "CP");
    STREET_TYPE.put("CNTER", "CTR");
    STREET_TYPE.put("CNTR", "CTR");
    STREET_TYPE.put("CNYN", "CYN");
    STREET_TYPE.put("COMMON", "CMN");
    STREET_TYPE.put("CORNER", "COR");
    STREET_TYPE.put("CORNERS", "CORS");
    STREET_TYPE.put("COURSE", "CRSE");
    STREET_TYPE.put("COURT", "CT");
    STREET_TYPE.put("COURTS", "CTS");
    STREET_TYPE.put("COVE", "CV");
    STREET_TYPE.put("COVES", "CVS");
    STREET_TYPE.put("CR", "CRK");
    STREET_TYPE.put("CRCL", "CIR");
    STREET_TYPE.put("CRCLE", "CIR");
    STREET_TYPE.put("CRECENT", "CRES");
    STREET_TYPE.put("CREEK", "CRK");
    STREET_TYPE.put("CRESCENT", "CRES");
    STREET_TYPE.put("CRESENT", "CRES");
    STREET_TYPE.put("CREST", "CRST");
    STREET_TYPE.put("CROSSING", "XING");
    STREET_TYPE.put("CROSSROAD", "XRD");
    STREET_TYPE.put("CRSCNT", "CRES");
    STREET_TYPE.put("CRSENT", "CRES");
    STREET_TYPE.put("CRSNT", "CRES");
    STREET_TYPE.put("CRSSING", "XING");
    STREET_TYPE.put("CRSSNG", "XING");
    STREET_TYPE.put("CRT", "CT");
    STREET_TYPE.put("CURVE", "CURV");
    STREET_TYPE.put("DALE", "DL");
    STREET_TYPE.put("DAM", "DM");
    STREET_TYPE.put("DIV", "DV");
    STREET_TYPE.put("DIVIDE", "DV");
    STREET_TYPE.put("DRIV", "DR");
    STREET_TYPE.put("DRIVE", "DR");
    STREET_TYPE.put("DRIVES", "DRS");
    STREET_TYPE.put("DRV", "DR");
    STREET_TYPE.put("DVD", "DV");
    STREET_TYPE.put("ESTATE", "EST");
    STREET_TYPE.put("ESTATES", "ESTS");
    STREET_TYPE.put("EXP", "EXPY");
    STREET_TYPE.put("EXPR", "EXPY");
    STREET_TYPE.put("EXPRESS", "EXPY");
    STREET_TYPE.put("EXPRESSWAY", "EXPY");
    STREET_TYPE.put("EXPW", "EXPY");
    STREET_TYPE.put("EXTENSION", "EXT");
    STREET_TYPE.put("EXTENSIONS", "EXTS");
    STREET_TYPE.put("EXTN", "EXT");
    STREET_TYPE.put("EXTNSN", "EXT");
    STREET_TYPE.put("FALLS", "FLS");
    STREET_TYPE.put("FERRY", "FRY");
    STREET_TYPE.put("FIELD", "FLD");
    STREET_TYPE.put("FIELDS", "FLDS");
    STREET_TYPE.put("FLAT", "FLT");
    STREET_TYPE.put("FLATS", "FLTS");
    STREET_TYPE.put("FORD", "FRD");
    STREET_TYPE.put("FORDS", "FRDS");
    STREET_TYPE.put("FOREST", "FRST");
    STREET_TYPE.put("FORESTS", "FRST");
    STREET_TYPE.put("FORG", "FRG");
    STREET_TYPE.put("FORGE", "FRG");
    STREET_TYPE.put("FORGES", "FRGS");
    STREET_TYPE.put("FORK", "FRK");
    STREET_TYPE.put("FORKS", "FRKS");
    STREET_TYPE.put("FORT", "FT");
    STREET_TYPE.put("FREEWAY", "FWY");
    STREET_TYPE.put("FREEWY", "FWY");
    STREET_TYPE.put("FRRY", "FRY");
    STREET_TYPE.put("FRT", "FT");
    STREET_TYPE.put("FRWAY", "FWY");
    STREET_TYPE.put("FRWY", "FWY");
    STREET_TYPE.put("GARDEN", "GDN");
    STREET_TYPE.put("GARDENS", "GDNS");
    STREET_TYPE.put("GARDN", "GDN");
    STREET_TYPE.put("GATEWAY", "GTWY");
    STREET_TYPE.put("GATEWY", "GTWY");
    STREET_TYPE.put("GATWAY", "GTWY");
    STREET_TYPE.put("GLEN", "GLN");
    STREET_TYPE.put("GLENS", "GLNS");
    STREET_TYPE.put("GRDEN", "GDN");
    STREET_TYPE.put("GRDN", "GDN");
    STREET_TYPE.put("GRDNS", "GDNS");
    STREET_TYPE.put("GREEN", "GRN");
    STREET_TYPE.put("GREENS", "GRNS");
    STREET_TYPE.put("GROV", "GRV");
    STREET_TYPE.put("GROVE", "GRV");
    STREET_TYPE.put("GROVES", "GRVS");
    STREET_TYPE.put("GTWAY", "GTWY");
    STREET_TYPE.put("HARB", "HBR");
    STREET_TYPE.put("HARBOR", "HBR");
    STREET_TYPE.put("HARBORS", "HBRS");
    STREET_TYPE.put("HARBR", "HBR");
    STREET_TYPE.put("HAVEN", "HVN");
    STREET_TYPE.put("HAVN", "HVN");
    STREET_TYPE.put("HEIGHT", "HTS");
    STREET_TYPE.put("HEIGHTS", "HTS");
    STREET_TYPE.put("HGTS", "HTS");
    STREET_TYPE.put("HIGHWAY", "HWY");
    STREET_TYPE.put("HIGHWY", "HWY");
    STREET_TYPE.put("HILL", "HL");
    STREET_TYPE.put("HILLS", "HLS");
    STREET_TYPE.put("HIWAY", "HWY");
    STREET_TYPE.put("HIWY", "HWY");
    STREET_TYPE.put("HLLW", "HOLW");
    STREET_TYPE.put("HOLLOW", "HOLW");
    STREET_TYPE.put("HOLLOWS", "HOLW");
    STREET_TYPE.put("HOLWS", "HOLW");
    STREET_TYPE.put("HRBOR", "HBR");
    STREET_TYPE.put("HT", "HTS");
    STREET_TYPE.put("HWAY", "HWY");
    STREET_TYPE.put("INLET", "INLT");
    STREET_TYPE.put("ISLAND", "IS");
    STREET_TYPE.put("ISLANDS", "ISS");
    STREET_TYPE.put("ISLES", "ISLE");
    STREET_TYPE.put("ISLND", "IS");
    STREET_TYPE.put("ISLNDS", "ISS");
    STREET_TYPE.put("JCTION", "JCT");
    STREET_TYPE.put("JCTN", "JCT");
    STREET_TYPE.put("JCTNS", "JCTS");
    STREET_TYPE.put("JUNCTION", "JCT");
    STREET_TYPE.put("JUNCTIONS", "JCTS");
    STREET_TYPE.put("JUNCTN", "JCT");
    STREET_TYPE.put("JUNCTON", "JCT");
    STREET_TYPE.put("KEY", "KY");
    STREET_TYPE.put("KEYS", "KYS");
    STREET_TYPE.put("KNOL", "KNL");
    STREET_TYPE.put("KNOLL", "KNL");
    STREET_TYPE.put("KNOLLS", "KNLS");
    STREET_TYPE.put("LA", "LN");
    STREET_TYPE.put("LAKE", "LK");
    STREET_TYPE.put("LAKES", "LKS");
    STREET_TYPE.put("LANDING", "LNDG");
    STREET_TYPE.put("LANE", "LN");
    STREET_TYPE.put("LANES", "LN");
    STREET_TYPE.put("LDGE", "LDG");
    STREET_TYPE.put("LIGHT", "LGT");
    STREET_TYPE.put("LIGHTS", "LGTS");
    STREET_TYPE.put("LNDNG", "LNDG");
    STREET_TYPE.put("LOAF", "LF");
    STREET_TYPE.put("LOCK", "LCK");
    STREET_TYPE.put("LOCKS", "LCKS");
    STREET_TYPE.put("LODG", "LDG");
    STREET_TYPE.put("LODGE", "LDG");
    STREET_TYPE.put("LOOPS", "LOOP");
    STREET_TYPE.put("MANOR", "MNR");
    STREET_TYPE.put("MANORS", "MNRS");
    STREET_TYPE.put("MEADOW", "MDW");
    STREET_TYPE.put("MEADOWS", "MDWS");
    STREET_TYPE.put("MEDOWS", "MDWS");
    STREET_TYPE.put("MILL", "ML");
    STREET_TYPE.put("MILLS", "MLS");
    STREET_TYPE.put("MISSION", "MSN");
    STREET_TYPE.put("MISSN", "MSN");
    STREET_TYPE.put("MNT", "MT");
    STREET_TYPE.put("MNTAIN", "MTN");
    STREET_TYPE.put("MNTN", "MTN");
    STREET_TYPE.put("MNTNS", "MTNS");
    STREET_TYPE.put("MOTORWAY", "MTWY");
    STREET_TYPE.put("MOUNT", "MT");
    STREET_TYPE.put("MOUNTAIN", "MTN");
    STREET_TYPE.put("MOUNTAINS", "MTNS");
    STREET_TYPE.put("MOUNTIN", "MTN");
    STREET_TYPE.put("MSSN", "MSN");
    STREET_TYPE.put("MTIN", "MTN");
    STREET_TYPE.put("NECK", "NCK");
    STREET_TYPE.put("ORCHARD", "ORCH");
    STREET_TYPE.put("ORCHRD", "ORCH");
    STREET_TYPE.put("OVERPASS", "OPAS");
    STREET_TYPE.put("OVL", "OVAL");
    STREET_TYPE.put("PARKS", "PARK");
    STREET_TYPE.put("PARKWAY", "PKWY");
    STREET_TYPE.put("PARKWAYS", "PKWY");
    STREET_TYPE.put("PARKWY", "PKWY");
    STREET_TYPE.put("PASSAGE", "PSGE");
    STREET_TYPE.put("PATHS", "PATH");
    STREET_TYPE.put("PIKES", "PIKE");
    STREET_TYPE.put("PINE", "PNE");
    STREET_TYPE.put("PINES", "PNES");
    STREET_TYPE.put("PK", "PARK");
    STREET_TYPE.put("PKWAY", "PKWY");
    STREET_TYPE.put("PKWYS", "PKWY");
    STREET_TYPE.put("PKY", "PKWY");
    STREET_TYPE.put("PLACE", "PL");
    STREET_TYPE.put("PLAIN", "PLN");
    STREET_TYPE.put("PLAINES", "PLNS");
    STREET_TYPE.put("PLAINS", "PLNS");
    STREET_TYPE.put("PLAZA", "PLZ");
    STREET_TYPE.put("PLZA", "PLZ");
    STREET_TYPE.put("POINT", "PT");
    STREET_TYPE.put("POINTS", "PTS");
    STREET_TYPE.put("PORT", "PRT");
    STREET_TYPE.put("PORTS", "PRTS");
    STREET_TYPE.put("PRAIRIE", "PR");
    STREET_TYPE.put("PRARIE", "PR");
    STREET_TYPE.put("PRK", "PARK");
    STREET_TYPE.put("PRR", "PR");
    STREET_TYPE.put("RAD", "RADL");
    STREET_TYPE.put("RADIAL", "RADL");
    STREET_TYPE.put("RADIEL", "RADL");
    STREET_TYPE.put("RANCH", "RNCH");
    STREET_TYPE.put("RANCHES", "RNCH");
    STREET_TYPE.put("RAPID", "RPD");
    STREET_TYPE.put("RAPIDS", "RPDS");
    STREET_TYPE.put("RDGE", "RDG");
    STREET_TYPE.put("REST", "RST");
    STREET_TYPE.put("RIDGE", "RDG");
    STREET_TYPE.put("RIDGES", "RDGS");
    STREET_TYPE.put("RIVER", "RIV");
    STREET_TYPE.put("RIVR", "RIV");
    STREET_TYPE.put("RNCHS", "RNCH");
    STREET_TYPE.put("ROAD", "RD");
    STREET_TYPE.put("ROADS", "RDS");
    STREET_TYPE.put("ROUTE", "RTE");
    STREET_TYPE.put("RVR", "RIV");
    STREET_TYPE.put("SHOAL", "SHL");
    STREET_TYPE.put("SHOALS", "SHLS");
    STREET_TYPE.put("SHOAR", "SHR");
    STREET_TYPE.put("SHOARS", "SHRS");
    STREET_TYPE.put("SHORE", "SHR");
    STREET_TYPE.put("SHORES", "SHRS");
    STREET_TYPE.put("SKYWAY", "SKWY");
    STREET_TYPE.put("SPNG", "SPG");
    STREET_TYPE.put("SPNGS", "SPGS");
    STREET_TYPE.put("SPRING", "SPG");
    STREET_TYPE.put("SPRINGS", "SPGS");
    STREET_TYPE.put("SPRNG", "SPG");
    STREET_TYPE.put("SPRNGS", "SPGS");
    STREET_TYPE.put("SPURS", "SPUR");
    STREET_TYPE.put("SQR", "SQ");
    STREET_TYPE.put("SQRE", "SQ");
    STREET_TYPE.put("SQRS", "SQS");
    STREET_TYPE.put("SQU", "SQ");
    STREET_TYPE.put("SQUARE", "SQ");
    STREET_TYPE.put("SQUARES", "SQS");
    STREET_TYPE.put("STATION", "STA");
    STREET_TYPE.put("STATN", "STA");
    STREET_TYPE.put("STN", "STA");
    STREET_TYPE.put("STR", "ST");
    STREET_TYPE.put("STRAV", "STRA");
    STREET_TYPE.put("STRAVE", "STRA");
    STREET_TYPE.put("STRAVEN", "STRA");
    STREET_TYPE.put("STRAVENUE", "STRA");
    STREET_TYPE.put("STRAVN", "STRA");
    STREET_TYPE.put("STREAM", "STRM");
    STREET_TYPE.put("STREET", "ST");
    STREET_TYPE.put("STREETS", "STS");
    STREET_TYPE.put("STREME", "STRM");
    STREET_TYPE.put("STRT", "ST");
    STREET_TYPE.put("STRVN", "STRA");
    STREET_TYPE.put("STRVNUE", "STRA");
    STREET_TYPE.put("SUMIT", "SMT");
    STREET_TYPE.put("SUMITT", "SMT");
    STREET_TYPE.put("SUMMIT", "SMT");
    STREET_TYPE.put("TERR", "TER");
    STREET_TYPE.put("TERRACE", "TER");
    STREET_TYPE.put("THROUGHWAY", "TRWY");
    STREET_TYPE.put("TPK", "TPKE");
    STREET_TYPE.put("TR", "TRL");
    STREET_TYPE.put("TRACE", "TRCE");
    STREET_TYPE.put("TRACES", "TRCE");
    STREET_TYPE.put("TRACK", "TRAK");
    STREET_TYPE.put("TRACKS", "TRAK");
    STREET_TYPE.put("TRAFFICWAY", "TRFY");
    STREET_TYPE.put("TRAIL", "TRL");
    STREET_TYPE.put("TRAILS", "TRL");
    STREET_TYPE.put("TRK", "TRAK");
    STREET_TYPE.put("TRKS", "TRAK");
    STREET_TYPE.put("TRLS", "TRL");
    STREET_TYPE.put("TRNPK", "TPKE");
    STREET_TYPE.put("TRPK", "TPKE");
    STREET_TYPE.put("TUNEL", "TUNL");
    STREET_TYPE.put("TUNLS", "TUNL");
    STREET_TYPE.put("TUNNEL", "TUNL");
    STREET_TYPE.put("TUNNELS", "TUNL");
    STREET_TYPE.put("TUNNL", "TUNL");
    STREET_TYPE.put("TURNPIKE", "TPKE");
    STREET_TYPE.put("TURNPK", "TPKE");
    STREET_TYPE.put("UNDERPASS", "UPAS");
    STREET_TYPE.put("UNION", "UN");
    STREET_TYPE.put("UNIONS", "UNS");
    STREET_TYPE.put("VALLEY", "VLY");
    STREET_TYPE.put("VALLEYS", "VLYS");
    STREET_TYPE.put("VALLY", "VLY");
    STREET_TYPE.put("VDCT", "VIA");
    STREET_TYPE.put("VIADCT", "VIA");
    STREET_TYPE.put("VIADUCT", "VIA");
    STREET_TYPE.put("VIEW", "VW");
    STREET_TYPE.put("VIEWS", "VWS");
    STREET_TYPE.put("VILL", "VLG");
    STREET_TYPE.put("VILLAG", "VLG");
    STREET_TYPE.put("VILLAGE", "VLG");
    STREET_TYPE.put("VILLAGES", "VLGS");
    STREET_TYPE.put("VILLE", "VL");
    STREET_TYPE.put("VILLG", "VLG");
    STREET_TYPE.put("VILLIAGE", "VLG");
    STREET_TYPE.put("VIST", "VIS");
    STREET_TYPE.put("VISTA", "VIS");
    STREET_TYPE.put("VLLY", "VLY");
    STREET_TYPE.put("VST", "VIS");
    STREET_TYPE.put("VSTA", "VIS");
    STREET_TYPE.put("WALKS", "WALK");
    STREET_TYPE.put("WELL", "WL");
    STREET_TYPE.put("WELLS", "WLS");
    STREET_TYPE.put("WY", "WAY");

    /**
     * State codes: US
     */
    STATE.put("ALABAMA", "AL");
    STATE.put("ALASKA", "AK");
    STATE.put("AMERICAN SAMOA", "AS");
    STATE.put("ARIZONA", "AZ");
    STATE.put("ARKANSAS", "AR");
    STATE.put("CALIFORNIA", "CA");
    STATE.put("COLORADO", "CO");
    STATE.put("CONNECTICUT", "CT");
    STATE.put("DELAWARE", "DE");
    STATE.put("DISTRICT OF COLUMBIA", "DC");
    STATE.put("FEDERATED STATES OF MICRONESIA", "FM");
    STATE.put("FLORIDA", "FL");
    STATE.put("GEORGIA", "GA");
    STATE.put("GUAM", "GU");
    STATE.put("HAWAII", "HI");
    STATE.put("IDAHO", "ID");
    STATE.put("ILLINOIS", "IL");
    STATE.put("INDIANA", "IN");
    STATE.put("IOWA", "IA");
    STATE.put("KANSAS", "KS");
    STATE.put("KENTUCKY", "KY");
    STATE.put("LOUISIANA", "LA");
    STATE.put("MAINE", "ME");
    STATE.put("MARSHALL IS", "MH");
    STATE.put("MARSHALL ISLANDS", "MH");
    STATE.put("MARYLAND", "MD");
    STATE.put("MASSACHUSETTS", "MA");
    STATE.put("MICHIGAN", "MI");
    STATE.put("MINNESOTA", "MN");
    STATE.put("MISSISSIPPI", "MS");
    STATE.put("MISSOURI", "MO");
    STATE.put("MONTANA", "MT");
    STATE.put("NEBRASKA", "NE");
    STATE.put("NEVADA", "NV");
    STATE.put("NEW HAMPSHIRE", "NH");
    STATE.put("NEW JERSEY", "NJ");
    STATE.put("NEW MEXICO", "NM");
    STATE.put("NEWJERSEY", "NJ");
    STATE.put("NEWMEXICO", "NM");
    STATE.put("NEWYORK", "NY");
    STATE.put("NEW YORK", "NY");
    STATE.put("N CAROLINA", "NC");
    STATE.put("N DAKOTA", "ND");
    STATE.put("NORTHERN MARIANA ISLANDS", "MP");
    STATE.put("N CAROLINA", "NC");
    STATE.put("NORTH DAKOTA", "ND");
    STATE.put("NORTHERN MARIANA ISLANDS", "MP");
    STATE.put("OHIO", "OH");
    STATE.put("OKLAHOMA", "OK");
    STATE.put("OREGON", "OR");
    STATE.put("PALAU", "PW");
    STATE.put("PENNSYLVANIA", "PA");
    STATE.put("PUERTO RICO", "PR");
    STATE.put("PUERTORICO", "PR");
    STATE.put("RHODE ISLAND", "RI");
    STATE.put("SOUTH CAROLINA", "SC");
    STATE.put("SOUTH DAKOTA", "SD");
    STATE.put("S CAROLINA", "SC");
    STATE.put("S DAKOTA", "SD");
    STATE.put("TENNESSEE", "TN");
    STATE.put("TEXAS", "TX");
    STATE.put("UTAH", "UT");
    STATE.put("VERMONT", "VT");
    STATE.put("VIRGIN IS", "VI");
    STATE.put("VIRGIN ISLANDS", "VI");
    STATE.put("VIRGINIA", "VA");
    STATE.put("WASHINGTON", "WA");
    STATE.put("WEST VIRGINIA", "WV");
    STATE.put("W VIRGINIA", "WV");
    STATE.put("WISCONSIN", "WI");
    STATE.put("WYOMING", "WY");

    /**
     * Place names with Saint / ST
     */
    SAINT_CITY.put("ST LOUISVILLE", "SAINT LOUISVILLE");
    SAINT_CITY.put("ST HENRY", "SAINT HENRY");
    SAINT_CITY.put("ST HEDWIG", "SAINT HEDWIG");
    SAINT_CITY.put("ST PARIS", "SAINT PARIS");
    SAINT_CITY.put("ST DAVID", "SAINT DAVID");
    SAINT_CITY.put("ST BENEDICT", "SAINT BENEDICT");
    SAINT_CITY.put("ST GEORGE", "SAINT GEORGE");
    SAINT_CITY.put("ST BENEDICT", "SAINT BENEDICT");
    SAINT_CITY.put("ST ANSGAR", "SAINT ANSGAR");
    SAINT_CITY.put("ST GEORGE", "SAINT GEORGE");
    SAINT_CITY.put("ST THOMAS", "SAINT THOMAS");
    SAINT_CITY.put("ST JOSEPH", "SAINT JOSEPH");
    SAINT_CITY.put("ST MARYS", "SAINT MARYS");
    SAINT_CITY.put("ST PAUL", "SAINT PAUL");
    SAINT_CITY.put("ST PAUL PARK", "SAINT PAUL PARK");
    SAINT_CITY.put("ST CHARLES", "SAINT CHARLES");
    SAINT_CITY.put("ST PAUL", "SAINT PAUL");
    SAINT_CITY.put("ST MICHAEL", "SAINT MICHAEL");
    SAINT_CITY.put("ST ALBANS", "SAINT ALBANS");
    SAINT_CITY.put("ST JOHN", "SAINT JOHN");
    SAINT_CITY.put("ST JAMES", "SAINT JAMES");
    SAINT_CITY.put("ST JAMES", "SAINT JAMES");
    SAINT_CITY.put("ST LAWRENCE", "SAINT LAWRENCE");
    SAINT_CITY.put("ST MATTHEWS", "SAINT MATTHEWS");
    SAINT_CITY.put("ST PETERS", "SAINT PETERS");
    SAINT_CITY.put("ST ALBANS", "SAINT ALBANS");
    SAINT_CITY.put("ST FRANCISVILLE", "SAINT FRANCISVILLE");
    SAINT_CITY.put("ST MARKS", "SAINT MARKS");
    SAINT_CITY.put("ST JOHNSVILLE", "SAINT JOHNSVILLE");
    SAINT_CITY.put("ST JOSEPH", "SAINT JOSEPH");
    SAINT_CITY.put("ST JOSEPH", "SAINT JOSEPH");
    SAINT_CITY.put("ST MAURICE", "SAINT MAURICE");
    SAINT_CITY.put("ST DONATUS", "SAINT DONATUS");
    SAINT_CITY.put("ST IGNACE", "SAINT IGNACE");
    SAINT_CITY.put("ST HELENA", "SAINT HELENA");
    SAINT_CITY.put("ST OLAF", "SAINT OLAF");
    SAINT_CITY.put("ST IGNATIUS", "SAINT IGNATIUS");
    SAINT_CITY.put("ST JAMES", "SAINT JAMES");
    SAINT_CITY.put("ST ALBANS", "SAINT ALBANS");
    SAINT_CITY.put("ST ROBERT", "SAINT ROBERT");
    SAINT_CITY.put("ST STEPHENS CHURCH", "SAINT STEPHENS CHURCH");
    SAINT_CITY.put("ST MARYS", "SAINT MARYS");
    SAINT_CITY.put("ST JOHNS", "SAINT JOHNS");
    SAINT_CITY.put("ST LIBORY", "SAINT LIBORY");
    SAINT_CITY.put("ST AGATHA", "SAINT AGATHA");
    SAINT_CITY.put("ST CHARLES", "SAINT CHARLES");
    SAINT_CITY.put("ST MARIES", "SAINT MARIES");
    SAINT_CITY.put("ST MICHAELS", "SAINT MICHAELS");
    SAINT_CITY.put("ST ANTHONY", "SAINT ANTHONY");
    SAINT_CITY.put("ST LIBORY", "SAINT LIBORY");
    SAINT_CITY.put("ST HELEN", "SAINT HELEN");
    SAINT_CITY.put("ST CROIX FALLS", "SAINT CROIX FALLS");
    SAINT_CITY.put("ST REGIS FALLS", "SAINT REGIS FALLS");
    SAINT_CITY.put("ST LOUIS", "SAINT LOUIS");
    SAINT_CITY.put("ST HELENA ISLAND", "SAINT HELENA ISLAND");
    SAINT_CITY.put("ST STEPHENS", "SAINT STEPHENS");
    SAINT_CITY.put("ST MARYS CITY", "SAINT MARYS CITY");
    SAINT_CITY.put("ST PAUL ISLAND", "SAINT PAUL ISLAND");
    SAINT_CITY.put("ST MICHAEL", "SAINT MICHAEL");
    SAINT_CITY.put("ST ELMO", "SAINT ELMO");
    SAINT_CITY.put("ST THOMAS", "SAINT THOMAS");
    SAINT_CITY.put("ST JOE", "SAINT JOE");
    SAINT_CITY.put("ST AUGUSTINE", "SAINT AUGUSTINE");
    SAINT_CITY.put("ST MARYS", "SAINT MARYS");
    SAINT_CITY.put("ST CLAIR SHORES", "SAINT CLAIR SHORES");
    SAINT_CITY.put("ST MARTIN", "SAINT MARTIN");
    SAINT_CITY.put("ST PAUL", "SAINT PAUL");
    SAINT_CITY.put("ST FRANCISVILLE", "SAINT FRANCISVILLE");
    SAINT_CITY.put("ST SIMONS ISLAND", "SAINT SIMONS ISLAND");
    SAINT_CITY.put("ST JOHN", "SAINT JOHN");
    SAINT_CITY.put("ST STEPHENS", "SAINT STEPHENS");
    SAINT_CITY.put("ST LUCAS", "SAINT LUCAS");
    SAINT_CITY.put("ST GABRIEL", "SAINT GABRIEL");
    SAINT_CITY.put("ST AMANT", "SAINT AMANT");
    SAINT_CITY.put("ST MICHAEL", "SAINT MICHAEL");
    SAINT_CITY.put("ST CLOUD", "SAINT CLOUD");
    SAINT_CITY.put("ST CHARLES", "SAINT CHARLES");
    SAINT_CITY.put("ST CROIX", "SAINT CROIX");
    SAINT_CITY.put("ST GEORGE", "SAINT GEORGE");
    SAINT_CITY.put("ST THOMAS", "SAINT THOMAS");
    SAINT_CITY.put("ST MARYS", "SAINT MARYS");
    SAINT_CITY.put("ST PAUL", "SAINT PAUL");
    SAINT_CITY.put("ST LOUIS", "SAINT LOUIS");
    SAINT_CITY.put("ST JOSEPH", "SAINT JOSEPH");
    SAINT_CITY.put("ST CLOUD", "SAINT CLOUD");
    SAINT_CITY.put("ST BERNICE", "SAINT BERNICE");
    SAINT_CITY.put("ST PAUL", "SAINT PAUL");
    SAINT_CITY.put("ST JAMES", "SAINT JAMES");
    SAINT_CITY.put("ST CHARLES", "SAINT CHARLES");
    SAINT_CITY.put("ST MARYS", "SAINT MARYS");
    SAINT_CITY.put("ST LOUIS", "SAINT LOUIS");
    SAINT_CITY.put("ST LANDRY", "SAINT LANDRY");
    SAINT_CITY.put("ST HELENS", "SAINT HELENS");
    SAINT_CITY.put("ST JO", "SAINT JO");
    SAINT_CITY.put("ST BERNARD", "SAINT BERNARD");
    SAINT_CITY.put("ST CHARLES", "SAINT CHARLES");
    SAINT_CITY.put("ST JOHNSBURY CENTER", "SAINT JOHNSBURY CENTER");
    SAINT_CITY.put("ST CHARLES", "SAINT CHARLES");
    SAINT_CITY.put("ST VINCENT", "SAINT VINCENT");
    SAINT_CITY.put("ST STEPHEN", "SAINT STEPHEN");
    SAINT_CITY.put("ST REGIS", "SAINT REGIS");
    SAINT_CITY.put("ST JUST CONTRACT", "SAINT JUST CONTRACT");
    SAINT_CITY.put("ST MARYS", "SAINT MARYS");
    SAINT_CITY.put("ST PETER", "SAINT PETER");
    SAINT_CITY.put("ST ELMO", "SAINT ELMO");
    SAINT_CITY.put("ST GEORGE ISLAND", "SAINT GEORGE ISLAND");
    SAINT_CITY.put("ST PAUL", "SAINT PAUL");
    SAINT_CITY.put("ST INIGOES", "SAINT INIGOES");
    SAINT_CITY.put("ST GEORGE", "SAINT GEORGE");
    SAINT_CITY.put("ST FRANCIS", "SAINT FRANCIS");
    SAINT_CITY.put("ST BONAVENTURE", "SAINT BONAVENTURE");
    SAINT_CITY.put("ST LEONARD", "SAINT LEONARD");
    SAINT_CITY.put("ST BONIFACE", "SAINT BONIFACE");
    SAINT_CITY.put("ST CLAIRSVILLE", "SAINT CLAIRSVILLE");
    SAINT_CITY.put("ST FRANCIS", "SAINT FRANCIS");
    SAINT_CITY.put("ST ELIZABETH", "SAINT ELIZABETH");
    SAINT_CITY.put("ST JAMES CITY", "SAINT JAMES CITY");
    SAINT_CITY.put("ST PAUL", "SAINT PAUL");
    SAINT_CITY.put("ST ANTHONY", "SAINT ANTHONY");
    SAINT_CITY.put("ST JOHNS", "SAINT JOHNS");
    SAINT_CITY.put("ST JOHN", "SAINT JOHN");
    SAINT_CITY.put("ST CLAIR", "SAINT CLAIR");
    SAINT_CITY.put("ST ANTHONY", "SAINT ANTHONY");
    SAINT_CITY.put("ST HELENA", "SAINT HELENA");
    SAINT_CITY.put("ST ALBANS BAY", "SAINT ALBANS BAY");
    SAINT_CITY.put("ST PETERSBURG", "SAINT PETERSBURG");
    SAINT_CITY.put("ST ANTHONY", "SAINT ANTHONY");
    SAINT_CITY.put("ST CLAIR", "SAINT CLAIR");
    SAINT_CITY.put("ST CLAIR", "SAINT CLAIR");
    SAINT_CITY.put("ST FRANCIS", "SAINT FRANCIS");
    SAINT_CITY.put("ST HILAIRE", "SAINT HILAIRE");
    SAINT_CITY.put("ST XAVIER", "SAINT XAVIER");
    SAINT_CITY.put("ST PETERSBURG", "SAINT PETERSBURG");
    SAINT_CITY.put("ST BENEDICT", "SAINT BENEDICT");
    SAINT_CITY.put("ST DAVID", "SAINT DAVID");
    SAINT_CITY.put("ST JOHNSBURY", "SAINT JOHNSBURY");
    SAINT_CITY.put("ST NAZIANZ", "SAINT NAZIANZ");
    SAINT_CITY.put("ST FRANCIS", "SAINT FRANCIS");
    SAINT_CITY.put("ST MICHAELS", "SAINT MICHAELS");
    SAINT_CITY.put("ST ANN", "SAINT ANN");
    SAINT_CITY.put("ST JACOB", "SAINT JACOB");
    SAINT_CITY.put("ST JAMES", "SAINT JAMES");
    SAINT_CITY.put("ST CLAIR", "SAINT CLAIR");
    SAINT_CITY.put("ST HELENS", "SAINT HELENS");
    SAINT_CITY.put("ST JOSEPH", "SAINT JOSEPH");
    SAINT_CITY.put("ST JOHN", "SAINT JOHN");
    SAINT_CITY.put("ST PAUL", "SAINT PAUL");
    SAINT_CITY.put("ST EDWARD", "SAINT EDWARD");
    SAINT_CITY.put("ST ROSE", "SAINT ROSE");
    SAINT_CITY.put("ST JOE", "SAINT JOE");
    SAINT_CITY.put("ST JOHNS", "SAINT JOHNS");
    SAINT_CITY.put("ST MEINRAD", "SAINT MEINRAD");
    SAINT_CITY.put("ST GEORGE", "SAINT GEORGE");
    SAINT_CITY.put("ST FRANCIS", "SAINT FRANCIS");
    SAINT_CITY.put("ST GERMAIN", "SAINT GERMAIN");
    SAINT_CITY.put("ST FRANCIS", "SAINT FRANCIS");
    SAINT_CITY.put("ST PAULS", "SAINT PAULS");
    SAINT_CITY.put("ST GEORGES", "SAINT GEORGES");
    SAINT_CITY.put("ST CLOUD", "SAINT CLOUD");
    SAINT_CITY.put("ST ONGE", "SAINT ONGE");
    SAINT_CITY.put("ST ALBANS", "SAINT ALBANS");
    SAINT_CITY.put("ST STEPHEN", "SAINT STEPHEN");
    SAINT_CITY.put("ST MARY OF THE WOODS", "SAINT MARY OF THE WOODS");
    SAINT_CITY.put("ST ANDREWS", "SAINT ANDREWS");
    SAINT_CITY.put("ST LEO", "SAINT LEO");
    SAINT_CITY.put("ST MARY", "SAINT MARY");
    SAINT_CITY.put("ST PAUL", "SAINT PAUL");
    SAINT_CITY.put("ST VRAIN", "SAINT VRAIN");
    SAINT_CITY.put("ST PETERS", "SAINT PETERS");
    SAINT_CITY.put("ST BETHLEHEM", "SAINT BETHLEHEM");
    SAINT_CITY.put("ST MICHAEL", "SAINT MICHAEL");
    SAINT_CITY.put("ST DAVID", "SAINT DAVID");
    SAINT_CITY.put("ST JOHNS", "SAINT JOHNS");
    SAINT_CITY.put("ST CHARLES", "SAINT CHARLES");
    SAINT_CITY.put("ST CHARLES", "SAINT CHARLES");
    SAINT_CITY.put("ST MARIE", "SAINT MARIE");
    SAINT_CITY.put("ST MARY", "SAINT MARY");
    SAINT_CITY.put("ST JOSEPH", "SAINT JOSEPH");
    SAINT_CITY.put("ST CHARLES", "SAINT CHARLES");
    SAINT_CITY.put("ST CHARLES", "SAINT CHARLES");
    SAINT_CITY.put("ST PETER", "SAINT PETER");
    SAINT_CITY.put("ST ANNE", "SAINT ANNE");
    SAINT_CITY.put("ST AUGUSTINE", "SAINT AUGUSTINE");
    SAINT_CITY.put("ST ALBANS", "SAINT ALBANS");
    SAINT_CITY.put("ST BONIFACIUS", "SAINT BONIFACIUS");
    SAINT_CITY.put("ST MARTINVILLE", "SAINT MARTINVILLE");
    SAINT_CITY.put("ST MARYS", "SAINT MARYS");
    SAINT_CITY.put("ST PATRICK", "SAINT PATRICK");
    SAINT_CITY.put("ST CATHARINE", "SAINT CATHARINE");
    SAINT_CITY.put("ST ANDREWS", "SAINT ANDREWS");
    SAINT_CITY.put("ST BERNARD", "SAINT BERNARD");
    SAINT_CITY.put("ST CHRISTOPHER", "SAINT CHRISTOPHER");
    SAINT_CITY.put("ST DENIS", "SAINT DENIS");
    SAINT_CITY.put("ST JAMES", "SAINT JAMES");
    SAINT_CITY.put("ST JOSEPHS", "SAINT JOSEPHS");
    SAINT_CITY.put("ST LUKE", "SAINT LUKE");
    SAINT_CITY.put("ST MARKS", "SAINT MARKS");
    SAINT_CITY.put("ST PAUL", "SAINT PAUL");
    SAINT_CITY.put("ST COLUMBANS", "SAINT COLUMBANS");

  }
}
