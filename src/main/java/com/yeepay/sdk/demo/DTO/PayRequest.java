package com.yeepay.sdk.demo.DTO;

import com.shoufuyou.sdk.PayRequestTourist;

import java.util.ArrayList;
import java.util.Map;

public class PayRequest {
    private String merchantOrderId;
    private int periods;
    private String productId;
    private String productName;
    private String productType;
    private String productUrl;
    private int orderAmount;
    private int timeLimit;
    private int touristAdultNumber;
    private int touristKidNumber;
    private int touristBabyNumber;
    private ArrayList<PayRequestTourist> tourists = new ArrayList();
    private String departure;
    private String arrival;
    private String departureDate;
    private String returnDate;
    private int hotelClass;
    private String sourceType;
    private String extraParam;
    private String returnUrl;
    private String notifyUrl;
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private Map<String,String> loanUser;
    private String ip;

    public PayRequest() {
    }

    public void addTourist(PayRequestTourist tourist) {
        this.tourists.add(tourist);
    }

    public String getMerchantOrderId() {
        return this.merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public int getPeriods() {
        return this.periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductUrl() {
        return this.productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public int getOrderAmount() {
        return this.orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getTouristAdultNumber() {
        return this.touristAdultNumber;
    }

    public void setTouristAdultNumber(int touristAdultNumber) {
        this.touristAdultNumber = touristAdultNumber;
    }

    public int getTouristKidNumber() {
        return this.touristKidNumber;
    }

    public void setTouristKidNumber(int touristKidNumber) {
        this.touristKidNumber = touristKidNumber;
    }

    public int getTouristBabyNumber() {
        return this.touristBabyNumber;
    }

    public void setTouristBabyNumber(int touristBabyNumber) {
        this.touristBabyNumber = touristBabyNumber;
    }

    public String getDeparture() {
        return this.departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return this.arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getHotelClass() {
        return this.hotelClass;
    }

    public void setHotelClass(int hotelClass) {
        this.hotelClass = hotelClass;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getExtraParam() {
        return this.extraParam;
    }

    public void setExtraParam(String extraParam) {
        this.extraParam = extraParam;
    }

    public String getReturnUrl() {
        return this.returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public ArrayList<PayRequestTourist> getTourists() {
        return this.tourists;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Map<String, String> getLoanUser() {
        return loanUser;
    }

    public void setLoanUser(Map<String, String> loanUser) {
        this.loanUser = loanUser;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
