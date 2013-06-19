package im.grusis.mkb.eco.util.dictionary.impl;

import im.grusis.mkb.eco.util.dictionary.BasicDict;
import im.grusis.mkb.eco.util.dictionary.MkbDictionary;

/**
 * User: Mothership
 * Date: 13-6-5
 * Time: 下午9:53
 */
public class BasicUsernameDict extends BasicDict {

  public final static String CharSet = "0123456789abcdefghijklmnopqrstuvwxyz";

  public BasicUsernameDict(String prefix) {
    super(prefix, CharSet, MkbDictionary.MaxUsernameLength);
    init();
  }
}