package com.ace;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * Enumeration of API status codes.
 *
 * <p>
 * The API status code series can be retrieved via {@link #value()}.
 *
 * @author Phuoc Chau
 * @since 1.0
 */
public enum AceStatusCode {

    SUCCESS(0),
    FAILED(1, HttpStatus.BAD_REQUEST),

    // < 19999: SYSTEM or GENERIC
    BAD_REQUEST(10400, HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(10401, HttpStatus.UNAUTHORIZED),
    PAYMENT_REQUIRED(10402, HttpStatus.PAYMENT_REQUIRED),
    FORBIDDEN(10403, HttpStatus.FORBIDDEN),
    NOT_FOUND(10404, HttpStatus.NOT_FOUND),
    TOO_MANY_REQUESTS(10429, HttpStatus.TOO_MANY_REQUESTS),

    INTERNAL_SERVER_ERROR(10500, HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_IMPLEMENTED(10501, HttpStatus.NOT_IMPLEMENTED),
    BAD_GATEWAY(10502, HttpStatus.BAD_GATEWAY),
    SERVICE_UNAVAILABLE(10503, HttpStatus.SERVICE_UNAVAILABLE),
    GATEWAY_TIMEOUT(10504, HttpStatus.GATEWAY_TIMEOUT),
    SYSTEM_MAINTAIN(10505, HttpStatus.BAD_REQUEST),
    SETTINGS_NOT_FOUND(10506, HttpStatus.BAD_REQUEST),

    // 2xxxx: AGENT, USER, PROFILE
    AGENT_NOT_FOUND(20000, HttpStatus.BAD_REQUEST),
    AGENT_BLOCKED(20001, HttpStatus.FORBIDDEN),
    AGENT_INACTIVE(20002, HttpStatus.FORBIDDEN),
    AGENT_SUSPENDED(20003, HttpStatus.FORBIDDEN),
    AGENT_DELETED(20004, HttpStatus.FORBIDDEN),
    AGENT_NOT_ENOUGH_BALANCE(20005, HttpStatus.INTERNAL_SERVER_ERROR),
    OAUTH_CLIENT_NOT_FOUND(20006, HttpStatus.NOT_FOUND),

    USER_NOT_FOUND(21001, HttpStatus.NOT_FOUND),
    USER_BLOCKED(21002, HttpStatus.FORBIDDEN),
    USER_INACTIVE(21003, HttpStatus.FORBIDDEN),
    USER_SUSPENDED(21004, HttpStatus.FORBIDDEN),
    USER_DELETED(21005, HttpStatus.FORBIDDEN),
    USER_INVALID_OLD_PASSWORD(21006, HttpStatus.BAD_REQUEST),
    USER_INVALID_NEW_PASSWORD(21007, HttpStatus.BAD_REQUEST),
    USER_INVALID_PASSWORD(21008, HttpStatus.BAD_REQUEST),

    USER_REGISTER_ERROR(21201, HttpStatus.BAD_REQUEST),
    USER_USERNAME_EXISTED(21202, HttpStatus.BAD_REQUEST),
    USER_EMAIL_EXISTED(21203, HttpStatus.BAD_REQUEST),
    USER_RESET_PASSWORD_ERROR(21204, HttpStatus.BAD_REQUEST),
    USER_RESET_PASSWORD_EXPIRED(21205, HttpStatus.BAD_REQUEST),

    USER_VERIFY_ERROR(21206, HttpStatus.BAD_REQUEST),
    USER_OTP_EXPIRED(21207, HttpStatus.BAD_REQUEST),
    USER_OTP_NOT_FOUND(21208, HttpStatus.BAD_REQUEST),
    USER_OTP_REVOKED(21209, HttpStatus.BAD_REQUEST),
    USER_OTP_USED(21210, HttpStatus.BAD_REQUEST),
    USER_OTP_NOT_VALID(21211, HttpStatus.BAD_REQUEST),
    USER_OTP_ACTION_NOT_VALID(21211, HttpStatus.BAD_REQUEST),
    USER_OTP_REACH_MAX_RETRY(21212, HttpStatus.BAD_REQUEST),
    EMAIL_SEND_FAILED(21213, HttpStatus.INTERNAL_SERVER_ERROR),

    USER_CONTACT_EXISTED(21209, HttpStatus.BAD_REQUEST),
    USER_CONTACT_NOT_EXISTED(21210, HttpStatus.BAD_REQUEST),
    USER_INVENTORY_NOT_FOUND(21211, HttpStatus.BAD_REQUEST),

    USER_FB_ACCOUNT_EXISTED(21212, HttpStatus.BAD_REQUEST),
    USER_FB_ACCOUNT_NOT_EXISTED(21213, HttpStatus.BAD_REQUEST),
    USER_ACE_ACCOUNT_NOT_EXISTED(21214, HttpStatus.BAD_REQUEST),
    USER_LOGIN_NOT_FOUND(21215, HttpStatus.BAD_REQUEST),
    USER_BALANCE_NOT_ENOUGH(21216, HttpStatus.BAD_REQUEST),
    USER_ACE_ACCOUNT_EXISTED(21217, HttpStatus.BAD_REQUEST),
    USER_GG_ACCOUNT_EXISTED(21218, HttpStatus.BAD_REQUEST),
    USER_GG_ACCOUNT_NOT_EXISTED(21219, HttpStatus.BAD_REQUEST),
    USER_NEED_AT_LEAST_ONE_LOGIN_METHOD(21220, HttpStatus.BAD_REQUEST),
    USER_ANDROID_ACCOUNT_EXISTED(21221, HttpStatus.BAD_REQUEST),
    USER_ANDROID_ACCOUNT_NOT_EXISTED(21222, HttpStatus.BAD_REQUEST),
    USER_TWITTER_ACCOUNT_EXISTED(21223, HttpStatus.BAD_REQUEST),
    USER_TWITTER_ACCOUNT_NOT_EXISTED(21224, HttpStatus.BAD_REQUEST),
    USER_APPLE_ACCOUNT_EXISTED(21225, HttpStatus.BAD_REQUEST),
    USER_APPLE_ACCOUNT_NOT_EXISTED(21226, HttpStatus.BAD_REQUEST),
    USER_iOS_ACCOUNT_EXISTED(21227, HttpStatus.BAD_REQUEST),
    USER_iOS_ACCOUNT_NOT_EXISTED(21228, HttpStatus.BAD_REQUEST),
    USER_GAME_PERMISSION(21229, HttpStatus.BAD_REQUEST),

    // Account Deletion
    DELETION_ACCOUNT_NOT_FOUND(21301, HttpStatus.NOT_FOUND),
    DELETION_REQUEST_EXISTING(21302, HttpStatus.CONFLICT),
    DELETION_REQUEST_NOT_FOUND(21303, HttpStatus.NOT_FOUND),
    DELETION_CANNOT_CANCEL(21304, HttpStatus.BAD_REQUEST),
    DELETION_GRACE_PERIOD_EXPIRED(21305, HttpStatus.BAD_REQUEST),
    DELETION_INVALID_ACCOUNT_TYPE(21306, HttpStatus.BAD_REQUEST),
    DELETION_INVALID_REASON(21307, HttpStatus.BAD_REQUEST),
    DELETION_INVALID_SEARCH_TYPE(21308, HttpStatus.BAD_REQUEST),

    // Account Deletion - OTP Security
    DELETION_INVALID_OTP(21309, HttpStatus.BAD_REQUEST),
    DELETION_OTP_EXPIRED(21310, HttpStatus.BAD_REQUEST),
    DELETION_RATE_LIMIT_EXCEEDED(21311, HttpStatus.TOO_MANY_REQUESTS),
    DELETION_NO_EMAIL_ASSOCIATED(21312, HttpStatus.BAD_REQUEST),
    DELETION_INVALID_PASSWORD(21313, HttpStatus.UNAUTHORIZED),
    DELETION_PASSWORD_REQUIRED(21314, HttpStatus.BAD_REQUEST),

    //
    PROFILE_NOT_FOUND(22000, HttpStatus.NOT_FOUND),

    // 3xxxx: WALLET
    WALLET_NOT_FOUND(30000, HttpStatus.BAD_REQUEST),
    WALLET_INSUFFICIENT_ERROR(30001, HttpStatus.BAD_REQUEST),
    WALLET_CURRENCY_NOT_FOUND(30002, HttpStatus.BAD_REQUEST),
    WALLET_TYPE_NOT_FOUND(30003, HttpStatus.BAD_REQUEST),
    WALLET_AGENT_NOT_FOUND(30004, HttpStatus.BAD_REQUEST),

    // 5xxxx repository
    TRANSACTION_SUCCESS(50000),
    TRANSACTION_PENDING(50001),
    TRANSACTION_ERROR(50002),
    TRANSACTION_EXCHANGE_NOT_FOUND(50003),
    TRANSACTION_SENDER_WALLET_NOT_FOUND(50004),
    TRANSACTION_RECEIVER_WALLET_NOT_FOUND(50005),
    TRANSACTION_SENDER_BALANCE_NOT_ENOUGH(50006),
    TRANSACTION_AMOUNT_NOT_VALID(50007),
    TRANSACTION_FEE_NOT_FOUND(50008),
    TRANSACTION_TYPE_NOT_FOUND(50009),
    TRANSACTION_CANCEL(50010),
    TRANSACTION_CREATE_RECEIPT_FAILED(50011),

    // 6xxxx payment
    PAYMENT_PROVIDER_NOT_FOUND(61000, HttpStatus.BAD_REQUEST),
    PAYMENT_DEPOSIT_UNKNOWN_ERROR(61001, HttpStatus.BAD_REQUEST),
    PAYMENT_DEPOSIT_IAP_PARAM_NOT_VALID(61002, HttpStatus.BAD_REQUEST),
    PAYMENT_DEPOSIT_IAP_STORE_NOT_VALID(61003, HttpStatus.BAD_REQUEST),
    PAYMENT_DEPOSIT_IAP_PRODUCT_NOT_VALID(61004, HttpStatus.BAD_REQUEST),
    PAYMENT_DEPOSIT_IAP_ORDER_NOT_VALID(61005, HttpStatus.BAD_REQUEST),
    PAYMENT_DEPOSIT_IAP_ORDER_EXISTED(61006, HttpStatus.BAD_REQUEST),
    PAYMENT_DEPOSIT_IAP_VERIFY_ERROR(61007, HttpStatus.BAD_REQUEST),
    PAYMENT_VOUCHER_INVALID(61008, HttpStatus.BAD_REQUEST),
    PAYMENT_VOUCHER_USED(61008, HttpStatus.BAD_REQUEST),
    PAYMENT_IDTC_TOKEN_ERROR(61009, HttpStatus.INTERNAL_SERVER_ERROR),
    PAYMENT_IDTC_QRIS_ERROR(61010, HttpStatus.INTERNAL_SERVER_ERROR),
    PAYMENT_AMOUNT_MISMATCH(61011, HttpStatus.BAD_REQUEST),
    PAYMENT_RECEIPT_NOT_FOUND(61012, HttpStatus.NOT_FOUND),
    PAYMENT_CRYPTO_ERROR(61013, HttpStatus.BAD_REQUEST),
    PAYMENT_RECEIPT_TYPE_NOT_MATCH(61014, HttpStatus.BAD_REQUEST),
    WITHDRAWAL_AMOUNT_NOT_IN_RANGE(61015, HttpStatus.BAD_REQUEST),
    PAYMENT_GP_ERROR(61016, HttpStatus.INTERNAL_SERVER_ERROR),
    PAYMENT_RECEIPT_PROVIDER_NOT_MATCH(61017, HttpStatus.BAD_REQUEST),
    PAYMENT_AMOUNT_INVALID(61018, HttpStatus.BAD_REQUEST),
    PAYMENT_SUB_AMOUNT_NOT_VALID(61019, HttpStatus.INTERNAL_SERVER_ERROR),
    PAYMENT_ADD_AMOUNT_NOT_VALID(61020, HttpStatus.INTERNAL_SERVER_ERROR),
    PAYMENT_FEE_AMOUNT_NOT_VALID(61021, HttpStatus.INTERNAL_SERVER_ERROR),
    PAYMENT_FEE_NOT_FOUND(61022, HttpStatus.INTERNAL_SERVER_ERROR),
    PAYMENT_PROVIDER_NOT_SUPPORTED(61023, HttpStatus.BAD_REQUEST),
    PAYMENT_RECEIPT_STATUS_NOT_MATCH(61024, HttpStatus.BAD_REQUEST),
    PAYMENT_AMOUNT_NOT_IN_RANGE(61025, HttpStatus.BAD_REQUEST),
    PAYMENT_METHOD_IS_CLOSED(61026, HttpStatus.BAD_REQUEST),
    PAYMENT_METHOD_MANUAL_IS_CLOSED(61027, HttpStatus.BAD_REQUEST),
    PAYMENT_SEC2PAY_ERROR(61028, HttpStatus.INTERNAL_SERVER_ERROR),
    PAYMENT_DEPOSIT_IAP_PRODUCT_NOT_FOUND(61029, HttpStatus.NOT_FOUND),
    // 7xxxx Service code
    GIFT_CODE_ERROR(70000, HttpStatus.BAD_REQUEST),
    GIFT_CODE_ALREADY_USED(70001, HttpStatus.BAD_REQUEST),
    GIFT_CODE_NOT_FOUND(70002, HttpStatus.NOT_FOUND),
    GIFT_CODE_EXPIRED(70003, HttpStatus.BAD_REQUEST),
    GIFT_CODE_DUPLICATE(70004, HttpStatus.BAD_REQUEST),

    LUCKY_SPIN_ERROR(72004, HttpStatus.BAD_REQUEST),
    LUCKY_SPIN_INVALID(72005, HttpStatus.BAD_REQUEST),
    LUCKY_SPIN_CONFIG_INVALID(72006, HttpStatus.BAD_REQUEST),
    LUCKY_SPIN_OUT_OF_SPINS(72007, HttpStatus.BAD_REQUEST),
    LUCKY_SPIN_RESET_NOT_FOUND(72008, HttpStatus.BAD_REQUEST),
    LUCKY_SPIN_CONFIG_NOT_FOUND(72009, HttpStatus.BAD_REQUEST),
    LUCKY_TICKET_NOT_FOUND(72010, HttpStatus.BAD_REQUEST),
    LUCKY_SPIN_OUT_OF_TICKETS(720011, HttpStatus.BAD_REQUEST),
    GAME_EVENT_NOT_FOUND(720012, HttpStatus.BAD_REQUEST),

    // message
    MESSAGE_NOT_FOUND(74001, HttpStatus.BAD_REQUEST),
    MESSAGE_CLAIM_FAIL(74002, HttpStatus.BAD_REQUEST),
    MESSAGE_PRESENT_NOT_FOUND(74003, HttpStatus.BAD_REQUEST),
    MESSAGE_REMOVE_FAIL(74004, HttpStatus.BAD_REQUEST),

    // 9xxx Referral CODE
    REFERRAL_ERROR(76000, HttpStatus.BAD_REQUEST),
    REFERRAL_CODE_NOT_FOUND(76001, HttpStatus.BAD_REQUEST),
    REFERRAL_CODE_NOT_EXISTED(76002, HttpStatus.BAD_REQUEST),
    REFERRAL_CODE_INVALID(76003, HttpStatus.BAD_REQUEST),
    REFERRAL_CODE_EXISTED(76004, HttpStatus.BAD_REQUEST),
    REFERRAL_CODE_DECLINE(76005, HttpStatus.BAD_REQUEST),
    REFERRAL_SEND_GIFT_ERROR(76006, HttpStatus.BAD_REQUEST),
    REFERRAL_ALREADY(76007, HttpStatus.BAD_REQUEST),

    PROMO_NOT_FOUND(80001, HttpStatus.NOT_FOUND),

    //
    CONFIG_NOT_FOUND(91000, HttpStatus.OK),
    REWARD_CONFIG_NOT_FOUND(96001, HttpStatus.NOT_FOUND),
    REWARD_NOT_FOUND(96002, HttpStatus.NOT_FOUND),

    VIP_REWARD_ALREADY_CLAIMED(97001, HttpStatus.BAD_REQUEST),
    VIP_CONFIG_NOT_FOUND(97002, HttpStatus.NOT_FOUND),
    VIP_LEVEL_NOT_ENOUGH(97003, HttpStatus.BAD_REQUEST),
    VIP_REWARD_CLAIM_ERROR(97004, HttpStatus.BAD_REQUEST),

    // Player Level status codes
    PLAYER_LEVEL_REWARD_ALREADY_CLAIMED(98001, HttpStatus.BAD_REQUEST),
    PLAYER_LEVEL_CONFIG_NOT_FOUND(98002, HttpStatus.NOT_FOUND),
    PLAYER_LEVEL_NOT_ENOUGH(98003, HttpStatus.BAD_REQUEST),
    PLAYER_LEVEL_REWARD_CLAIM_ERROR(98004, HttpStatus.BAD_REQUEST),

    ITEM_AMOUNT_INVALID(100001, HttpStatus.BAD_REQUEST),
    ITEM_NOT_FOUND(100002, HttpStatus.BAD_REQUEST),
    ITEM_CONFIG_NOT_FOUND(100003, HttpStatus.BAD_REQUEST),
    ITEM_PROFILE_NOT_FOUND(100004, HttpStatus.BAD_REQUEST),
    ITEM_PRICE_NOT_FOUND(100005, HttpStatus.BAD_REQUEST),
    ITEM_PRICE_NOT_ACTIVE(100006, HttpStatus.BAD_REQUEST),
    ITEM_PRICE_MISMATCH(100007, HttpStatus.BAD_REQUEST),
    ITEM_EXPIRED(100008, HttpStatus.BAD_REQUEST),
    ITEM_PRICE_EXPIRED(100009, HttpStatus.BAD_REQUEST),
    CONTENT_CODE_INVALID(10432, HttpStatus.BAD_REQUEST),

    EVOLUTION_AUTH_ERROR(101001, HttpStatus.BAD_REQUEST),
    EVOLUTION_SYNC_GAMES_ERROR(101002, HttpStatus.BAD_REQUEST),

    // GENERIC_EXCEPTION 4xxxx
    GAME_NOT_FOUND(40000, HttpStatus.BAD_REQUEST),
    GAME_NOT_ACTIVE(40001, HttpStatus.BAD_REQUEST),
    GAME_GROUP_NOT_FOUND(40002, HttpStatus.BAD_REQUEST),
    GAME_PROVIDER_NOT_FOUND(41000, HttpStatus.BAD_REQUEST),
    CRYPTO_CURRENCY_NOT_SUPPORTED(42000, HttpStatus.BAD_REQUEST),
    CRYPTO_WITHDRAW_REQUEST_VALIDATE_FAILED(42001, HttpStatus.BAD_REQUEST),
    CRYPTO_WITHDRAW_REQUEST_NOT_VALID(42002, HttpStatus.BAD_REQUEST),
    CRYPTO_DEPOSIT_CLAIM_FAILED(42003, HttpStatus.INTERNAL_SERVER_ERROR),

    // quest
    QUEST_PROGRESS_NOT_FOUND(94001, HttpStatus.NOT_FOUND),
    QUEST_NOT_COMPLETE(94002, HttpStatus.BAD_REQUEST),
    QUEST_ALREADY_CLAIM(94003, HttpStatus.BAD_REQUEST),

    QUEST_REACH_LIMIT(94004, HttpStatus.BAD_REQUEST),

    MAX_REFRESH_REACHED(94005, HttpStatus.BAD_REQUEST),

    QUEST_CONFIG_NOT_FOUND(94006, HttpStatus.NOT_FOUND),

    NOT_ENOUGH_TICKET_FOR_SUITCASE(94007, HttpStatus.BAD_REQUEST),
    QUEST_PROGRESS_NOT_ENOUGH(94008, HttpStatus.BAD_REQUEST),

    // Refresh Token
    REFRESH_TOKEN_INVALID(95001, HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_EXPIRED(95002, HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_REVOKED(95003, HttpStatus.UNAUTHORIZED),

    // Friend
    FRIEND_NOT_FOUND(100001, HttpStatus.NOT_FOUND),
    ALREADY_FRIENDS(100002, HttpStatus.BAD_REQUEST),
    FRIEND_LIST_FULL(100003, HttpStatus.BAD_REQUEST),
    CANNOT_ADD_SELF(100004, HttpStatus.BAD_REQUEST),

    REQUEST_NOT_FOUND(100005, HttpStatus.NOT_FOUND),
    REQUEST_ALREADY_SENT(100006, HttpStatus.BAD_REQUEST),
    REQUEST_ALREADY_PROCESSED(100007, HttpStatus.BAD_REQUEST),

    GIFT_NOT_FOUND(100008, HttpStatus.NOT_FOUND),
    GIFT_ALREADY_CLAIMED(100009, HttpStatus.BAD_REQUEST),
    GIFT_ALREADY_SENT_TODAY(100010, HttpStatus.BAD_REQUEST),
    NOT_FRIENDS(100011, HttpStatus.BAD_REQUEST),
    RECEIVER_FRIEND_LIST_FULL(100012, HttpStatus.BAD_REQUEST);

    // region Implementation
    private final int value;

    private final String phrase;

    private final String message;

    private final HttpStatus httpStatus;

    AceStatusCode(int value, HttpStatus httpStatus) {
        this.value = value;
        this.phrase = name();
        this.message = name();
        this.httpStatus = httpStatus;
    }

    AceStatusCode(int value) {
        this.value = value;
        this.phrase = name();
        this.message = name();
        this.httpStatus = HttpStatus.OK;
    }

    AceStatusCode(int value, String phrase) {
        this.value = value;
        this.phrase = phrase;
        this.message = phrase;
        this.httpStatus = HttpStatus.OK;
    }

    AceStatusCode(int value, String phrase, String message) {
        this.value = value;
        this.phrase = phrase;
        this.message = StringUtils.hasText(message) ? message : phrase;
        this.httpStatus = HttpStatus.OK;
    }

    AceStatusCode(int value, String phrase, HttpStatus httpStatus) {
        this.value = value;
        this.phrase = phrase;
        this.message = phrase;
        this.httpStatus = httpStatus;
    }

    AceStatusCode(int value, String phrase, String message, HttpStatus httpStatus) {
        this.value = value;
        this.phrase = phrase;
        this.message = StringUtils.hasText(message) ? message : phrase;
        this.httpStatus = httpStatus;
    }

    /**
     * Return the integer value of this status code.
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getPhrase() {
        return this.phrase;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    /**
     * Return a string representation of this status code.
     */
    @Override
    public String toString() {
        return this.value + " " + name();
    }

    /**
     * Return the enum constant of this type with the specified numeric value.
     *
     * @param statusCode the numeric value of the enum to be returned
     * @return the enum constant with the specified numeric value
     * @throws IllegalArgumentException if this enum has no constant for the
     *                                  specified numeric value
     */
    public static AceStatusCode valueOf(int statusCode) {
        AceStatusCode status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        }
        return status;
    }

    /**
     * Resolve the given status code to an {@code AceStatusCode}, if possible.
     *
     * @param statusCode the HTTP status code (potentially non-standard)
     * @return the corresponding {@code AceStatusCode}, or {@code null} if not found
     * @since 5.0
     */
    @Nullable
    public static AceStatusCode resolve(int statusCode) {
        for (AceStatusCode status : values()) {
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }
}
