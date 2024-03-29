package im.grusis.mkb.core.emulator.game.model.basic;

/**
 * User: Mothership
 * Date: 13-6-9
 * Time: 下午4:13
 */
public class Chip {

  public static final int FromStage = 1;
  public static final int FromMaze = 2;
  public static final int FromFreeFight = 3;

  private int id;
  private int num;
  private int type;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public void set() {
    num = 1;
  }

  public void reset() {
    num = 0;
  }

  public boolean obtained() {
    return num == 1;
  }
}
