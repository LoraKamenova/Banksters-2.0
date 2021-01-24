package softuni.banksters.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Analysis not found!")
public class AnalysisNotFoundException extends RuntimeException{

    public AnalysisNotFoundException() {
    }

    public AnalysisNotFoundException(String message) {
        super(message);
    }
}
