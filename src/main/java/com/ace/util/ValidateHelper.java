package com.ace.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateHelper {

    public static boolean isValidUsername(String username) {
        // Define the regular expression for validation
        String regex = "^[a-zA-Z0-9]{8,25}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(username);

        // Check if the username matches the pattern
        return matcher.matches();
    }

    public static boolean isValidUpdateUser(String username) {
        // Define the regular expression for validation
        String regex = "^[a-zA-Z0-9 _-]+$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(username);

        // Check if the username matches the pattern
        return matcher.matches();
    }
}
