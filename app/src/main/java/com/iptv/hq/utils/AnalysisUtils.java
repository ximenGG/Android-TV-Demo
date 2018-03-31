package com.iptv.hq.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 　　    ()  　　  ()
 * 　　   ( ) 　　　( )
 * 　　   ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * Created by HQ on 2017/11/3.
 */
public class AnalysisUtils {
    //账号AK信息请填写(必选)
    private static String access_key_id = "LTAIJrwjSMFH9kPa";
    //账号AK信息请填写(必选)
    private static String access_key_secret = "xNi3x5BeAkR5z1Y5VOK1gcqI2BzBi8";
    //STS临时授权方式访问时该参数为必选，使用主账号AK和RAM子账号AK不需要填写
    private static String security_token = "";
    //以下参数不需要修改
    public final static String VOD_DOMAIN = "http://vod.cn-shanghai.aliyuncs.com/";
    private final static String ISO8601_DATE_FORMAT = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'";
    private final static String HTTP_METHOD = "GET";
    private final static String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    /**
     * 生成视频点播OpenAPI私有参数
     * 不同API需要修改此方法中的参数
     * @return
     */
    private static Map<String, String> generatePrivateParamters(String videoId, String action) {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<>();
        // 视频ID
        //privateParams.put("VideoId", "4976d4c5e76247018c95f25f97b13e35");
        privateParams.put("VideoId", videoId);
        // API名称
        //privateParams.put("Action", "GetPlayInfo");
        privateParams.put("Action", action);
        return privateParams;
    }

    /**
     * 生成视频点播OpenAPI公共参数
     * 不需要修改
     *
     * @return
     */
    private static Map<String, String> generatePublicParamters() {
        Map<String, String> publicParams = new HashMap<>();
        publicParams.put("Format", "JSON");
        publicParams.put("Version", "2017-03-21");
        publicParams.put("AccessKeyId", access_key_id);
        publicParams.put("SignatureMethod", "HMAC-SHA1");
        publicParams.put("Timestamp", generateTimestamp());
        publicParams.put("SignatureVersion", "1.0");
        publicParams.put("SignatureNonce", generateRandom());
        if (security_token != null && security_token.length() > 0) {
            publicParams.put("SecurityToken", security_token);
        }
        return publicParams;
    }

    /**
     * 生成OpenAPI地址
     *
     * @return
     * @throws Exception
     */
    public static String generateOpenAPIURL(String securityToken, String keySecret, String keyId, String videoId, String action) {
        security_token = securityToken;
        access_key_secret = keySecret;
        access_key_id = keyId;
        return generateURL(VOD_DOMAIN, HTTP_METHOD, generatePublicParamters(), generatePrivateParamters(videoId, action));
    }

    /**
     * 生成OpenAPI地址
     *
     * @return
     * @throws Exception
     */
    public static String generateOpenAPIURL(String videoId, String action) {
        return generateURL(VOD_DOMAIN, HTTP_METHOD, generatePublicParamters(), generatePrivateParamters(videoId, action));
    }

    /**
     * 生成OpenAPI地址
     *
     * @return
     * @throws Exception
     */
    public static String generateOpenAPIURL(String videoId) {
        return generateURL(VOD_DOMAIN, HTTP_METHOD, generatePublicParamters(), generatePrivateParamters(videoId, "GetPlayInfo"));
    }

    /**
     * @param domain        请求地址
     * @param httpMethod    HTTP请求方式GET，POST等
     * @param publicParams  公共参数
     * @param privateParams 接口的私有参数
     * @return 最后的url
     */
    private static String generateURL(String domain, String httpMethod, Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> allEncodeParams = getAllParams(publicParams, privateParams);
        String cqsString = getCQS(allEncodeParams);
        String stringToSign = httpMethod + "&" + percentEncode("/") + "&" + percentEncode(cqsString);
        String signature = hmacSHA1Signature(access_key_secret, stringToSign);
        return domain+"?" + percentEncode("Signature") + "=" + percentEncode(signature) + "&" + cqsString;
    }

