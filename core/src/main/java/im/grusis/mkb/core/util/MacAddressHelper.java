package im.grusis.mkb.core.util;

import java.util.Random;

/**
 * User: Mothership
 * Date: 13-5-26
 * Time: 下午10:26
 */
public class MacAddressHelper {
  private static final Random r = new Random();


  public static String getMacAddress() {
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < 6; i++) {
      if(i != 0) {
        sb.append(':');
      }
      sb.append(Integer.toHexString(16 + r.nextInt(240)));
    }
    return sb.toString().toUpperCase();
  }
}
