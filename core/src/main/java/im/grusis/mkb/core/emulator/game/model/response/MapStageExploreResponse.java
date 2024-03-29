package im.grusis.mkb.core.emulator.game.model.response;

import im.grusis.mkb.core.emulator.game.model.basic.Explore;

/**
 * User: Mothership
 * Date: 13-5-31
 * Time: 下午8:28
 */
public class MapStageExploreResponse extends EnergyUsingResponse<Explore> {

  public static final String StageNotAvailableMessage = "关卡尚未开启";  // 关卡尚未开启!

  public boolean stageNotAvailable() {
    return message != null && message.contains(StageNotAvailableMessage);
  }
}
