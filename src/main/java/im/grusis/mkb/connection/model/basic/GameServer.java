package im.grusis.mkb.connection.model.basic;

/**
 * User: Mothership
 * Date: 13-5-24
 * Time: 下午11:10
 */
public class GameServer {
  String gsChatIp;
  int gsChatPort;
  long gsCreateDate;
  String gsDesc;
  long gsId;
  String gsIp;
  String gsName;
  String gsPlatform;
  int gsPort;
  int gsServiceType;
  String gsServiceUrl;
  int gsState;
  int userCount;

  public String getGsChatIp() {
    return gsChatIp;
  }

  public void setGsChatIp(String gsChatIp) {
    this.gsChatIp = gsChatIp;
  }

  public int getGsChatPort() {
    return gsChatPort;
  }

  public void setGsChatPort(int gsChatPort) {
    this.gsChatPort = gsChatPort;
  }

  public long getGsCreateDate() {
    return gsCreateDate;
  }

  public void setGsCreateDate(long gsCreateDate) {
    this.gsCreateDate = gsCreateDate;
  }

  public String getGsDesc() {
    return gsDesc;
  }

  public void setGsDesc(String gsDesc) {
    this.gsDesc = gsDesc;
  }

  public long getGsId() {
    return gsId;
  }

  public void setGsId(long gsId) {
    this.gsId = gsId;
  }

  public String getGsIp() {
    return gsIp;
  }

  public void setGsIp(String gsIp) {
    this.gsIp = gsIp;
  }

  public String getGsName() {
    return gsName;
  }

  public void setGsName(String gsName) {
    this.gsName = gsName;
  }

  public String getGsPlatform() {
    return gsPlatform;
  }

  public void setGsPlatform(String gsPlatform) {
    this.gsPlatform = gsPlatform;
  }

  public int getGsPort() {
    return gsPort;
  }

  public void setGsPort(int gsPort) {
    this.gsPort = gsPort;
  }

  public int getGsServiceType() {
    return gsServiceType;
  }

  public void setGsServiceType(int gsServiceType) {
    this.gsServiceType = gsServiceType;
  }

  public String getGsServiceUrl() {
    return gsServiceUrl;
  }

  public void setGsServiceUrl(String gsServiceUrl) {
    this.gsServiceUrl = gsServiceUrl;
  }

  public int getGsState() {
    return gsState;
  }

  public void setGsState(int gsState) {
    this.gsState = gsState;
  }

  public int getUserCount() {
    return userCount;
  }

  public void setUserCount(int userCount) {
    this.userCount = userCount;
  }
}