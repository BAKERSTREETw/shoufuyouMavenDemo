package com.yeepay.test2.demo;

import com.shoufuyou.sdk.PayRequestTourist;
import com.yeepay.sdk.demo.DTO.PayRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateOrderServlet extends HttpServlet implements javax.servlet.Servlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TestClient client = TestClientFactory.createSfyClient();
        PayRequest payReq = new PayRequest();
        payReq.setMerchantOrderId("5789651321512316");//商户订单号
        payReq.setProductType("flight");//产品类型
        payReq.setProductName("北京到东京五天三晚自由行");//产品名称
//        payReq.setProductUrl("");//产品url
//        payReq.setProductId("");//产品id
        payReq.setOrderAmount(1000);//单位为分
        payReq.setPeriods(12);//分期数
        payReq.setTimeLimit(60);//订单有效时长 30分钟
        payReq.setTouristAdultNumber(1);//大于12周岁
        payReq.setTouristKidNumber(0);//儿童出行人数
        payReq.setTouristBabyNumber(0);//婴儿人数
        //payReq.setFlightNumber();//航班号
        payReq.setDeparture("北京");//出发城市
        payReq.setArrival("东京");//目的地城市
        payReq.setDepartureDate("2018-08-27");//出发日期
        payReq.setReturnDate("2018-08-30");//返程日期
        payReq.setDepartureTime("2018-08-27 09:30:01");//出发时间
        payReq.setArrivalTime("2018-08-27 14:30:01");//到达时间
        //payReq.setHotelClass(4);//酒店星级
        payReq.setSourceType("wap");//订单来源类型
        //payReq.setExtraParam("");//额外参数
        PayRequestTourist tourist = new PayRequestTourist();
        tourist.setName("王五");
//        tourist.setNameSpelling("Wang Wu");
        tourist.setIdCardNumber("450100200007048933");
        tourist.setMobile("13226677969");
        tourist.setEmail("norepl2@gmail.com");
        payReq.addTourist(tourist);//出行人列表
        //TODO
        Map<String,String> tourists = new HashMap<String, String>();
        tourists.put("name","");
        tourists.put("id_card_number","");
        tourists.put("mobile","");
        tourists.put("email","");
        payReq.setLoanUser(tourists);
//        payReq.setLoanUser("{\"name\":\"王五\",\"id_card_number\":\"450100200007048933\",\"mobile\":\"13226677969\",\"email\":\"norepl2@gmail.com\"}");
        payReq.setIp("127.0.0.1");
        payReq.setReturnUrl("http://sbi68c.natappfree.cc/create-order-return");
        payReq.setNotifyUrl("http://sbi68c.natappfree.cc/create-order-notify");

        Map<String,String> map = client.createMakeOrder(payReq);

        System.out.println(map);
    }
}
