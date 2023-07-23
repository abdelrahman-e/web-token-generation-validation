package token.generator.util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static token.generator.constants.GeneratorConstants.TOKEN_LENGTH_WITHOUT_DASHES;
import static token.generator.constants.GeneratorConstants.TOKEN_LENGTH_WITH_DASHES;


public class TokenGenerationUtil {
    private TokenGenerationUtil() {
    }


    public static String generateToken(List<Integer> availableDigits) {
        if (availableDigits == null || availableDigits.isEmpty()) {
            availableDigits = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        }

        SecureRandom secureRandom = new SecureRandom();

        return secureRandom.ints(TOKEN_LENGTH_WITHOUT_DASHES, 0, availableDigits.size())
                .mapToObj(availableDigits::get)//convert each random index in the stream into a digit from availableDigits
                .map(String::valueOf)//converts integers to string
                .collect(Collectors.joining())//concatenate all strings into a single string
                .replaceAll("....", "$0-")//replaces each 4 numbers with themselves($0) followed by dash
                .substring(0, TOKEN_LENGTH_WITH_DASHES);//removes final dash
    }

}
