package im.grusis.mkb.eco.util.filter.operators;

import im.grusis.mkb.core.repository.model.MkbAccount;
import im.grusis.mkb.core.util.AccountFilter;

/**
 * User: Mothership
 * Date: 13-6-8
 * Time: 上午2:09
 */
public class NegFilter implements AccountFilter {

  private AccountFilter filter;

  public NegFilter(AccountFilter filter) {
    this.filter = filter;
  }

  @Override
  public boolean accept(MkbAccount account) {
    return !filter.accept(account);
  }
}
