package com.airtribe.learntrack.util;

import java.util.regex.Pattern;

import com.airtribe.learntrack.exceptions.InvalidDataException;

public class Validator {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static void IsNotNullOrEmpty(String data) throws InvalidDataException {
        if (data == null || data.isBlank()) {
            throw new InvalidDataException("Null or Empty is not allowed");
        }
    }

    public static void IsValidEmail(String emailAddress) throws InvalidDataException {

        if (emailAddress == null || emailAddress.isBlank()) {
            throw new InvalidDataException("Email cannot be null or empty.");
        }

        if (!pattern.matcher(emailAddress).matches()) {
            throw new InvalidDataException("Invalid email format: " + emailAddress);
        }
    }

}
