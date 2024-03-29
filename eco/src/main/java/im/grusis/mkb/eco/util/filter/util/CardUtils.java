package im.grusis.mkb.eco.util.filter.util;

import java.util.*;

import im.grusis.mkb.core.emulator.game.model.basic.UserCard;
import im.grusis.mkb.eco.util.filter.common.CompareOperator;

/**
 * User: Mothership
 * Date: 13-6-15
 * Time: 下午11:05
 */
public class CardUtils {

  public static List<Integer> GetCardIdList(Iterable<UserCard> cards) {
    List<Integer> ret = new ArrayList<Integer>();
    for(UserCard card : cards) {
      ret.add(card.getCardId());
    }
    return ret;
  }

  public static Map<Integer, Integer> GetCardCount(Iterable<UserCard> cards) {
    Map<Integer, Integer> ret = new TreeMap<Integer, Integer>();
    for(int id : GetCardIdList(cards)) {
      Integer count = ret.get(id);
      if(count == null) {
        count = 1;
      } else {
        count++;
      }
      ret.put(id, count);
    }
    return ret;
  }

  public static boolean CompareCardCount(Map<Integer, Integer> source, Map<Integer, Integer> target, CompareOperator compare) {
    for(Map.Entry<Integer, Integer> node : target.entrySet()) {
      Integer value = source.get(node.getKey());
      if(value == null) {
        value = 0;
      }
      int threshold = node.getValue();
      switch(compare) {
        case LessThan:
          if(value >= threshold) {
            return false;
          }
          break;
        case LessThanOrEqualTo:
          if(value > threshold) {
            return false;
          }
          break;
        case EqualTo:
          if(value != threshold) {
            return false;
          }
          break;
        case GreaterThan:
          if(value <= threshold) {
            return false;
          }
          break;
        case GreaterThanOrEqualTo:
          if(value < threshold) {
            return false;
          }
          break;
        case NotEqualTo:
          if(value == threshold) {
            return false;
          }
          break;
        default:
          return false;
      }
    }
    return true;
  }
}
