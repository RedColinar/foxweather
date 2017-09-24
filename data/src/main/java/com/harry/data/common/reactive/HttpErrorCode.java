package com.harry.data.common.reactive;

import com.harry.data.common.exception.TimeErrorException;

public enum HttpErrorCode {
    WechatUnregistered(4011), TimeDifferent(4012), Unknown(9999);

    int errorCode;

    HttpErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getValue() {
        return errorCode;
    }

    public static HttpErrorCode valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        switch (value) {
            case 4011:
                return WechatUnregistered;
            case 4012:
                return TimeDifferent;
            default:
                return Unknown;
        }
    }

    public static void processError(ErrorResponse errorResponse) {
        if (errorResponse == null) {
            return;
        }

        switch (valueOf(errorResponse.getCode())) {
            case TimeDifferent:
                throw new TimeErrorException(errorResponse);
            case WechatUnregistered:
                // FIXME Auth 之后出现 WechatUnregistered 怎么处理 (Auth后账号被删掉)
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        switch (errorCode) {
            case 4011:
                return "4011(WechatUnregistered)";
            case 4012:
                return "4012(TimeDifferent)";
            default:
                return "9999(Unknown)";
        }
    }
}
