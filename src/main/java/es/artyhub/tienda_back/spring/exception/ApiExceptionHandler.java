package es.artyhub.tienda_back.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.artyhub.tienda_back.domain.exception.ResourceNotFoundException;
import es.artyhub.tienda_back.domain.exception.ValidationException;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorMessage(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ErrorMessage handleValidationException(Exception ex) {
        return new ErrorMessage(ex);
    }

}
