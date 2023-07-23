package token.generator.service;

import org.springframework.stereotype.Service;
import token.generator.util.TokenGenerationUtil;

import java.util.List;
import java.util.logging.Logger;

@Service
public class GeneratorService {

    Logger log = Logger.getLogger(GeneratorService.class.getName());

    private boolean isValidDigitList(List<Integer> availableDigits) {

        if (availableDigits == null || availableDigits.isEmpty()) {
            return true;
        }
        return availableDigits.stream().allMatch(i -> i >= 0 && i <= 9);
    }

    public String generateToken(List<Integer> availableDigits) {
        if (!isValidDigitList(availableDigits)) {
            return "";
        }
        return TokenGenerationUtil.generateToken(availableDigits);
    }
}
