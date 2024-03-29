package im.grusis.mkb.eco.util.filter.common;

import java.util.Map;

import im.grusis.mkb.core.emulator.game.model.basic.UserMapStage;
import im.grusis.mkb.core.repository.model.MkbAccount;
import im.grusis.mkb.core.util.AccountFilter;

/**
 * User: Mothership
 * Date: 13-6-18
 * Time: 下午11:59
 */
public class MapStagerProgressFilter implements AccountFilter {

  private int difficulty;
  private int stage;

  public MapStagerProgressFilter(int difficulty, int stage) {
    this.difficulty = difficulty;
    this.stage = stage;
  }

  @Override
  public boolean accept(MkbAccount account) {
    Map<Integer, UserMapStage> userMapStages = account.getUserMapStages();
    if(userMapStages == null) {
      return false;
    }
    UserMapStage userMapStage = userMapStages.get(stage);
    return userMapStage != null && userMapStage.getFinishedStage() >= difficulty;
  }
}
