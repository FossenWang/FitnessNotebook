package fitnessnotebook;

import java.util.HashMap;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = RestController.class)
public class RestErrorHandler extends ResponseEntityExceptionHandler {
    public ResponseEntity<Object> errorToResponse(
            Exception e, HttpStatus status,
            MultiValueMap<String, String> headers) {
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("msg", e.getMessage());
        return new ResponseEntity<>(result, headers, status);
    }

    public ResponseEntity<Object> errorToResponse(
            Exception e, HttpStatus status) {
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("msg", e.getMessage());
        return new ResponseEntity<>(result, null, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handle404(EntityNotFoundException e) {
        return this.errorToResponse(e, HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleError(Exception e) {
        return this.errorToResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @Override
	public ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.errorToResponse(ex, status);
    }
}