package com.yeepay.sdk.demo.util;

import com.shoufuyou.sdk.SfyConfig;

public class YeepayClientFactory {
    public static YeepayClient createSfyClient() {
        //创建客户端配置
        SfyConfig config = new SfyConfig();
        config.setVersion("2.0");//版本号
//        config.setMerchantCode("1000000000");//商户号
//        config.setMerchantCode("3300000001");//商户号
//        config.setMerchantCode("90301000011");//商户号
        config.setMerchantCode("1000000000");//商户号
//        config.setPrivateKey("012345678901234567890123");//商户私钥
//        config.setPrivateKey("012345678901234567890123");//商户私钥
//        config.setPrivateKey("012345678901234567890123");//商户私钥
        config.setPrivateKey("012345678901234567890123");//商户私钥
        config.setApiUrl("http://apitest.shoufuyou.com/service");//api url
//        config.setPayUrl("http://test2-pay.shoufuyou.com/v2/");//pc支付 url
        config.setPayUrl("http://apitest2.shoufuyou.com/service");//pc支付 url
//        config.setPayUrl("http://apitest.shoufuyou.com/service");//pc支付 url
        //config.setPayMobileUrl("http://test2-pay-mobile.shoufuyou.com/v2/");//mobile支付 url
        config.setPayMobileUrl("http://test2-pay-mobile.shoufuyou.com/v2/");//mobile支付 url
        YeepayClient client = new YeepayClient(config);
        return client;
    }
}
