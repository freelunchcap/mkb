package im.grusis.mkb.repository;

import im.grusis.mkb.internal.MkAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * User: Mothership
 * Date: 13-5-27
 * Time: 下午10:03
 */
@Repository
public class AccountRepository extends MkbRepository<MkAccount> {

  private static final Logger Log = LoggerFactory.getLogger(AccountRepository.class);

  public AccountRepository() {
    super("accounts");
  }

  public MkAccount getAccount(String username) {
    return read(username, MkAccount.class);
  }

  public void createOrUpdateAccount(MkAccount mkAccount) {
    String index = mkAccount.getUsername();
    if(index == null || index.isEmpty()) {
      Log.error("Cannot create or update account with no username.");
      return;
    }
    write(index, mkAccount, true);
  }

}
