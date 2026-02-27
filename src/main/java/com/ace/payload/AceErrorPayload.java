package com.ace.payload;

import com.ace.AceStatusCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@JsonPropertyOrder({ "success", "path", "timestamp", "code", "status", "message", "trace" })
@Data
public class AceErrorPayload {

    @JsonIgnore
    // @JsonInclude(JsonInclude.Include.NON_NULL)
    // @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("code")
    private int code;

    @JsonProperty("status")
    private String status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("message")
    private String message;

    @JsonIgnore
    // @JsonInclude(JsonInclude.Include.NON_NULL)
    // @JsonProperty("path")
    private String path;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("trace")
    private String trace;

    @JsonIgnore
    private AceStatusCode statusCode;

    @JsonIgnore
    private WebRequest request;

    private AceErrorPayload() {

    }

    public AceErrorPayload(AceStatusCode status) {
        this();
        this.statusCode = status;
        this.code = status.value();
        this.status = status.name();
    }

    public AceErrorPayload(AceStatusCode status, Throwable ex) {
        this(status);
        this.trace = convertStackTraceToString(ex);
    }

    public AceErrorPayload(AceStatusCode status, String message, Throwable ex) {
        this(status, ex);
        this.message = message;
    }

    @JsonProperty("success")
    @Deprecated
    public boolean getSuccess() {
        return false;
    }

    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return statusCode.getHttpStatus();
    }

    public void setRequest(WebRequest request) {
        this.request = request;
        try {
            if (request != null) {
                final ServletWebRequest _req = ((ServletWebRequest) request);
                final String path = _req.getRequest().getRequestURI();
                final String query = _req.getRequest().getQueryString();
                this.path = path;
                if (!StringUtils.isEmpty(query))
                    this.path = path + "?" + query;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String convertStackTraceToString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}