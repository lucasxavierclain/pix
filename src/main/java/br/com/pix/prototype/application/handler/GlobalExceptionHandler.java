package br.com.pix.prototype.application.handler;

import br.com.pix.prototype.domain.enums.Erros;
import br.com.pix.prototype.domain.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {




    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handlerServiceException(ServiceException exception, WebRequest req){
        Map<String, String> result= new HashMap<>();
        result.put("Error code:", exception.getCodeError());
        result.put("Error message", exception.getMessage());
        return new ResponseEntity<>(result, HttpStatus.PRECONDITION_REQUIRED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerAllException(Exception exception, WebRequest req){
        Map<String, String> result= new HashMap<>();
        result.put("Error code:", Erros.ERROR_013.getCode());
        result.put("Error message", exception.getMessage());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
