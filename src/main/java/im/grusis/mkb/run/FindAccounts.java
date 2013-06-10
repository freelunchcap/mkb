package im.grusis.mkb.run;

import im.grusis.mkb.config.RuntimeConfig;
import im.grusis.mkb.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * User: Mothership
 * Date: 13-6-7
 * Time: 下午11:46
 */
public class FindAccounts {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RuntimeConfig.class);
    ctx.start();

    AccountService as = ctx.getBean(AccountService.class);
  }
}