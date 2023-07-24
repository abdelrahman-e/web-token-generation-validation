package token.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import token.generator.service.GeneratorService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/token")
@CrossOrigin
public class GeneratorController {
    Logger log = Logger.getLogger(GeneratorController.class.getName());
    private final GeneratorService generatorService;

    @Autowired
    public GeneratorController(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> getGeneratedToken(@RequestParam List<Integer> availableDigits) {

        String result = generatorService.generateToken(availableDigits);
        if (result.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only single digits are allowed " + availableDigits);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
