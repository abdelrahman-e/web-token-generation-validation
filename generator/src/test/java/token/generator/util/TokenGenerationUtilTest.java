package token.generator.util;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TokenGenerationUtilTest {

    final int EXPECTED_TOKEN_LENGTH = 19;

    @Test
    void testValidTokenGenerationWithoutDigits() {
        String result = TokenGenerationUtil.generateToken(Collections.emptyList());
        System.out.println(result);

        assertEquals(EXPECTED_TOKEN_LENGTH, result.length());

    }

    @Test
    void testValidTokenGenerationWithDigits() {
        List<Integer> availableDigits = List.of(1, 2, 3, 4);
        String result = TokenGenerationUtil.generateToken(availableDigits);

        assertThat(result, allOf(containsString("1"), containsString("2"), containsString("3"), containsString("4")));

        assertEquals(EXPECTED_TOKEN_LENGTH, result.length());
    }
    

}
