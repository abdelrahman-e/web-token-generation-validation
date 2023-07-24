package token.generator.service;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static token.generator.constants.GeneratorConstants.TOKEN_LENGTH_WITH_DASHES;

public class GeneratorServiceTest {

    GeneratorService generatorService = new GeneratorService();

    @Test
    public void testGenerateTokenWithoutDigits() {
        String token = generatorService.generateToken(Collections.emptyList());
        assertEquals(TOKEN_LENGTH_WITH_DASHES, token.length());
    }

    @Test
    public void testGenerateTokenWithInvalidDigits() {
        String token = generatorService.generateToken(List.of(1, 2, 12));
        assertEquals("", token);
    }

    @Test
    public void testGenerateTokenWithValidDigits() {
        String token = generatorService.generateToken(List.of(1));
        assertThat(token, containsString("1"));
        assertEquals(TOKEN_LENGTH_WITH_DASHES, token.length());
    }
}
