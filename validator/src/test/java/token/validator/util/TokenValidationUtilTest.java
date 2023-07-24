package token.validator.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenValidationUtilTest {
    String validToken = "7019-4165-7968-4996";
    String validToken2 = "0088-4315-6083-7827";

    String invalidToken = "7019-4165-7968-4995";

    @Test
    void testValidTokenValidation() {

        assertTrue(TokenValidationUtil.isValidToken(validToken));

        assertTrue(TokenValidationUtil.isValidToken(validToken2));
    }

    @Test
    void testCorrectInvalidTokenValidation() {
        assertFalse(TokenValidationUtil.isValidToken(invalidToken));
    }
}
