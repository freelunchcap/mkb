package im.grusis.mkb.core.emulator.game.model.basic;

import java.util.List;

/**
 * User: Mothership
 * Date: 13-5-29
 * Time: 下午11:38
 */
public class Group {
  private long Uid;
  private String UserCardIds;
  private String UserRuneIds;
  private long GroupId;
  private List<UserCard> UserCardInfo;
  private List<UserRuneInfo> UserRuneInfo;

  public long getUid() {
    return Uid;
  }

  public void setUid(long uid) {
    Uid = uid;
  }

  public String getUserCardIds() {
    return UserCardIds;
  }

  public void setUserCardIds(String userCardIds) {
    UserCardIds = userCardIds;
  }

  public String getUserRuneIds() {
    return UserRuneIds;
  }

  public void setUserRuneIds(String userRuneIds) {
    UserRuneIds = userRuneIds;
  }

  public long getGroupId() {
    return GroupId;
  }

  public void setGroupId(long groupId) {
    GroupId = groupId;
  }

  public List<UserCard> getUserCardInfo() {
    return UserCardInfo;
  }

  public void setUserCardInfo(List<UserCard> userCardInfo) {
    UserCardInfo = userCardInfo;
  }

  public List<UserRuneInfo> getUserRuneInfo() {
    return UserRuneInfo;
  }

  public void setUserRuneInfo(List<UserRuneInfo> userRuneInfo) {
    UserRuneInfo = userRuneInfo;
  }
}
