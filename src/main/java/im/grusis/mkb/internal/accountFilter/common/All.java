package im.grusis.mkb.internal.accountFilter.common;

import im.grusis.mkb.internal.MkbAccount;
import im.grusis.mkb.internal.accountFilter.AccountFilter;

/**
 * User: Mothership
 * Date: 13-6-15
 * Time: 上午12:28
 */
public class All implements AccountFilter {
  @Override
  public boolean accept(MkbAccount account) {
    return true;
  }
}