package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

     @ExceptionHandler(Exception.class)
     public final ResponseEntity<Object> handleAppException(Exception ex, WebRequest request) {
         if(ex instanceof AppException) {
             AppException appException = (AppException) ex;
             log.error("Error Code: {}, Type: {}, Message: {}", appException.getCodigo(), appException.getType(), appException.getMessage());
             return new ResponseEntity<>(ApiResponse.error(appException.getMessage(),appException.getCodigo(), appException.getType()), HttpStatus.INTERNAL_SERVER_ERROR);
         } else {
             log.error("Unexpected error occurred: {}", ex.getMessage(), ex);
                return new ResponseEntity<>(ApiResponse.error("An unexpected error occurred " + ex), HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

}
