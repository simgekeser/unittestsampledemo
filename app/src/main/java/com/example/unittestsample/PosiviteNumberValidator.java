package com.example.unittestsample;

import androidx.annotation.Nullable;

public class PosiviteNumberValidator {

    public boolean isPositive(int number){
        if (number > 0)
            return true;
        else {
            return false;
        }
    }
}
