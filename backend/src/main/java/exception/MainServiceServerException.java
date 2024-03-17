package exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class MainServiceServerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 45487L;

    private HttpStatus errorCode;

    public MainServiceServerException(){
        super("Unexpected Exception Encountered");
    }

    public MainServiceServerException(String message,HttpStatus errorCode){
        super(message);
        this.errorCode=errorCode;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }
}