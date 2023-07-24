package token.validator.service;

import org.springframework.stereotype.Service;
import token.validator.util.TokenValidationUtil;

import java.util.logging.Logger;

import static token.validator.constants.ValidatorConstants.TOKEN_LENGTH_WITH_DASHES;

@Service
public class ValidatorService {

    Logger log = Logger.getLogger(ValidatorService.class.getName());


    public String validateToken(String token) {
        if (token.length() != TOKEN_LENGTH_WITH_DASHES) {
            return "";
        }
        return TokenValidationUtil.isValidToken(token).toString();
    }
}
