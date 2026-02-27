package com.ace.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VoucherCode {
    private final Integer count;
    private final Integer length;
    private final String charset;
    private final String prefix;
    private final String postfix;
    private final String pattern;

    public Map<String, String> charsets = new HashMap<String, String>() {{
        put("numbers", "0123456789");
        put("alphabetic", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        put("alphanumeric", "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        put("alphanumeric-upper", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }};

    public static Integer randomInt(Integer min, Integer max) {
        Double result = Math.floor(Math.random() * (max - min + 1)) + min;
        return result.intValue();
    }

    public Character randomElem(final String sources) {
        if (sources == null || sources.length() <= 0) return 0;
        return sources.charAt(randomInt(0, sources.length() - 1));
    }

    public String charset(final String name) {
        return charsets.get(name);
    }

    public String repeat(final String str, final Integer count) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < count; i++) {
            res.append(str);
        }
        return res.toString();
    }

    public VoucherCode(Integer count, Integer length, String charset, String prefix, String postfix, String pattern) {
        this.count = Math.max(count, 1);
        this.length = Math.max(length, 1);
        this.charset = StringUtils.hasText(charset) ? charset : charset("alphanumeric-upper");
        this.prefix = prefix;
        this.postfix = postfix;
        this.pattern = StringUtils.hasText(pattern) ? pattern : repeat("#", this.length);
    }

    public String generateOne() {
        String[] patternSplit = this.pattern.split("");
        StringBuilder rawCode = new StringBuilder(this.prefix);
        for (String s : patternSplit) {
            if (s.equals("#")) {
                rawCode.append(randomElem(this.charset));
            } else {
                rawCode.append(s);
            }
        }
        rawCode.append(this.postfix);
        return rawCode.toString();
    }

    public Boolean isFeasible() {
        List<String> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile("(#)", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE)
                .matcher(this.pattern);
        while (m.find()) {
            allMatches.add(m.group());
        }
        return Math.pow(charset.length(), allMatches.size()) >= count;
    }

    public static String generateOne(Integer count, Integer length, String charset, String prefix, String postfix, String pattern) {
        VoucherCode voucherCode = new VoucherCode(count, length, charset, prefix, postfix, pattern);
        if (!voucherCode.isFeasible()) {
            throw new Error("Not possible to generate requested number of codes.");
        }
        return voucherCode.generateOne();
    }

    public static List<String> generate(Integer count, Integer length, String charset, String prefix, String postfix, String pattern) {
        VoucherCode voucherCode = new VoucherCode(count, length, charset, prefix, postfix, pattern);
        if (!voucherCode.isFeasible()) {
            throw new Error("Not possible to generate requested number of codes.");
        }
        Map<String, Boolean> result = new HashMap<>();
        while (count > 0) {
            String code = voucherCode.generateOne();
            if (result.get(code) == null) {
                result.put(code, true);
                count--;
            }
        }
        return new ArrayList<>(result.keySet());
    }
}

