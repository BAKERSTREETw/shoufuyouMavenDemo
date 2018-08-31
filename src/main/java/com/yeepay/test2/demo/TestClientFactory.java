package com.yeepay.test2.demo;

import com.shoufuyou.sdk.SfyConfig;

public class TestClientFactory {
    public static TestClient createSfyClient() {
        //创建客户端配置
        SfyConfig config = new SfyConfig();
        config.setVersion("2.0");//版本号
//        config.setMerchantCode("1000000000");//商户号
        config.setMerchantCode("3300000001");//商户号
        config.setPrivateKey("012345678901234567890123");//商户私钥
//        config.setApiUrl("http://apitest.shoufuyou.com/service");//api url
        config.setApiUrl("http://apitest2.shoufuyou.com/service");//api url
        config.setPayUrl("http://test1-pay.shoufuyou.com/v2/");//pc支付 url
        //config.setPayMobileUrl("http://test2-pay-mobile.shoufuyou.com/v2/");//mobile支付 url
        config.setPayMobileUrl("http://test1-pay-mobile.shoufuyou.com/v2/");//mobile支付 url
        TestClient client = new TestClient(config);
        return client;
    }
}
