package com.ace;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;

public final class AceConst {

    public static final Long AGENT_ROOT_ID = 1L;

    public static final Long DEFAULT_BALANCE = 0L;

    public static final long DEFAULT_OAUTH_CLIENT_ID = 1;

    public static final int MAX_CCU_PER_SERVER = 2000;

    // WHEEL SPIN CONFIG

    public static final String SERVICE_GIFT_CODE = "SERVICE_GIFT_CODE";
    public static final String SERVICE_LUCKY_SPIN = "SERVICE_LUCKY_SPIN";
    public static final String SERVICE_MESSAGE = "SERVICE_MESSAGE";
    public static final String SERVICE_FRIEND = "SERVICE_FRIEND";
    public static final String SERVICE_VIP = "SERVICE_VIP";
    public static final String SERVICE_PLAYER_LEVEL = "SERVICE_PLAYER_LEVEL";
    public static final String SERVICE_MESSAGE_SYSTEM = "SERVICE_MESSAGE_SYSTEM";
    public static final String SERVICE_DEPOSIT_VOUCHER = "SERVICE_DEPOSIT_VOUCHER";
    public static final String SERVICE_TOPUP_QRIS = "SERVICE_TOPUP_QRIS";
    public static final String SERVICE_DEPOSIT_CRYPTO = "SERVICE_DEPOSIT_CRYPTO";
    public static final String SERVICE_WITHDRAW_CRYPTO = "SERVICE_WITHDRAW_CRYPTO";
    public static final String SERVICE_WITHDRAWAL_QRIS = "SERVICE_WITHDRAWAL_QRIS";
    public static final String SERVICE_CLAIM_REFERRAL = "SERVICE_CLAIM_REFERRAL";
    public static final String SERVICE_LINK_PHONE_BONUS = "SERVICE_LINK_PHONE_BONUS";
    public static final String SERVICE_CONSUMER_REWARD = "SERVICE_CONSUMER_REWARD";
    public static final String SERVICE_PROMOTION = "SERVICE_PROMOTION";
    public static final String SERVICE_REBATE = "SERVICE_REBATE";
    public static final String SERVICE_DEPOSIT_GGPAY = "SERVICE_DEPOSIT_GGPAY";
    public static final String SERVICE_QUEST = "QUEST_";
    // public static final String SERVICE_BET_REFUND = "SERVICE_BET_REFUND";
    public static final String SERVICE_WELCOME_BONUS = "SERVICE_WELCOME_BONUS";
    // USER DEFAULT

    public static final String DEFAULT_AGENT = "ACEPOKER";

    public static final String DEFAULT_USER_TYPE = "user";

    public static final boolean singleClientSignOn = true;

    public static final String DEFAULT_CURRENCY_CODE = "IDR";

    public static final String DEFAULT_AVATAR = "internal://avatar_00";

    public static final int MAX_DEFAULT_AVATAR = 10;

    // ROUTE

    public static final String ROUTE_REST = "/api";

    public static final String ROUTE_REST_BATCH = "/api/batch";

    public static final String ROUTE_REST_PHONE = "/api/phone";

    public static final String ROUTE_REST_EMAIL = "/api/email";

    public static final String ROUTE_REST_MS_CRYPTO = "/api/crypto";

    public static final String ROUTE_REST_MS_EXTERNAL_CRYPTO = "/api/crypto/external";

    public static final String ROUTE_REST_BROADCAST = "/api/broadcast";

    public static final String ROUTE_REST_ADAPTER = "/api/adapter";

    public static final String ROUTE_REST_PUBLIC = "/api/public";

    public static final String ROUTE_REST_SAFEBOX = "/api/safebox";

    public static final String ROUTE_REST_ACCOUNTS = "/api/accounts";

    public static final String ROUTE_REST_BET_REFUND = "/api/bet-refund";

    public static final String ROUTE_REST_USERS = "/api/users";

    public static final String ROUTE_REST_FEATURES = "/api/features";

    public static final String ROUTE_REST_SERVICE_MAINTENANCE = "/api/services/maintenance";

    public static final String ROUTE_REST_PAYMENTS = "/api/payments";

    public static final String ROUTE_REST_OAUTH = "/oauth";

    public static final String ROUTE_REST_PASSWORD = "/password";

    public static final String ROUTE_REST_NOTIFICATION = "/api/notification";

    public static final String ROUTE_REST_TEST = "/api/test";

    public static final String ROUTE_REST_PROMOTION = "/api/promotion";

    public static final String ROUTE_REST_SPIN = "/api/lucky-spin";
    public static final String ROUTE_REST_SETTINGS = "/api/settings";

    public static final String ROUTE_REST_GAME_HISTORY = "/api/game-history";

    public static final String ROUTE_REST_REWARD = "/api/rewards";

    public static final String ROUTE_REST_FRIENDS = "/api/friends";

    public static final String ROUTE_REST_FRIEND_REQUEST = ROUTE_REST_FRIENDS + "/requests";

    public static final String ROUTE_REST_FRIEND_GIFT = ROUTE_REST_FRIENDS + "/gifts";

    public static final String ROUTE_REST_FRIEND_HISTORY = ROUTE_REST_FRIENDS + "/history";

    public static final String ROUTE_REST_FRIEND_RECOMMENDATION = ROUTE_REST_FRIENDS + "/recommendations";

    // chestbox
    public static final String ROUTE_REST_FEATURES_CHESTBOX = "/api/features/require-chest-box";
    public static final String ROUTE_REST_FEATURES_CHESTBOX_CLAIM = "/api/features/chest-box";


    // ROLE
    public static final SimpleGrantedAuthority GRANT_ROLE_GAME_CLIENT = new SimpleGrantedAuthority("ROLE_GAME_CLIENT");

    public static final SimpleGrantedAuthority GRANT_ROLE_MS_CRYPTO = new SimpleGrantedAuthority("ROLE_MS_CRYPTO");

    public static final SimpleGrantedAuthority GRANT_ROLE_LOGGED_USER = new SimpleGrantedAuthority("ROLE_USER");

    public static final SimpleGrantedAuthority GRANT_ROLE_CLIENT = new SimpleGrantedAuthority("ROLE_CLIENT");

    public static final SimpleGrantedAuthority GRANT_ROLE_OPERATOR = new SimpleGrantedAuthority("ROLE_OPERATOR");

    public static final String ROLE_LOGGED_GAME_CLIENT = "hasRole('GAME_CLIENT')";

    public static final String ROLE_MS_CRYPTO = "hasRole('MS_CRYPTO')";

    public static final String ROLE_LOGGED_USER = "hasRole('USER')";

    public static final String ROLE_CLIENT = "hasRole('CLIENT')";

    public static final String ROLE_LOGGED_OPERATOR = "hasRole('OPERATOR')";

    // PAYPAL
    public static final String PAYPAL_CLIENT_ID = "paypal_client_id";
    public static final String PAYPAL_CLIENT_SECRET = "paypal_client_secret";
    public static final String PAYPAL_ENV = "paypal_env";
    public static final String PAYPAL_LIVE = "1";
    public static final String PAYPAL_DEV = "0";
    public static final String PAYPAL_ORDER_ID = "paypal_order_id_";

    public static List<String> CLIENT_APIS = Arrays.asList(
            "/v1/add-balance",
            "/v1/sub-balance");

    public static final String WALLET_OWNER_PROFILE = "profile";
    public static final String WALLET_OWNER_AGENT = "agent";

    public static final String OAUTH_CLIENT_INHERIT = "__INHERIT__";

    // REWARD IDS
    public static final Long PHONE_BIND_BONUS_ID = 1001L;
}
