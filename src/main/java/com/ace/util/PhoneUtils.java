package com.ace.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneUtils {

    public static String getPhoneNumber(String rawPhoneNumber) throws NumberParseException {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(rawPhoneNumber, null);
        return phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
    }

    public static void main(String[] args) {
        try {
            System.out.println(getPhoneNumber("+84789123123"));
        } catch (NumberParseException e) {
            log.error("", e);
        }
    }
}
