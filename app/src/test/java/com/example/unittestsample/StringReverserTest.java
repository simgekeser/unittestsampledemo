package com.example.unittestsample;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


public class StringReverserTest {

    StringReverser SUT;
    @Before
    public void setUp() throws Exception {
        SUT = new StringReverser();
    }

    @Test
    public void reverse_emptyString_reversedStringReturned() throws Exception{
        String result = SUT.reverse("");
        assertThat(result).isEqualTo("");
    }

    @Test
    public void reverse_singleCharacter_reversedStringReturned() throws Exception{
        String result = SUT.reverse("a");
        assertThat(result).isEqualTo("a");
    }

    @Test
    public void reverse_longString_reversedStringReturned() throws Exception{
        String result = SUT.reverse("Simge Keser");
        assertThat(result).isEqualTo("reseK egmiS");
    }

}