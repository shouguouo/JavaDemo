package com.shouguouo.dingding;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by shouguouo~ on 2018/9/21.
 */
public class ShouGuoGuo {
    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=a2f6b730b042c7040dac09152a13073700386b31dcc400c3e07ded4236207adb";
    public static String secret = "SECdbc64dc55330ed0e7a4bd786c90d18e9cb656b9a04aae4f8b074c89f13d3ff0b";
    public static void main(String[] args) {

      /*  DingTalkClient client = new DefaultDingTalkClient(WEBHOOK_TOKEN);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        link.setMessageUrl("https://music.163.com/#/song?id=29418037");
        link.setPicUrl("");
        link.setTitle("微光-华晨宇");
        link.setText("");
        request.setText(text);
        try {
            OapiRobotSendResponse response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
*/
    }
    
    public static String encode() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        return URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
    }
}
