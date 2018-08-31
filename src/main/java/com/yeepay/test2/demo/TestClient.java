package com.yeepay.test2.demo;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.shoufuyou.sdk.PayRequestTourist;
import com.shoufuyou.sdk.SfyConfig;
import com.shoufuyou.sdk.SfyException;
import com.yeepay.sdk.demo.DTO.PayRequest;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class TestClient {
    private SfyConfig config;

    public TestClient(SfyConfig config) {
        this.config = config;
    }

    //下单请求
    public Map<String,String> createMakeOrder(PayRequest payRequest){
        Map<String,Object> map = buildPayReqMap(payRequest);
        return invoke("rest.create",map);
    }

    //构造下单请求参数
    public Map<String,Object> buildPayReqMap(PayRequest payRequest){
        Map<String, Object> data = new HashMap();
        data.put("merchant_order_id",payRequest.getMerchantOrderId());
        data.put("product_type",payRequest.getProductType());
        data.put("product_name",payRequest.getProductName());
        //TODO
//        data.put("product_url",payRequest.getProductUrl());
//        data.put("product_id",payRequest.getProductId());
        data.put("product_url","https://m.shootout.com/products/1001363");
        data.put("product_id","1001215");

        data.put("order_amount",payRequest.getOrderAmount());
        data.put("periods",payRequest.getPeriods());
        data.put("time_limit",payRequest.getTimeLimit());
        data.put("tourist_adult_number",payRequest.getTouristAdultNumber());
        data.put("tourist_kid_number",payRequest.getTouristKidNumber());
        data.put("tourist_baby_number",payRequest.getTouristBabyNumber());

        //TODO
        data.put("flight_number","ca9573");

        data.put("departure",payRequest.getDeparture());
        data.put("arrival",payRequest.getArrival());
        data.put("departure_date",payRequest.getDepartureDate());
        data.put("return_date",payRequest.getReturnDate());
        data.put("departure_time",payRequest.getDepartureTime());
        data.put("arrival_time",payRequest.getArrivalTime());
//        data.put("hotel_class",payRequest);
        data.put("source_type",payRequest.getSourceType());
//        data.put("extra_param",payRequest);
        data.put("return_url",payRequest.getReturnUrl());
        data.put("notify_url",payRequest.getNotifyUrl());

        ArrayList<PayRequestTourist> list = payRequest.getTourists();
        List<Map<String,String>> lists = new ArrayList<Map<String,String>>();
        for (PayRequestTourist tourist:list) {
            HashMap<String,String> entityMap = new HashMap<String, String>();
            entityMap.put("name",tourist.getName());
            entityMap.put("id_card_number",tourist.getIdCardNumber());
            entityMap.put("mobile",tourist.getMobile());
            lists.add(entityMap);
        }
        data.put("tourists",lists);
        data.put("loan_user",payRequest.getLoanUser());
        data.put("ip",payRequest.getIp());

        return data;
    }


    //加密参数 并请求
    private Map<String, String> invoke(String method, Map<String, Object> data) {
        try {
            URL url = new URL(this.config.getApiUrl());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            StringBuilder sb = new StringBuilder();
            Map<String, String> fields = this.assembleData(method, data);
            System.out.println(fields);
//            String newBizContent = fields.get("biz_content")+"";
//            fields.put("biz_content",newBizContent);

            //TODO 造数据试试
            //TODO 临时静态数据
//            fields = new HashMap<String, String>();
//            fields.put("sign", "c219005402600f81a33a69e2ca37f896255c050db9095c1f2beba5a008bd3e9e");
//            fields.put("biz_content", "4QxIocVrXIHqRzEnA1ZLNZzUXY22fdxgbiE9ntQCJ94eZQYzhk6ZDls9sX7mIgVU9cY1ALQGqgk7lrwD2uRruuCJHQOPMcn9O53ooSnmT%2FzuRcp6SESieStJXWf9H233LDbl0SQQZToEDnM9XdUWEV0NRmUorp6wzOpgAEDLfrlL8MBBdIJ97NFHyys8vcwTIXREiDpQgZjcr8S5tUBOCRs5gHrXZrqPcGh291tgA2fWkPKqYuy%2BIoCYUwkM6WtOJpo8geyiSBdzr6sA1c4%2Fqfv06E%2BlVVRtBHw88TAYRHOynamZD0S1b6LCujT3pi9iPY5t2Ip3zqVryJ8r7atGxGe6SpT3vOZsHEzAqAYdgRoLQ9TT%2FA%2BCkEHTPbjJIygWosP1GXIJvbxbU9NfHNO6GfvNwDspMK7PyEV2yG1ed4JP%2FeJ5ho62LGsx6npkY0EJvbrfuy072%2F4m9oOOXMTSUVjw0mj8GwmAdTi4LVd7VdihOjSkE78Up0tKUB5MwA7hxzql5FkXqHoRU0O276XITVGmvwTKErKRKABPEp%2F5QdXiRx%2BixoYvGHRJ3QqNAQfcKwsatLvCmi8i4h2NB%2F8Si7yFaJln%2F%2FE3rKcUTHPOn989Rgp9IvtlU6oD0uTfqRuml07idyGJj%2FAEIoRsvoIrNi%2FY2A0vCbYFdEndCo0BB9xv%2FQM8H8hu0TvT3uITacwHUU%2F%2FnzCe1zfZ9LdsTICTEHSKxGaRoBEWbyrESsN2n08MwvmPrkdBt4KUpAH5XoInL2raF5zBKp3iHJK5ahBRipXJO8mFEz6pKe2iVeabJ9T%2BIMftNUXdzF6S0CjMeou%2BIlE27Qd8f%2BScOdngza%2BXv35wUx1wSmPr%2BKU6LT%2BKvRvv7SBuHqzocHSKxGaRoBEWX2bMuZmwzYFmDQ0zlZwdEacEYcupsXWmsKfpqkX286iDkwatO28CNjxwjMkAzCk9SC4YIVyCfoEkpeJKFXF0tx9ZrdH3AG2oSG5Y6UpcdhxnIPq3s%2FYBo8OYyZF%2FjafDCWztdwEAF1xwjq2i5rteL3%2BeNSjyn6YmSHgddYHVrxu8GJJVz%2FK3l5pI%2FOn4dGU4KZ0IUrdUlj7w06z%2BytZc2v6APkUObUyJM313qGBmI%2FVvP6z1vqInXfWwm1app2%2BCn9FAOOu%2Bu4q4RpQ63UGQt9x%2B1E4CI3b94AGO26nNxD0%3D");
//            fields.put("timestamp", "1535528416935");
//            fields.put("charset", "UTF-8");
//            fields.put("method", "rest.create");
//            fields.put("merchant_code", "3300000001");
//            fields.put("version", "2.0");

            Iterator var7 = fields.entrySet().iterator();

            while(var7.hasNext()) {
                Map.Entry<String, String> field = (Map.Entry)var7.next();
                if (sb.length() != 0) {
                    sb.append('&');
                }

                sb.append(URLEncoder.encode((String)field.getKey(), "UTF-8"));
                sb.append('=');
                sb.append(URLEncoder.encode(String.valueOf(field.getValue()), "UTF-8"));
            }

            DataOutputStream os = new DataOutputStream(connection.getOutputStream());

            try {
                os.writeBytes(sb.toString());
                os.flush();
            } finally {
                os.close();
            }

            int var25 = connection.getResponseCode();
            if (var25 != 200) {
                sb = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                try {
                    while((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                } finally {
                    reader.close();
                }
                throw new SfyException("Api 请求异常.");
            } else {
                sb = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                try {
                    while((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                } finally {
                    reader.close();
                }

                return this.disassembleData(this.convertJsonToMap(sb.toString()));
            }
        } catch (RuntimeException var22) {
            throw var22;
        } catch (Exception var23) {
            throw new RuntimeException(var23);
        }
    }


    //具体生成加密参数方法
    private Map<String, String> assembleData(String method, Map<String, Object> data) {
        HashMap<String, String> result = new HashMap();
        Date date = new Date();
        result.put("timestamp", String.valueOf(date.getTime()));
        result.put("merchant_code", this.config.getMerchantCode());
        result.put("charset", "UTF-8");
        result.put("version", this.config.getVersion());
        result.put("biz_content", this.encrypt(this.convertToJson(data), this.config.getPrivateKey()));
        result.put("method", method);
        result.put("sign", this.sign(result));
        return result;
    }

    private String encrypt(String data, String privateKey) {
        try {
            return URLEncoder.encode(Base64.encodeBase64String(this.encryptUsing3Des(data.getBytes("UTF-8"), privateKey)), "UTF-8");
        } catch (UnsupportedEncodingException var4) {
            throw new RuntimeException(var4);
        }
    }

    private String convertToJson(Object object) {
        Gson gson = (new GsonBuilder()).create();
        return gson.toJson(object);
    }

    private String sign(Map<String, String> data) {
        SortedSet<String> keys = new TreeSet(data.keySet());
        String s = "";

        String key;
        for(Iterator var4 = keys.iterator(); var4.hasNext(); s = s + (String)data.get(key)) {
            key = (String)var4.next();
        }

        s = s + this.config.getPrivateKey();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(s.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger tmp = new BigInteger(1, digest);

            String result;
            for(result = tmp.toString(16); result.length() < 64; result = "0" + result) {
                ;
            }

            return result;
        } catch (UnsupportedEncodingException var8) {
            throw new RuntimeException(var8);
        } catch (NoSuchAlgorithmException var9) {
            throw new RuntimeException(var9);
        }
    }

    private byte[] encryptUsing3Des(byte[] data, String key) {
        return this.handle3Des(1, data, key);
    }

    private byte[] handle3Des(int mode, byte[] data, String key) {
        try {
            byte[] k = key.getBytes("UTF-8");
            SecretKey sk = new SecretKeySpec(k, "DESede");
            Cipher c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c.init(mode, sk);
            return c.doFinal(data);
        } catch (RuntimeException var7) {
            throw var7;
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        }
    }
    //加密方法结束

    //解密并返回结果
    private Map<String, String> disassembleData(Map<String, String> data) {
        if (!this.checkSign(data)) {
            throw new SfyException("签名错误.");
        } else {
//            Map<String, String> result = this.convertJsonToMap(this.decrypt((String)data.get("biz_content"), this.config.getPrivateKey()));
            //TODO 第二次解析有问题  换fastJson
            String jsonString = this.decrypt((String)data.get("biz_content"), this.config.getPrivateKey());
//            Map<String, String> result = this.convertJsonToMap();
            Map<String,String> result = JSON.parseObject(jsonString,Map.class);
            if (result.containsKey("code")) {
                int code = Integer.valueOf((String)result.get("code"));
                if (code != 10000) {
                    throw new SfyException((String)result.get("message"), code, (String)result.get("merchant_order_id"));
                }
            }

            return result;
        }
    }

    private Map<String, String> convertJsonToMap(String json) {
        return (Map)(new Gson()).fromJson(json, (new TypeToken<HashMap<String, String>>() {
        }).getType());
    }

    private boolean checkSign(Map<String, String> data) {
        String sign = (String)data.get("sign");
        data.remove("sign");
        return this.sign(data).equals(sign);
    }

    private String decrypt(String data, String privateKey) {
        try {
            return new String(this.decryptUsing3Des(Base64.decodeBase64(URLDecoder.decode(data, "UTF-8")), privateKey));
        } catch (UnsupportedEncodingException var4) {
            throw new RuntimeException(var4);
        }
    }

    private byte[] decryptUsing3Des(byte[] data, String key) {
        return this.handle3Des(2, data, key);
    }

    public static void main(String[] args){
        String[] a = new String[0];
        String s = JSON.toJSONString(a);
        JsonArray google = new JsonArray();
        System.out.println("a="+a);
        System.out.println("s="+s);
        System.out.println("google="+google);
    }
}

