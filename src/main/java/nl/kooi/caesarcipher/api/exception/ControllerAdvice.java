package nl.kooi.caesarcipher.api.exception;

import lombok.extern.slf4j.Slf4j;
import nl.kooi.caesarcipher.core.exception.CipheringException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(CipheringException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleCipheringException(CipheringException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail handleUncaughtRuntimeException(RuntimeException exception) {
        log.error("Something went wrong: {} \n {}", exception.getMessage(), exception.getStackTrace());

        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
    }
}
