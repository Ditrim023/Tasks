package com.tasks.cities;

import com.tasks.cities.exceptions.InvalidDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static void checkName(String name) {
        if (!name.matches("[a-z]+")) {
            throw new InvalidDataException();
        }
    }
}
