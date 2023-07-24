package token.validator.util;

public class TokenValidationUtil {
    private TokenValidationUtil() {
    }

    public static Boolean isValidToken(String token) {
        // Remove dashes from the token
        String digits = token.replace("-", "");
        // Compute the checksum using the Luhn algorithm
        int sum = 0;
        for (int i = 0; i < digits.length(); i++) {
            int digit = Character.getNumericValue(digits.charAt(i));
            if ((digits.length() - i) % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        // Check if the checksum is divisible by 10
        return sum % 10 == 0;
    }

//    public static Boolean isValidToken(String token) {
//        // Remove dashes from the token
//        String digits = token.replaceAll("-", "");
//        // Compute the checksum using the Luhn algorithm
//        int sum = IntStream.range(0, digits.length())
//                .map(i -> Character.getNumericValue(digits.charAt(digits.length() - 1 - i)))
//                .map(digit -> (digit % 2 == 1) ? (digit * 2 > 9 ? digit * 2 - 9 : digit * 2) : digit)
//                .sum();
//        // Check if the checksum is divisible by 10
//        return sum % 10 == 0;
//    }
}
