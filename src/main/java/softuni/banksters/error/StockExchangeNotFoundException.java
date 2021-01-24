package softuni.banksters.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Stock Exchange not found!")
public class StockExchangeNotFoundException extends RuntimeException{

    public StockExchangeNotFoundException() {
    }

    public StockExchangeNotFoundException(String message) {
        super(message);
    }
}
