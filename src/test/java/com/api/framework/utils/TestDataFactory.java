package com.api.framework.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class TestDataFactory {

    public static String randomScreenName() {
        return "screenName_" + RandomStringUtils.randomAlphabetic(5);
    }

    public static String randomLogin() {
        return "login_" + RandomStringUtils.randomAlphabetic(5);
    }

    public static String randomAlphabeticPassword(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String randomNumericPassword(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public static Integer getMinValidAge() {
        return 17;
    }

    public static Integer getMaxValidAge() {
        return 59;
    }
}
