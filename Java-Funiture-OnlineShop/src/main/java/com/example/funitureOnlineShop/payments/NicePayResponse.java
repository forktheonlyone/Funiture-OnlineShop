package com.example.funitureOnlineShop.payments;

public class NicePayResponse {

    public static class ResponseDTO{
    // 	결제결과코드
    private String resultCode;
    // 결제결과메시지
    private String resultMsg;
    // 결제 승인 키
    private String tid;
    // 쇼핑몰 거래 고유번호
    private String orderId;
    // 결제 처리상태
    private String status;
    // 결제수단
    private String payMethod;
    // 결제 금액
    private String amount;
    // 취소 가능 잔액
    private String balanceAmt;
    // 상품명
    private String goodsName;
    }

    public static class cancelDTO{
        // 승인 취소 거래번호
        private String tid;
        // 취소금액
        private int amount;
        // 취소된 시각
        private String cancelledAt;
        // 취소사유
        private String reason;
        // 취소에 대한 매출전표 확인 URL
        private String receiptUrl;

    }
}
