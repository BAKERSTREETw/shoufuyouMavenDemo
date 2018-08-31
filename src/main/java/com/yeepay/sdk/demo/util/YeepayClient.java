package com.yeepay.sdk.demo.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shoufuyou.sdk.PayRequestTourist;
import com.shoufuyou.sdk.SfyConfig;
import com.shoufuyou.sdk.SfyException;
import com.yeepay.sdk.demo.DTO.*;
import com.yeepay.sdk.demo.util.httpClient.HttpClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class YeepayClient {
    private SfyConfig config;

    public YeepayClient(SfyConfig config) {
        this.config = config;
    }

    //申请白条
    public Map<String,String> createCredit(CreditDTO dto){
        Map<String,Object> map = buildCreditRequetMap(dto);
        return invoke("credit.apply",map);
    }

    //白条额度查询
    public Map<String,String> queryCredit(String apply_id){
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("apply_id",apply_id);
        return invoke("credit.query",map);
    }

    //支付请求
    public Map<String,String> crateTrade(TradeRequestDTO request){
       Map<String,Object> map = buildPayRequestMap(request);
       return invoke("rest.payment",map);
    }

    //下单请求
    public Map<String,String> createMakeOrder(PayRequest payRequest){
        Map<String,Object> map = buildPayReqMap(payRequest);
        return invoke("rest.create",map);
    }

    //自动还款，绑卡
    public Map<String,String> buildAutoRefund(AutoRefundDTO dto){
        Map<String,Object> map = buildAutoRefundReqMap(dto);
        return invoke("rest.bind.card",map);
    }

    //交易退款
    public Map<String,String> buildTradeRefund(TradeRefundDTO dto){
        Map<String,Object> map = buildTradeRefundReqMap(dto);
        return invoke("trade.refund",map);
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

    //构造申请白条请求参数
    public Map<String,Object> buildCreditRequetMap(CreditDTO dto){
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("apply_id",dto.getApplyId());
        data.put("id_card_number",dto.getIdCardNumber());
        data.put("name",dto.getName());
        data.put("mobile",dto.getMobile());
        data.put("source_type",dto.getSourceType());
        if(dto.getExtraParam()!= null && !"".equals(dto.getExtraParam())){
            data.put("extra_param",dto.getExtraParam());
        }
        data.put("ip",dto.getIp());
        return data;
    }

    //构造支付请求参数
    public Map<String, Object> buildPayRequestMap(TradeRequestDTO request) {
        Map<String, Object> data = new HashMap<String,Object>();
        data.put("merchant_order_id", request.getMerchantOrderId());
        data.put("trade_number",request.getTradeNumber());
//        data.put("discount_id", request.getTouristAdultNumber());
//        data.put("discount_type", request.getTouristKidNumber());
        data.put("periods", request.getPeriods());
        data.put("ip", request.getIp());
//        return this.assembleData("rest.payment", data);
        //TODO 此处修改为只返回map
        return  data;
    }

    //构造自动还款，绑卡请求参数
    public Map<String,Object> buildAutoRefundReqMap(AutoRefundDTO dto){
        Map<String,Object> data = new HashMap<String, Object>();
        if(dto.getTradeNumber()!=null && !"".equals(dto.getTradeNumber())){
            data.put("trade_number",dto.getTradeNumber());
        }
        data.put("name",dto.getName());
        data.put("bank_card",dto.getBankCard());
        data.put("id_card_number",dto.getIdCardNumber());
        data.put("mobile",dto.getMobile());
        return data;
    }
    //构造交易退款请求参数
    public Map<String,Object> buildTradeRefundReqMap(TradeRefundDTO dto){
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("merchant_order_id",dto.getMerchantOrderId());
        data.put("merchant_refund_id",dto.getMerchantRefundId());
        data.put("refund_amount",dto.getRefundAmount());
        data.put("refund_reason",dto.getRefundReason());
        return data;
    }

    private Map<String, String> invoke(String method, Map<String, Object> data) {
        HttpClient httpClient = new HttpClient();
        try {

            Map<String, String> fields = this.assembleData(method, data);
            //TODO MasaPay Post请求
            Set<Map.Entry<String,String>> entrySet = fields.entrySet();
            Iterator iterator = entrySet.iterator();
            ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
            while (iterator.hasNext()){

                Map.Entry entry = (Map.Entry) iterator.next();
//                if("sign".equals(entry.getKey().toString()) || "biz_content".equals(entry.getKey().toString()) || "timestamp".equals(entry.getKey().toString())){
//                    System.out.println("nvps    "+entry.getKey().toString()+"="+entry.getValue().toString());
//                }
                nvps.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
            }

            String responseBody = httpClient.HttpPost(nvps, this.config.getPayUrl());

                return this.disassembleData(this.convertJsonToMap(responseBody));
//            }
        } catch (RuntimeException var22) {
            throw var22;
        } catch (Exception var23) {
            throw new RuntimeException(var23);
        }
    }


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

    private byte[] encryptUsing3Des(byte[] data, String key) {
        return this.handle3Des(1, data, key);
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

    private Map<String, String> disassembleData(Map<String, String> data) {
        if (!this.checkSign(data)) {
            throw new SfyException("签名错误.");
        } else {
            //TODO demo解析有问题  暂时使用fastJson
//            Map<String, String> result = this.convertJsonToMap(this.decrypt((String)data.get("biz_content"), this.config.getPrivateKey()));
            String jsonString = this.decrypt((String)data.get("biz_content"), this.config.getPrivateKey());
            //TODO 这有问题  code 有时候是String 有时候是 Integer
            Map<String, String> result = JSON.parseObject(jsonString,Map.class);
            if (result.containsKey("code")) {
                int code = Integer.valueOf((String)result.get("code"));
                if (code != 10000) {
                    throw new SfyException((String)result.get("message"), code, (String)result.get("merchant_order_id"));
                }
            }

            return result;
        }
    }

    private boolean checkSign(Map<String, String> data) {
        String sign = (String)data.get("sign");
        data.remove("sign");
        return this.sign(data).equals(sign);
    }

    private Map<String, String> convertJsonToMap(String json) {
        return (Map)(new Gson()).fromJson(json, (new TypeToken<HashMap<String, String>>() {
        }).getType());
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

    //---------------------------------------分割线------------------------------------------

}
