
package vu.firstbank;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;


public class Validator {
     private static final Pattern LETTERS_ONLY  = Pattern.compile("^[A-Za-z]{2,30}$");
    private static final Pattern NIN_PATTERN   = Pattern.compile("^[A-Z0-9]{14}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\+256[0-9]{9}$");   // +256 then exactly 9 digits = 12 digits after +
    private static final Pattern PIN_PATTERN   = Pattern.compile("^[0-9]{4,6}$");

    public static String validateName(String value, String fieldName) {
        if (value == null || value.trim().isEmpty())
            return fieldName + " is required.";
        if (!LETTERS_ONLY.matcher(value.trim()).matches())
            return fieldName + " must be letters only, 2-30 characters.";
        return null;
    }

    public static String validateNin(String value) {
        if (value == null || value.trim().isEmpty())
            return "National ID (NIN) is required.";
        if (!NIN_PATTERN.matcher(value.trim()).matches())
            return "NIN must be exactly 14 uppercase alphanumeric characters.";
        return null;
    }

    public static String validateEmail(String email, String confirm) {
        if (email == null || email.trim().isEmpty())
            return "Email is required.";
        if (!EMAIL_PATTERN.matcher(email.trim()).matches())
            return "Email format is invalid.";
        if (!email.trim().equalsIgnoreCase(confirm == null ? "" : confirm.trim()))
            return "Email and Confirm Email do not match.";
        return null;
    }

    public static String validatePhone(String value) {
        if (value == null || value.trim().isEmpty())
            return "Phone Number is required.";
        if (!PHONE_PATTERN.matcher(value.trim()).matches())
            return "Phone must follow format +256XXXXXXXXX (12 digits total).";
        return null;
    }

    public static String validatePin(String pin, String confirm) {
        if (pin == null || pin.trim().isEmpty())
            return "PIN is required.";
        if (!PIN_PATTERN.matcher(pin.trim()).matches())
            return "PIN must be 4-6 digits (numeric only).";
        if (pin.trim().chars().distinct().count() == 1)
            return "PIN must not be all identical digits (e.g. 0000).";
        if (!pin.trim().equals(confirm == null ? "" : confirm.trim()))
            return "PIN and Confirm PIN do not match.";
        return null;
    }

    public static int validateDob(int year, int month, int day, String[] errorOut) {
        try {
            LocalDate dob = LocalDate.of(year, month, day);
            LocalDate today = LocalDate.now();
            if (dob.isAfter(today)) {
                errorOut[0] = "Date of Birth cannot be in the future.";
                return -1;
            }
            int age = Period.between(dob, today).getYears();
            if (age < 18 || age > 75) {
                errorOut[0] = "Age must be between 18 and 75. Computed age: " + age;
                return -1;
            }
            errorOut[0] = null;
            return age;
        } catch (Exception e) {
            errorOut[0] = "Invalid Date of Birth.";
            return -1;
        }
    }

    public static String validateDeposit(String value, double minimum, String accountType) {
        if (value == null || value.trim().isEmpty())
            return "Opening Deposit is required.";
        try {
            double amount = Double.parseDouble(value.trim().replace(",", ""));
            if (amount < minimum)
                return String.format(
                    "Minimum deposit for %s is UGX %,.0f. You entered UGX %,.0f.",
                    accountType, minimum, amount);
            return null;
        } catch (NumberFormatException e) {
            return "Opening Deposit must be a numeric value.";
        }
    }

    public static String validateSecondNin(String value) {
        if (value == null || value.trim().isEmpty())
            return "Second NIN is required for Joint accounts.";
        if (!NIN_PATTERN.matcher(value.trim()).matches())
            return "Second NIN must be exactly 14 uppercase alphanumeric characters.";
        return null;
    }
}
