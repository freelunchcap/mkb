package im.grusis.mkb.app;

import im.grusis.mkb.core.config.MkbCoreConfig;
import im.grusis.mkb.core.emulator.AutomatedServiceEngine;
import im.grusis.mkb.core.emulator.MkbEmulator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FindAccounts {

  public static void main(String[] args) throws Exception{
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MkbCoreConfig.class);
    ctx.start();

//    EcoSystemConfig.KeepConfig scl = ctx.getBean(EcoSystemConfig.KeepConfig.class);

//    String username = "urnmso90377";
    String username = "tug100";
    AutomatedServiceEngine ase = ctx.getBean(AutomatedServiceEngine.class);
    MkbEmulator emulator = ctx.getBean(MkbEmulator.class);

//    core.webLogin("tug100", "mewhome123", MacAddressHelper.getMacAddress());
//    core.gameGetUserInfo(username, true);
//    ase.clearCounterAttacks(username, 5);
    emulator.gameAcceptSalary(username);
    ase.clearMaze(username,8, 2, true, 0);
    ase.clearMaze(username,7, 2, true, 0);
    ase.clearMaze(username,6, 2, true, 0);
    ase.collectAndSendEnergy(username, true, true);
    ase.clearCounterAttacks(username, 5);
    ase.clearMaze(username,8, 2, true, 0);
    ase.clearMaze(username,7, 2, true, 0);
    ase.clearMaze(username,6, 2, true, 0);
    ase.clearMaze(username,5, 2, true, 0);
  }
}
