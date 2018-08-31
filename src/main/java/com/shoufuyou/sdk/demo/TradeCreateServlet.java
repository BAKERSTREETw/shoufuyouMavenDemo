package com.shoufuyou.sdk.demo;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoufuyou.sdk.*;

//支付
@SuppressWarnings("serial")
public class TradeCreateServlet extends HttpServlet implements javax.servlet.Servlet{ 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        SfyClient client = SfyClientFactory.createSfyClient();
        PayRequest payRequest = new PayRequest();
        payRequest.setMerchantOrderId("5789651321512316");
        payRequest.setPeriods(12);
        payRequest.setProductId("1001215");
        payRequest.setProductName("北京到东京自由行");
        payRequest.setProductType("free_tour");
        payRequest.setProductUrl("https://m.shoufuyou.com/products/1001363");
        payRequest.setOrderAmount(10000);
        payRequest.setTimeLimit(1000);
        payRequest.setDeparture("北京");
        payRequest.setArrival("东京");
        payRequest.setDepartureDate("2018-08-25");
        payRequest.setReturnDate("2016-08-28");
        payRequest.setHotelClass(4);
        payRequest.setSourceType("wap");
        payRequest.setExtraParam("测试数据");
        payRequest.setReturnUrl("http://qac36k.natappfree.cc/after-pay-return");
        payRequest.setNotifyUrl("http://qac36k.natappfree.cc/after-pay-notify");
        PayRequestTourist tourist = new PayRequestTourist();
        tourist.setName("王五");
        tourist.setNameSpelling("Wang Wu");
        tourist.setIdCardNumber("450100200007048933");
        tourist.setMobile("13226677969");
        tourist.setEmail("norepl2@gmail.com");
        payRequest.addTourist(tourist);
        payRequest.setTouristAdultNumber(1);
        payRequest.setTouristKidNumber(0);
        payRequest.setTouristBabyNumber(0);
        payRequest.setFlightNumber("MU5269");
        payRequest.setDepartureTime("2018-08-25 11:11:11");
        payRequest.setArrivalTime("2018-08-25 12:11:11");
        String s = client.buildPayRequestHtml(payRequest);
//        Map<String,String> map = client.buildPayRequestMap(payRequest);
//        client.sendTradeQueryRequest()
//        System.out.print(map);
        response.getWriter().write(s);
    }
}