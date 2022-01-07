package com.example.unittestsample;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class RegistrationUtilTest {

    @Test
    public void test1(){
        RegistirationUtil registirationUtil = new RegistirationUtil();
        boolean result = registirationUtil.validateRegistirationInput(
                "",
                "123",
                "123"
        );
        assertThat(result).isTrue();
    }

}