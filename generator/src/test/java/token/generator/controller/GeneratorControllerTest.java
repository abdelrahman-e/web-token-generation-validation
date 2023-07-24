package token.generator.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import token.generator.service.GeneratorService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeneratorControllerTest {

    @Mock
    private GeneratorService generatorService;

    @InjectMocks
    private GeneratorController generatorController;

    @Test
    public void testGetGeneratedTokenWithValidDigits() {
        when(generatorService.generateToken(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))).thenReturn("1234-5678-9012-3456");
        ResponseEntity<String> response = generatorController.getGeneratedToken(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1234-5678-9012-3456", response.getBody());
    }

    @Test
    public void testGetGeneratedTokenWithInvalidDigits() {
        ResponseEntity<String> response = generatorController.getGeneratedToken(List.of(-1, 10));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Only single digits are allowed [-1, 10]", response.getBody());
    }

    @Test
    public void testGetGeneratedTokenWithEmptyDigits() {
        when(generatorService.generateToken(Collections.emptyList())).thenReturn("1234-5678-9012-3456");
        ResponseEntity<String> response = generatorController.getGeneratedToken(Collections.emptyList());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1234-5678-9012-3456", response.getBody());
    }
}

