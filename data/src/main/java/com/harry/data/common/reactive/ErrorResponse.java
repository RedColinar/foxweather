package com.harry.data.common.reactive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ErrorResponse extends Throwable {
    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("errors")
    private Map<String, List<String>> errors;

    @Expose
    @SerializedName("code")
    private int code;

    @Expose
    @SerializedName("ids")
    private List<String> ids;

    private String localMessage;

    public ErrorResponse(Throwable e) {
        super(e);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }

    @Override
    public String getLocalizedMessage() {
        return localMessage;
    }

    public void setLocalizedMessage(String localMessage) {
        this.localMessage = localMessage;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", errors=" + errors +
                ", code=" + code +
                ", ids=" + ids +
                ", localMessage='" + localMessage + '\'' +
                '}';
    }
}
