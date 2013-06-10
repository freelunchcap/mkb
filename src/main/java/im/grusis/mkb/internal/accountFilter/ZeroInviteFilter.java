package im.grusis.mkb.internal.accountFilter;

import im.grusis.mkb.internal.MkbAccount;

/**
 * User: Mothership
 * Date: 13-6-5
 * Time: 下午8:59
 */
public class ZeroInviteFilter implements AccountFilter {
  @Override
  public boolean accept(MkbAccount account) {
    return account.getInviteCount() == 0;
  }
}