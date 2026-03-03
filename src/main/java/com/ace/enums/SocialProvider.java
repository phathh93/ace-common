package com.ace.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum SocialProvider {
    @JsonEnumDefaultValue
    unknown,
    guest,
    facebook,
    fbinstant,
    apple,
    google,
    github,
    android,
    twitter,
    iOS,
    nopassword,
}
