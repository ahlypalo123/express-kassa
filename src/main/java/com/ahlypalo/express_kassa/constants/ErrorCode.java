package com.ahlypalo.express_kassa.constants;

public interface ErrorCode {
    int UNKNOWN_ERROR = 0;
    int USER_NOT_FOUND = 1;
    int VERIFICATION_CODE_ALREADY_SENT = 2;
    int INVALID_VERIFICATION_CODE = 3;
    int MERCHANT_WITH_THIS_EMAIL_EXISTS = 4;
    int MERCHANT_NOT_FOUND = 5;
    int MERCHANT_DETAILS_NOT_FOUND = 6;
    int RECEIPT_NOT_FOUND = 7;
    int YOU_HAVE_OPENED_SHIFT = 8;
    int YOU_DONT_HAVE_SHIFT = 9;
}
