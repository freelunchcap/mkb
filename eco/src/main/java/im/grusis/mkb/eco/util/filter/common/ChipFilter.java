package im.grusis.mkb.eco.util.filter.common;

import java.util.Collection;
import java.util.Map;

import im.grusis.mkb.core.util.AccountFilter;
import im.grusis.mkb.core.emulator.game.model.basic.Chip;
import im.grusis.mkb.core.repository.model.MkbAccount;

/**
 * User: Mothership
 * Date: 13-6-16
 * Time: 上午2:10
 */
public class ChipFilter implements AccountFilter {

  public static int ChipMax = 3;

  private ChipType type;
  private CompareOperator compare;
  private int num;

  public ChipFilter(ChipType type, CompareOperator compare, int num) {
    this.type = type;
    this.compare = compare;
    this.num = num;
  }

  @Override
  public boolean accept(MkbAccount account) {
    Map<Integer, Chip> userChip = account.getUserChip();
    if(userChip == null) {
      return false;
    }
    Collection<Chip> chips = userChip.values();
    int count = 0;
    int intType;
    switch(type) {
      case Maze:
        intType = Chip.FromMaze;
        break;
      case Stage:
        intType = Chip.FromStage;
        break;
      case FreeFight:
        intType = Chip.FromFreeFight;
        break;
      default:
        return false;
    }
    for(Chip chip : chips) {
      if(chip.getType() == intType) {
        count += chip.getNum();
      }
    }
    switch(compare) {
      case LessThan:
        return count < num;
      case LessThanOrEqualTo:
        return count <= num;
      case EqualTo:
        return count == num;
      case GreaterThan:
        return count > num;
      case GreaterThanOrEqualTo:
        return count >= num;
      case NotEqualTo:
        return count != num;
      default:
        return false;
    }
  }
}
