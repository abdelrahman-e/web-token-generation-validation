package token.validator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import token.validator.service.ValidatorService;

import java.util.logging.Logger;

@RestController
@RequestMapping("/token")
public class ValidatorController {
    Logger log = Logger.getLogger(ValidatorController.class.getName());
    private final ValidatorService validatorService;

    @Autowired
    public ValidatorController(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<String> getGeneratedToken(@PathVariable String token) {

        String result = validatorService.validateToken(token);
        if (result.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid token " + token);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
