package com.harry.data.common.reactive;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.Log;

import com.augmentum.scrm.assistant.common.utility.StrUtil;
import com.augmentum.scrm.assistant.data.R;
import com.google.gson.Gson;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;

import okhttp3.Request;
import retrofit2.HttpException;
import rx.Subscriber;

public abstract class HttpSubscriber<T> extends Subscriber<T> {
    private static final String ERROR_PRIFIX = "error_msg_";
    private static final String REQUEST_POST = "POST";
    private static final String PACKAGE_NAME = "com.augmentum.scrm.assistant.data";
    private static final String STRING = "string";

    Context context;

    public HttpSubscriber(Context context) {
        this.context = context;
    }

    public abstract void onError(ErrorResponse errorResponse);

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();

        ErrorResponse errorResponse;
        if (e instanceof HttpException) {
            try {
                Gson gson = new Gson();
                HttpException httpException = (HttpException) e;
                Request request = (Request) httpException.response().raw().request().tag();
                String method = request.method();
                String encodedPath = request.url().encodedPath() == null ? "" : request.url().encodedPath();
                Log.e(method, encodedPath);
                String errorBodyString = new String(httpException.response().errorBody().bytes());
                errorResponse = gson.fromJson(errorBodyString, ErrorResponse.class);
                Log.e("error string", errorBodyString);

                Map<String, List<String>> errors = errorResponse.getErrors();

                if (errors == null || errors.isEmpty()) {
                    processErrorResponseCode(errorResponse, null, null);
                } else {
                    List<Map.Entry<String, List<String>>> list = new ArrayList<>(errors.entrySet());
                    processErrorResponseCode(errorResponse, list.get(0).getKey(), list.get(0).getValue().get(0));
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                errorResponse = new ErrorResponse(e1);
                errorResponse.setLocalizedMessage(getErrorString(R.string.error_msg_unknown_error));
            }
        } else if (e instanceof SocketTimeoutException) {
            errorResponse = new ErrorResponse(e);
            errorResponse.setLocalizedMessage(getErrorString(R.string.error_msg_unknown_host));
        } else if (e instanceof UnknownHostException || e instanceof SocketException || e instanceof SSLException) {
            errorResponse = new ErrorResponse(e);
            errorResponse.setLocalizedMessage(getErrorString(R.string.error_msg_socket_time_out));
        } else {
            errorResponse = new ErrorResponse(e);
            errorResponse.setLocalizedMessage(getErrorString(R.string.error_msg_unknown_error));
        }
        onError(errorResponse);
    }

    private void processErrorResponseCode(ErrorResponse errorResponse, String key, String value) {
        StringBuilder errorContent = new StringBuilder(StrUtil.EMPTY);

        if (errorResponse.getCode() > 0) {
            switch (HttpErrorCode.valueOf(errorResponse.getCode())) {
                // 4011 - 微信账号未添加
                case WechatUnregistered:
                    errorContent.append(getErrorString(R.string.error_msg_wechat_id_not_register));
                    break;
                case TimeDifferent:
                    errorContent.append(getErrorString(R.string.error_msg_time_error));
                    break;
                default:
                    break;
            }
            errorResponse.setLocalizedMessage(errorContent.toString());
            return;
        }

        if (key == null || key.isEmpty()) {
            errorContent.append(errorResponse.getMessage());
        } else {
            try {
                String param = getErrorKeyString(key);
                errorContent.append(param).append(StrUtil.COLON).append(value);
            } catch (Exception e) {
                errorContent.append(key).append(StrUtil.COLON).append(value);
            }
        }
        errorResponse.setLocalizedMessage(errorContent.toString());
    }

    private String getErrorKeyString(String key) {
        int rid = context.getResources().getIdentifier(key + ERROR_PRIFIX, STRING, PACKAGE_NAME);

        return context.getString(rid);
    }

    private String getErrorString(String key) {
        int rid = context.getResources().getIdentifier(key, STRING, PACKAGE_NAME);

        return context.getString(rid);
    }

    private String getErrorString(@StringRes int rid) {
        return context.getString(rid);
    }

}
