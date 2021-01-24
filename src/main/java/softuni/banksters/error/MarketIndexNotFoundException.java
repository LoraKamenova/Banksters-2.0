package softuni.banksters.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Stock Exchange not found!")
public class MarketIndexNotFoundException extends RuntimeException{

    public MarketIndexNotFoundException() {
    }

    public MarketIndexNotFoundException(String message) {
        super(message);
    }
}
