package com.yeepay.sdk.demo.DTO;

public class TradeRefundDTO {
    private String merchantOrderId;//商户订单号
    private String merchantRefundId;//退款流水号
    private int refundAmount;//退款金额
    private String refundReason;//退款理由

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public String getMerchantRefundId() {
        return merchantRefundId;
    }

    public void setMerchantRefundId(String merchantRefundId) {
        this.merchantRefundId = merchantRefundId;
    }

    public int getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }
}
