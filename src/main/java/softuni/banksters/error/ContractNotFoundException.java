package softuni.banksters.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Contract not found!")
public class ContractNotFoundException extends RuntimeException{

    public ContractNotFoundException() {
    }

    public ContractNotFoundException(String message) {
        super(message);
    }
}
