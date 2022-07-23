package com.marsh.zutils.util;

import com.alibaba.fastjson.JSONObject;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: Colin
 * @date: 2021-12-16 9:19 AM
 */
public class SignatureUtil {

    private static final String CHARSET = "UTF-8";
    private static final String SIGN_TYPE = "MD5";
    private static final String SING = "sign";
    private static final String SIGN_TIMESTAMP = "timestamp";
    private static final Long SIGN_TIMESTAMP_EXPIRES = 5 * 60 * 1000L;

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。
     *
     * @param data      Map类型数据
     * @param appSecret API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean check(Map<String, Object> data, String appSecret) {
        if (!data.containsKey(SING) || !data.containsKey(SIGN_TIMESTAMP)) {
            return false;
        }
        if (Math.abs(System.currentTimeMillis() - Long.valueOf(String.valueOf(data.get(SIGN_TIMESTAMP)))) > SIGN_TIMESTAMP_EXPIRES) {
            return false;
        }
        String sign = String.valueOf(data.get(SING));
        return sign(data, appSecret).equals(sign);
    }


    /**
     * 生成签名.
     *
     * @param data      待签名数据
     * @param appSecret API密钥
     * @return 签名
     */
    public static String sign(final Map<String, Object> data, String appSecret) {
        System.out.println("签名参数：" + JSONObject.toJSONString(data));
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(SING)) {
                continue;
            }
            if (data.get(k) instanceof String) {
                // 参数值为空，则不参与签名
                if (String.valueOf(data.get(k)).trim().length() > 0) {
                    sb.append(k).append("=").append(data.get(k)).append("&");
                }
            } else {
                sb.append(k).append("=").append(JSONObject.toJSONString(data.get(k))).append("&");
            }
        }
        sb.append("appSecret=").append(appSecret);
        String result = MD5(sb.toString()).toUpperCase();
        System.out.println("生成的签名为:" + result);
        return result;
    }

    private static String MD5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance(SIGN_TYPE);
            byte[] array = md.digest(data.getBytes(CHARSET));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        // {申请的appsecert}
        String appSecret = "6AE71D08884244E3B439BD58C92C4F8D";
        // 推送消息，由于需要进行字符串签名，为防止字段重新排序，将参数统一转换成JSON字符串进行数据传输
        String pushMessage = "{\"deviceNo\":\"731210115162059\"}";
        Map<String, Object> req = new HashMap<>(16);
        // {申请的appid}
        req.put("appId", "SCOMMFTLF12KC14LF");
        // 请求5分钟内有效
        req.put("timestamp", 1640151435470L);
        req.put("body", pushMessage);
        String sign = sign(req, appSecret);
        // 添加签名
        req.put("sign", sign);
        System.out.println("签名校验结果：" + check(req, appSecret));

    }

}
