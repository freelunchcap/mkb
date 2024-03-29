package im.grusis.mkb.eco;

import im.grusis.mkb.core.emulator.*;
import im.grusis.mkb.core.exception.MkbException;
import im.grusis.mkb.core.repository.model.MkbAccount;
import im.grusis.mkb.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * User: Mothership
 * Date: 13-6-19
 * Time: 下午11:20
 */
@Component
public class UpdateScheduler {

  @Autowired AccountService accountService;
  @Autowired EmulatorUser user;
  @Autowired EmulatorCard card;
  @Autowired EmulatorMapStage mapStage;
  @Autowired EmulatorChip chip;

  @Scheduled(cron = "0 2 0/2 * * *")
  public void refreshUserInfo() throws MkbException {
    Iterable<MkbAccount> mkbAccounts = accountService.getAll();
    for(MkbAccount account : mkbAccounts) {
      user.getUserInfo(account.getUsername(), true);
    }
  }

  @Scheduled(cron = "0 12 0/2 * * *")
  public void refreshCardGroup() throws MkbException {
    Iterable<MkbAccount> mkbAccounts = accountService.getAll();
    for(MkbAccount account : mkbAccounts) {
      card.getUserCards(account.getUsername(), true);
    }
  }

  @Scheduled(cron = "0 22 0/4 * * *")
  public void refreshMapStage() throws MkbException {
    Iterable<MkbAccount> mkbAccounts = accountService.getAll();
    for(MkbAccount account : mkbAccounts) {
      mapStage.gameGetUserMapStages(account.getUsername(), true);
    }
  }

  @Scheduled(cron = "0 32 0/4 * * *")
  public void refreshChip() throws MkbException {
    Iterable<MkbAccount> accounts = accountService.getAll();
    for(MkbAccount account : accounts) {
      chip.getUserChip(account.getUsername(), true);
    }
  }

}
