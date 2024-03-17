package loggers;

import exception.MainServiceServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

public class MainLogger {

    private Logger logger;

    public MainLogger(Class<?> tClass){
        this.logger= LogManager.getLogger(tClass);
    }

    public void log(String message){
        logger.info(message);
    }

    public void log(String message, HttpStatus httpStatus){
        logger.error(message);
        throw new MainServiceServerException(message,httpStatus);
    }
}
