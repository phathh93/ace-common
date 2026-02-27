package com.ace.payload;

import com.ace.AceStatusCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.util.StringUtils;

@JsonPropertyOrder({ "success", "code", "status", "message", "data" })
public class AceResponsePayload<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("success")
    @Deprecated
    private Boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("status")
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data")
    private T data;

    protected AceResponsePayload() {
    }

    public static <T> AceResponsePayload<T> success() {
        AceResponsePayload<T> _res = new AceResponsePayload<T>();
        _res.code = AceStatusCode.SUCCESS.value();
        _res.status = AceStatusCode.SUCCESS.name();
        _res.success = true;
        return _res;
    }

    public static <T> AceResponsePayload<T> success(final String message) {
        AceResponsePayload<T> _res = new AceResponsePayload<T>();
        _res.code = AceStatusCode.SUCCESS.value();
        _res.status = AceStatusCode.SUCCESS.name();
        _res.message = StringUtils.hasText(message) ? message : AceStatusCode.SUCCESS.getMessage();
        return _res;
    }

    public static <T> AceResponsePayload<T> success(final T data) {
        AceResponsePayload<T> _res = new AceResponsePayload<T>();
        _res.code = AceStatusCode.SUCCESS.value();
        _res.status = AceStatusCode.SUCCESS.name();
        _res.success = true;
        _res.data = data;
        return _res;
    }

    public static <T> AceResponsePayload<T> success(final String message, final T data) {
        AceResponsePayload<T> _res = new AceResponsePayload<T>();
        _res.code = AceStatusCode.SUCCESS.value();
        _res.status = AceStatusCode.SUCCESS.name();
        _res.message = StringUtils.hasText(message) ? message : AceStatusCode.SUCCESS.getMessage();
        _res.data = data;
        _res.success = true;
        return _res;
    }

    public static <T> AceResponsePayload<T> fail(AceStatusCode statusCode) {
        AceResponsePayload<T> _res = new AceResponsePayload<T>();
        _res.code = statusCode.value();
        _res.status = statusCode.name();
        _res.success = false;
        _res.message = statusCode.getMessage();
        return _res;
    }

    public static <T> AceResponsePayload<T> fail(AceStatusCode statusCode, String message) {
        AceResponsePayload<T> _res = new AceResponsePayload<T>();
        _res.code = statusCode.value();
        _res.status = statusCode.name();
        _res.success = false;
        _res.message = StringUtils.hasText(message) ? message : statusCode.getMessage();
        return _res;
    }

    @Deprecated
    public Boolean getSuccess() {
        return success;
    }

    @Deprecated
    public AceResponsePayload<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AceResponsePayload<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public AceResponsePayload<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public AceResponsePayload<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public T getData() {
        return data;
    }

    public AceResponsePayload<T> setData(T data) {
        this.data = data;
        return this;
    }
}
