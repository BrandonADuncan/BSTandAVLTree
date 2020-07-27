import java.util.List;

/**
 * this class holds the methods that will compare an old data set to a new data
 * set and return a list of suspicious buyers per the requirements set forth by
 * the DEA.
 * 
 * @author Brandon Duncan
 */
public class DEA {
  private AVLMap<String, Purchases> map = new AVLMap<String, Purchases>();
  private AVLSet<String> set = new AVLSet<String>();

  /**
   * Read in the historic file using an ArcosReader and store each purchase in the
   * map, with the BUYER_NAME+" "+BUYER_ADDL_CO_INFO as the key, and a Purchases
   * as a value. Each Purchases object should contain a record of every number of
   * pills bought by that buyer throughout the file.
   * 
   * @param filename - the filename to read the historic data from.
   */
  public void readHistoricData(String filename) {
    // initialize our iterator to go through the tsv file
    ArcosReader ar = new ArcosReader(filename);
    for (Map<String, String> aLine : ar) {
      // determine buyer name
      String key = buyerName(aLine);

      // retrieve dosage unit
      int dosageUnit = (int) Double.parseDouble(aLine.get("DOSAGE_UNIT"));

      // locate the current value for our key
      Purchases curr = map.get(key);

      // if it is empty, create new purchases field and add to map
      if (curr == null) {
        Purchases transactions = new Purchases();
        transactions.add(dosageUnit);
        map.set(key, transactions);
      }

      // if it already exists update pills of the purchases.
      else {
        curr.add(dosageUnit);
      }
    }

    // calculate the mean for each key on the map
    List<Purchases> values = map.values();
    int listSize = values.size();
    for (int i = 0; i < listSize; i++) {
      Purchases curr = values.get(i);
      curr.calcMean();
      curr.calcStdDev();
    }
  }

  /**
   * After the historic data has been read in, this method reads through a second
   * file of new purchases, and builds a set of Strings to flag for the DEA. These
   * strings are either the BUYER_NAME+" "+BUYER_ADDL_CO_INFO of a brand new buyer
   * that didn't make any purchases in the historic data, or it is the
   * BUYER_NAME+" "+BUYER_ADDL_CO_INFO+" "+TRANSACTION_ID of a purchase which was
   * greater than that buyer's mean purchase plus four times its standard
   * deviation.
   */
  public Set<String> flagSuspiciousBuys(String filename) {
    // initialize arcosReader to read in new file
    ArcosReader ar = new ArcosReader(filename);
    for (Map<String, String> aLine : ar) {
      // determine buyer name
      String buyer = buyerName(aLine);

      // if buyer is not in original map, add to set.
      if (!map.find(buyer)) {
        set.add(buyer);
      }

      // else if already in map check if meets flag condition
      else {
        // calculate flag condition for current buyer
        Purchases curr = map.get(buyer);
        Double flagCon = curr.getMean() + 4 * curr.getStdDev();

        // retrieve new purchase
        int newPurchase = (int) Double.parseDouble(aLine.get("DOSAGE_UNIT"));

        // compare with flag condition
        if (newPurchase > flagCon) {
          // if larger retrieve transaction ID
          String tID = aLine.get("TRANSACTION_ID");
          // add to set including new transaction id
          set.add(buyer + " " + tID);
        }
      }
    }
    return set;
  }

  /**
   * this method parses the buyers name depending on whether the Buyer's
   * additional informatino is provided or not
   * 
   * @param line the line from our tsv file we are analyzing, provided from arcos
   *             reader.
   * @return key A string that represents the buyer's name that will be used as a
   *         key in our map/set
   */
  private String buyerName(Map<String, String> line) {
    // retrieve buyer name
    String buyer = line.get("BUYER_NAME");
    // retrieve additional info
    String buyerInfo = line.get("BUYER_ADDL_CO_INFO");
    String key = "";
    // determine key based on if buyerInfo exists or not
    // used contains method only because == null, or .length() == null were not
    // triggering
    // not sure how this affects speed.
    if (buyerInfo.contains("null")) {
      key = buyer;
    } else {
      key = buyer + " " + buyerInfo;
    }
    return key;
  }
}

