package com.ace;

/**
 * Constants for error message keys used across the application
 */
public final class MessageKeys {

    private MessageKeys() {
        // Utility class
    }

    // ============================================
    // AUTHENTICATION & AUTHORIZATION
    // ============================================
    public static final String KEY_UNAUTHORIZED = "KEY_UNAUTHORIZED";
    public static final String KEY_ACCESS_DENIED = "KEY_ACCESS_DENIED";
    public static final String KEY_TOKEN_INVALID = "KEY_TOKEN_INVALID";
    public static final String KEY_TOKEN_EXPIRED = "KEY_TOKEN_EXPIRED";
    public static final String KEY_TOKEN_REVOKED = "KEY_TOKEN_REVOKED";
    public static final String KEY_CLIENT_INVALID = "KEY_CLIENT_INVALID";
    public static final String KEY_USER_INVALID = "KEY_USER_INVALID";
    public static final String KEY_NO_TOKEN = "KEY_NO_TOKEN";
    public static final String KEY_AUTHENTICATION_FAILED = "KEY_AUTHENTICATION_FAILED";

    // ============================================
    // VALIDATION & PARAMETERS
    // ============================================
    public static final String KEY_INVALID_PARAMETER = "KEY_INVALID_PARAMETER";
    public static final String KEY_VALIDATION_ERROR = "KEY_VALIDATION_ERROR";

    // ============================================
    // DATA & DATABASE
    // ============================================
    public static final String KEY_DATA_CONFLICT = "KEY_DATA_CONFLICT";
    public static final String KEY_DATA_CHANGED_PLEASE_REFRESH = "KEY_DATA_CHANGED_PLEASE_REFRESH";
    public static final String KEY_RECORD_NOT_FOUND = "KEY_RECORD_NOT_FOUND";
    public static final String KEY_RECORD_DELETED = "KEY_RECORD_DELETED";
    public static final String KEY_DUPLICATE_DATA_PLEASE_RETRY = "KEY_DUPLICATE_DATA_PLEASE_RETRY";
    public static final String KEY_DATA_ERROR = "KEY_DATA_ERROR";
    public static final String KEY_TRANSACTION_FAILED = "KEY_TRANSACTION_FAILED";

    // ============================================
    // SERVER & SYSTEM
    // ============================================
    public static final String KEY_SERVER_BUSY_PLEASE_RETRY = "KEY_SERVER_BUSY_PLEASE_RETRY";
    public static final String KEY_REQUEST_TIMEOUT = "KEY_REQUEST_TIMEOUT";
    public static final String KEY_INTERNAL_ERROR = "KEY_INTERNAL_ERROR";

    // ============================================
    // WEBSOCKET
    // ============================================
    public static final String KEY_SESSION_INVALID = "KEY_SESSION_INVALID";
}
