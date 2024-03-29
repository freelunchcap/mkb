package im.grusis.mkb.core.emulator.game.model.basic;

import java.util.ArrayList;

import im.grusis.mkb.core.emulator.game.model.response.HasTimestamp;

/**
 * User: Mothership
 * Date: 13-6-7
 * Time: 下午9:46
 */
public class MapStageAll extends ArrayList<MapDef> implements HasTimestamp {
  private long timestamp = System.currentTimeMillis();

  @Override
  public long getTimestamp() {
    return timestamp;
  }
}
