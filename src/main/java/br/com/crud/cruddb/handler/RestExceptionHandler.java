package br.com.crud.cruddb.handler;

import br.com.crud.cruddb.model.error.ErrorMessage;
import br.com.crud.cruddb.model.exception.ResourceBadRequestException;
import br.com.crud.cruddb.model.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundEntity(ResourceNotFoundException ex){
        ErrorMessage error = new ErrorMessage("Not Found",404,ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<?> handleResourceBadRequest(ResourceBadRequestException ex){
        ErrorMessage error = new ErrorMessage("Bad Request",400,ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
