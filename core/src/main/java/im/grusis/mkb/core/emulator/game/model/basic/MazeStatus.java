package im.grusis.mkb.core.emulator.game.model.basic;

public class MazeStatus {
  private String Name;
  private int Layer;
  private int Clear;
  private int FreeReset;
  private int ResetCash;

  public String getName() {
    return Name;
  }

  public int getLayer() {
    return Layer;
  }

  public void setLayer(int layer) {
    Layer = layer;
  }

  public int getClear() {
    return Clear;
  }

  public int getFreeReset() {
    return FreeReset;
  }

  public int getResetCash() {
    return ResetCash;
  }

  public boolean isClear() {
    return Clear != 0;
  }

  public void clear() {
    Clear = 1;
  }

  public boolean isFreeReset() {
    return FreeReset != 0;
  }

  public void reset() {
    if(FreeReset > 0) {
      FreeReset = 0;
    } else {
      ResetCash += 20;
    }
    Layer = 1;
    Clear = 0;
  }
}