    private static List<String> getAllParams(Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> encodeParams = new ArrayList<String>();
        if (publicParams != null) {
            for (String key : publicParams.keySet()) {
                String value = publicParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        if (privateParams != null) {
            for (String key : privateParams.keySet()) {
                String value = privateParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        return encodeParams;
    }

    /**
     * 参数urlEncode
     *
     * @param value
     * @return
     */
    private static String percentEncode(String value) {
        try {
            String urlEncodeOrignStr = URLEncoder.encode(value, "UTF-8");
            String plusReplaced = urlEncodeOrignStr.replace("+", "%20");
            String starReplaced = plusReplaced.replace("*", "%2A");
            String waveReplaced = starReplaced.replace("%7E", "~");
            return waveReplaced;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取CQS 的字符串
     *
     * @param allParams
     * @return
     */
    private static String getCQS(List<String> allParams) {
        ParamsComparator paramsComparator = new ParamsComparator();
        Collections.sort(allParams, paramsComparator);
        String cqString = "";
        for (int i = 0; i < allParams.size(); i++) {
            cqString += allParams.get(i);
            if (i != allParams.size() - 1) {
                cqString += "&";
            }
        }

        return cqString;
    }

    private static class ParamsComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }

    private static String hmacSHA1Signature(String accessKeySecret, String stringtoSign) {
        try {
            String key = accessKeySecret + "&";
            try {
                SecretKeySpec signKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
                Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
                mac.init(signKey);
                byte[] rawHmac = mac.doFinal(stringtoSign.getBytes());
                //按照Base64 编码规则把上面的 HMAC 值编码成字符串，即得到签名值（Signature）
                return new String(Base64Encoder.encode(rawHmac));
            } catch (Exception e) {
                throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
            }
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成随机数
     *
     * @return
     */
    private static String generateRandom() {
        String signatureNonce = UUID.randomUUID().toString();
        return signatureNonce;
    }

    /**
     * 生成当前UTC时间戳
     *
     * @return
     */
    private static String generateTimestamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    private static class Base64Encoder {
        private static final char last2byte = (char) Integer
                .parseInt("00000011", 2);
        private static final char last4byte = (char) Integer
                .parseInt("00001111", 2);
        private static final char last6byte = (char) Integer
                .parseInt("00111111", 2);
        private static final char lead6byte = (char) Integer
                .parseInt("11111100", 2);
        private static final char lead4byte = (char) Integer
                .parseInt("11110000", 2);
        private static final char lead2byte = (char) Integer
                .parseInt("11000000", 2);
        private static final char[] encodeTable = new char[]{'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
                '4', '5', '6', '7', '8', '9', '+', '/'};

        /**
         * Base64 encoding.
         *
         * @param from The src data.
         * @return
         */
        public static String encode(byte[] from) {
            StringBuffer to = new StringBuffer((int) (from.length * 1.34) + 3);
            int num = 0;
            char currentByte = 0;
            for (int i = 0; i < from.length; i++) {
                num = num % 8;
                while (num < 8) {
                    switch (num) {
                        case 0:
                            currentByte = (char) (from[i] & lead6byte);
                            currentByte = (char) (currentByte >>> 2);
                            break;
                        case 2:
                            currentByte = (char) (from[i] & last6byte);
                            break;
                        case 4:
                            currentByte = (char) (from[i] & last4byte);
                            currentByte = (char) (currentByte << 2);
                            if ((i + 1) < from.length) {
                                currentByte |= (from[i + 1] & lead2byte) >>> 6;
                            }
                            break;
                        case 6:
                            currentByte = (char) (from[i] & last2byte);
                            currentByte = (char) (currentByte << 4);
                            if ((i + 1) < from.length) {
                                currentByte |= (from[i + 1] & lead4byte) >>> 4;
                            }
                            break;
                    }
                    to.append(encodeTable[currentByte]);
                    num += 6;
                }
            }
            if (to.length() % 4 != 0) {
                for (int i = 4 - to.length() % 4; i > 0; i--) {
                    to.append("=");
                }
            }
            return to.toString();
        }
    }
}
