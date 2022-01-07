package com.example.unittestsample;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class PosiviteNumberValidatorTest {
    PosiviteNumberValidator SUT;
    @Before
    public void set(){
        SUT= new PosiviteNumberValidator();
    }

    @Test
    public void isPositive() {
        boolean result = SUT.isPositive(2);
        assertThat(result).isTrue();
    }
    @Test
    public void isNegative() {
        boolean result = SUT.isPositive(-1);
        assertThat(result).isFalse();
    }
}