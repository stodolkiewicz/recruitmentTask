package github.info.client.exception.handler;

import github.info.client.exception.ErrorDetail;
import github.info.client.exception.RepositoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RepositoryNotFoundException.class)
    public ResponseEntity<?> handleRepositoryNotFoundException(RepositoryNotFoundException rnfe, HttpServletRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().toString());
        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }
}
