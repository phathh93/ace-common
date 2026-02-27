package com.ace.util;

import com.ace.AceConst;
import com.ace.AceStatusCode;
import com.ace.exception.AceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.PemReader;
import com.google.api.client.util.SecurityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

public class Utils {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String NUMERIC_STRING = "0123456789";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static PrivateKey privateKeyFromPkcs8(String privateKeyPem) throws IOException {
        Reader reader = new StringReader(privateKeyPem);
        PemReader.Section section = PemReader.readFirstSectionAndClose(reader, "PRIVATE KEY");
        if (section == null) {
            throw new IOException("Invalid PKCS8 data.");
        }
        byte[] bytes = section.getBase64DecodedBytes();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        Exception unexpectedException = null;
        try {
            KeyFactory keyFactory = SecurityUtils.getRsaKeyFactory();
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException exception) {
            unexpectedException = exception;
        } catch (InvalidKeySpecException exception) {
            unexpectedException = exception;
        }
        throw new IOException("Unexpected exception reading PKCS data");

    }

    public static String hashMD5(final String value) {
        String hash = value;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(value.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            hash = sb.toString();
        } catch (Exception ex) {

        }
        return hash;
    }

    public static String hashHmacSHA256(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return org.apache.commons.codec.binary.Hex
                .encodeHexString(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static String randomNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * NUMERIC_STRING.length());
            builder.append(NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static String generateReferralCode() {
        return VoucherCode.generateOne(2000000000, 7, "", "R", "", "#######");
    }

    public static String generateReferralCode(long number) {
        String generateCode = BigInteger.valueOf(8000000000L + number).toString(36);
        String code = "R" + generateCode.toUpperCase();
        return code;
    }

    public static long randomLong(final long min, final long max) {
        return min + (long) (Math.random() * (max - min));
    }

    public static int randomInt(final int min, final int max) {
        return min + (int) (Math.random() * (max - min));
    }

    public static Double randomDouble(final Double min, final Double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if (list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }

    public static <T extends Comparable<T>> T clamp(final T min, final T max, final T value) {
        if (min.compareTo(max) > 0)
            return value;
        else if (value.compareTo(min) < 0)
            return min;
        else if (value.compareTo(max) > 0)
            return max;
        return value;
    }

    public static boolean isDateWithoutTimeExpired(final Date dateNeedCheck) {
        return Utils.isDateWithoutTimeExpired(dateNeedCheck, Utils.getNowWithoutTime());
    }

    public static boolean isDateWithoutTimeExpired(final Date dateNeedCheck, final Date dateNow) {
        Date toDay = Utils.getDateWithoutTime(dateNow);
        Date dayCreate = Utils.getDateWithoutTime(dateNeedCheck);
        long millisecondDiff = toDay.getTime() - dayCreate.getTime();
        long dayDiff = TimeUnit.DAYS.convert(millisecondDiff, TimeUnit.MILLISECONDS);
        return dayDiff >= 0;
    }

    public static long getDayDiffWithNow(final Date dateNeedCheck) {
        return Utils.getDayDiff(Utils.getNowWithoutTime(), dateNeedCheck);
    }

    public static long getDayDiff(final Date dateFirst, final Date dateSecond) {
        return getDayDiff(dateFirst, dateSecond, false);
    }

    public static long getDayDiff(final Date dateFirst, final Date dateSecond, boolean requireTime) {
        Date first = requireTime ? dateFirst : Utils.getDateWithoutTime(dateFirst);
        Date second = requireTime ? dateSecond : Utils.getDateWithoutTime(dateSecond);
        long millisecondDiff = second.getTime() - first.getTime();
        return TimeUnit.DAYS.convert(millisecondDiff, TimeUnit.MILLISECONDS);
    }

    public static Date getDateWithoutTime(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getNowWithoutTime() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        return Utils.getDateWithoutTime(calendar.getTime());
    }

    public static Date getNextDateWithoutTime() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.add(Calendar.DATE, 1);
        return Utils.getDateWithoutTime(calendar.getTime());
    }

    public static Date getPrevDateWithoutTime() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.add(Calendar.DATE, -1);
        return Utils.getDateWithoutTime(calendar.getTime());
    }

    public static Date addSecondToNow(int second) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public static Date addSecondToDate(int seconds, @NotNull Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public static long getRemainTime(Date next) {
        Date now = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime();
        return Utils.getRemainTime(next, now);
    }

    public static long getRemainTime(Date next, Date now) {
        // milliseconds
        long remainTime = next.getTime() - now.getTime();
        // seconds
        remainTime = Math.max(0, remainTime) / 1000;
        return remainTime;
    }

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public static String createUID() {
        return hashMD5(randomAlphaNumeric(3));
    }

    public static String getAvatarDefault() {
        return AceConst.DEFAULT_AVATAR;
        // Random rd = new Random();
        // int indexAvatar = rd.nextInt(AceConst.MAX_DEFAULT_AVATAR) + 1;
        // String temp = indexAvatar < 10 ? "0" + indexAvatar : "" + indexAvatar;
        // return String.format("internal://avatar_%s", temp);
    }

    public static String secondsToHHMMSS(Long seconds) {
        return String.format("%02d", seconds / 3600) + ":" + String.format("%02d", seconds / 60 % 60) + ":"
                + String.format("%02d", seconds % 60);
    }

    public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static DateTime getUtcNow() {
        return new DateTime(DateTimeZone.UTC);
    }

    public static Boolean isPassDay(DateTime time) {
        DateTime now = getUtcNow();
        time = time.toDateTime(DateTimeZone.UTC);
        return (time.getDayOfMonth() != now.getDayOfMonth() || time.getMonthOfYear() != now.getMonthOfYear()
                || time.getYear() != now.getYear());

    }

    public static Boolean isPassWeek(DateTime time) {
        DateTime now = getUtcNow();
        time = time.toDateTime(DateTimeZone.UTC);
        DateTime firstDateOfWeek = now.withDayOfWeek(DateTimeConstants.MONDAY).withTime(0, 0, 0, 0);
        return DateTimeComparator.getInstance().compare(firstDateOfWeek, time) > 0;

    }

    public static Boolean isPassMonth(DateTime time) {
        DateTime now = getUtcNow();
        time = time.toDateTime(DateTimeZone.UTC);
        return (time.getMonthOfYear() != now.getMonthOfYear() || time.getYear() != now.getYear());
    }

    public static Boolean isPassYear(DateTime time) {
        if (time == null)
            return false;
        DateTime now = getUtcNow();
        time = time.toDateTime(DateTimeZone.UTC);
        return time.getYear() != now.getYear();
    }

    public static Boolean isToday(DateTime time) {
        DateTime now = getUtcNow();
        time = time.toDateTime(DateTimeZone.UTC);
        return (time.getDayOfMonth() == now.getDayOfMonth() && time.getMonthOfYear() == now.getMonthOfYear()
                && time.getYear() == now.getYear());
    }

    public static String numberWithCommas(BigDecimal number, int decimalPlaces) {
        number = number.setScale(decimalPlaces, RoundingMode.DOWN);
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(decimalPlaces);
        numberFormat.setMinimumFractionDigits(0);
        return numberFormat.format(number);
    }

    public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (!StringUtils.hasText(bearerToken))
            bearerToken = request.getParameter("token");
        if (!StringUtils.hasText(bearerToken))
            bearerToken = request.getParameter("accessToken");
        if (!StringUtils.hasText(bearerToken))
            bearerToken = request.getParameter("access_token");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return bearerToken;
    }

    public static String writeDataToJSONString(Object data, ObjectMapper objectMapper) {

        if (objectMapper == null) {
            objectMapper = Utils.objectMapper;
        }
        try {
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new AceException(AceStatusCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public static <T> T readDataFromJSONString(String data, ObjectMapper objectMapper, Class<T> clazz) {
        if (objectMapper == null) {
            objectMapper = Utils.objectMapper;
        }
        try {
            return objectMapper.readValue(data, clazz);
        } catch (Exception e) {
            throw new AceException(AceStatusCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public static String formatWithAsterisks(String input) {
        if (input == null || input.length() < 5) {
            throw new IllegalArgumentException("Input must be at least 5 characters long.");
        }
        String start = input.substring(0, 3); // First 3 characters
        String end = input.substring(input.length() - 2); // Last 2 characters

        // Create a string with asterisks for the middle part
        StringBuilder middle = new StringBuilder();
        for (int i = 0; i < input.length() - 5; i++) {
            middle.append("*");
        }
        return start + middle.toString() + end;
    }

    public static Date getStartOfDay(Date date) {
        DateTime dateTime = new DateTime(date).withZone(DateTimeZone.UTC);
        return dateTime.withTimeAtStartOfDay().toDate();
    }

    public static Date getEndOfDay(Date date) {
        DateTime dateTime = new DateTime(date).withZone(DateTimeZone.UTC);
        return dateTime.withTime(23, 59, 59, 999).toDate();
    }

    public static String getClientIP(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("HttpServletRequest cannot be null");
        }

        // 1. Check Cloudflare's CF-Connecting-IP header
        String cfConnectingIP = request.getHeader("CF-Connecting-IP");
        if (cfConnectingIP != null && !cfConnectingIP.trim().isEmpty()) {
            return cfConnectingIP.trim();
        }

        // 2. Check X-Real-IP header (commonly set by Nginx or other reverse proxies)
        String xRealIP = request.getHeader("X-Real-IP");
        if (xRealIP != null && !xRealIP.trim().isEmpty()) {
            return xRealIP.trim();
        }

        // 3. Check X-Forwarded-For header (contains a list of proxies, take the first
        // valid IP)
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.trim().isEmpty()) {
            String[] ipList = xForwardedFor.split(",");
            if (ipList.length > 0 && !ipList[0].trim().isEmpty()) {
                return ipList[0].trim();
            }
        }

        // 4. Fallback to remote address
        return request.getRemoteAddr();
    }

    public static <T> JsonNode objectToJsonNode(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        return objectMapper.valueToTree(object);
    }

    public static String getUrlFromRequest(HttpServletRequest request, boolean fullUrl) {
        if (request == null) {
            throw new IllegalArgumentException("HttpServletRequest cannot be null");
        }

        // 1. Thử lấy từ header Referer (URL đầy đủ mà client gửi từ đó)
        String referer = request.getHeader("Referer");
        if (referer != null && !referer.trim().isEmpty()) {
            if (fullUrl) {
                return referer.trim();
            } else {
                // Chỉ lấy base URL từ Referer
                return extractBaseUrl(referer);
            }
        }

        // 2. Thử lấy từ header Origin (chỉ domain, không path)
        String origin = request.getHeader("Origin");
        if (origin != null && !origin.trim().isEmpty()) {
            return origin.trim(); // Origin luôn là base URL, bỏ qua fullUrl
        }

        // 3. Thử lấy từ X-Forwarded-Host (domain mà client thấy qua proxy)
        String forwardedHost = request.getHeader("X-Forwarded-Host");
        if (forwardedHost != null && !forwardedHost.trim().isEmpty()) {
            String scheme = request.getHeader("X-Forwarded-Proto") != null ? request.getHeader("X-Forwarded-Proto")
                    : "http";
            String baseUrl = scheme + "://" + forwardedHost.trim();
            if (fullUrl) {
                // Nếu cần full URL, thêm path từ request (giả sử path từ client giống server)
                String path = request.getRequestURI();
                return baseUrl + path;
            } else {
                return baseUrl;
            }
        }

        // 4. Fallback: Dùng request.getRequestURL() (URL của server, không phải client)
        String fullServerUrl = request.getRequestURL().toString();
        if (fullUrl) {
            return fullServerUrl;
        } else {
            String requestUri = request.getRequestURI();
            return fullServerUrl.replace(requestUri, "");
        }
    }

    private static String extractBaseUrl(String url) {
        try {
            java.net.URL parsedUrl = new java.net.URL(url);
            return parsedUrl.getProtocol() + "://" + parsedUrl.getHost() +
                    (parsedUrl.getPort() != -1 ? ":" + parsedUrl.getPort() : "");
        } catch (Exception e) {
            // Nếu không parse được, trả về nguyên gốc (hoặc xử lý theo cách khác)
            return url;
        }
    }

    public static boolean isInternalUrl(String url) {
        return url.startsWith("internal://");
    }

    static final String TOKEN_COOKIE_NAME = "client-token";

    public static String getClientToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (TOKEN_COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        String token = UUID.randomUUID().toString();
        Cookie cookie = new Cookie(TOKEN_COOKIE_NAME, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge((int) Duration.ofDays(365).getSeconds());
        response.addCookie(cookie);
        return token;
    }

    public static Integer getIntegerValue(Object value) {
        if (value == null)
            return null;
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getOrigin(HttpServletRequest request) {
        String origin = request.getHeader("Origin");
        if (origin != null && !origin.isEmpty()) {
            if (origin != null && !origin.isEmpty()) {
                if (origin.startsWith("http://")) {
                    origin = origin.substring(7);
                } else if (origin.startsWith("https://")) {
                    origin = origin.substring(8);
                }
                // Remove trailing slash if present
                if (origin.endsWith("/")) {
                    origin = origin.substring(0, origin.length() - 1);
                }
            }
            int colonIndex = origin.indexOf(':');
            if (colonIndex > 0) {
                origin = origin.substring(0, colonIndex);
            }
            if (origin.startsWith("www.")) {
                origin = origin.substring(4);
            }
            return origin;
        }
        return "";
    }
}