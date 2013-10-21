
package StockTradingClient;

/**
 * @date    :   Oct 20, 2013
 * @author  :   Hirosh Wickramasuriya
 */
public class KeyValuePair {
    
  private final String key;
  private final String value;
  public KeyValuePair(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String toString() {
    return value;
  }

}
