package bookstore.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GeneralException extends RuntimeException{

    private final String message;
    private final HttpStatus status;

    public GeneralException (String message,HttpStatus status){
        this.message = message;
        this.status = status;
    }

}
