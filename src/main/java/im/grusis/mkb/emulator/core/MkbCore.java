package im.grusis.mkb.emulator.core;

import java.io.IOException;
import java.util.*;

import im.grusis.mkb.emulator.MkbEmulator;
import im.grusis.mkb.emulator.core.model.basic.PassportLogin;
import im.grusis.mkb.emulator.core.model.response.GameDataFactory;
import im.grusis.mkb.emulator.core.model.response.PassportLoginResponse;
import org.apache.http.*;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: Mothership
 * Date: 13-5-26
 * Time: 下午12:59
 */

public class MkbCore {

  @Autowired MkbEmulator emulator;

  private DefaultHttpClient httpClient;

  private String phpp;
  private String phpl;
  private String pvc;
  private String pvb;

  private String host;
  private String username;
  private long uid;
  private String key;
  private String mac;
  private long time;

  public MkbCore(String host, String username, long uid, String key, String mac, long time,
                  DefaultHttpClient httpClient, String phpp, String phpl, String pvc, String pvb) {
    this.host = host;
    this.username = username;
    this.uid = uid;
    this.key = key;
    this.mac = mac;
    this.time = time;
    this.httpClient = httpClient;
    this.phpp = phpp;
    this.phpl = phpl;
    this.pvc = pvc;
    this.pvb = pvb;
  }

  public String doAction(String service, String action, Map<String, String> params) {
    String url = host + service + "?do=" + action + "&phpp=" + phpp + "&phpl=" + phpl + "&pvc=" + pvc + "&pvb=" + pvb;
    HttpPost post = new HttpPost(url);
    if(params != null) {
      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
      Set<Map.Entry<String, String>> entrySet = params.entrySet();
      for(Map.Entry<String, String> entry : entrySet) {
        nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
      }
      post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
    }
    try {
      httpClient.addResponseInterceptor(new HttpResponseInterceptor() {

        public void process(final HttpResponse response, final HttpContext context) throws HttpException, IOException {
          HttpEntity entity = response.getEntity();
          if(entity != null) {
            Header ceheader = entity.getContentEncoding();
            if(ceheader != null) {
              HeaderElement[] codecs = ceheader.getElements();
              for(HeaderElement codec : codecs) {
                if(codec.getName().equalsIgnoreCase("gzip")) {
                  response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                  return;
                }
              }
            }
          }
        }

      });
      HttpResponse response = httpClient.execute(post);
      HttpEntity entity = response.getEntity();
      String content = EntityUtils.toString(entity);
      return content;
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public PassportLogin doPassportLogin() {
    Map<String, String> paramMap = new LinkedHashMap<String, String>();
    paramMap.put("Devicetoken", "");
    paramMap.put("time", Long.toString(time));
    paramMap.put("key", key);
    paramMap.put("Origin", "TTGM");
    paramMap.put("Udid", mac);
    paramMap.put("UserName", username);
    paramMap.put("Password", Long.toString(uid));
    String responseString = doAction("login.php", "PassportLogin", paramMap);
    return GameDataFactory.getGameData(responseString, PassportLoginResponse.class).getData();
  }
}
