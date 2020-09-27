package net.mkhalili96.dynamic_role_hierarchy.common.exception;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorDto;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionWrapper extends ResponseEntityExceptionHandler {

//    @Autowired
//    Tracer tracer;

    // this method will handle all exceptions
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDto> handleAllExceptions(Exception e, HttpServletRequest request, HttpServletResponse response) {

        // error template
        ErrorDto error = new ErrorDto();
        // if it's a custom exception
        if (e instanceof ExceptionsTemplate) {
            ExceptionsTemplate template = (ExceptionsTemplate) e;
            error.setMessage(template.getMessage());
            error.setError(template.getError());
            error.setStatus(template.getStatus());
//            error.setTraceId(tracer.currentSpan().context().traceIdString());

            return new ResponseEntity(error, ErrorMap.getHttpStatus(template.getStatus()));
        }
        if (e instanceof AccessDeniedException) {
            error.setError(ErrorMap.ACCESS_DENIED);
            error.setStatus(ErrorMap.ACCESS_DENIED.value());
            error.setMessage(e.getMessage());
//            error.setTraceId(tracer.currentSpan().context().traceIdString());

            return new ResponseEntity(error, HttpStatus.FORBIDDEN);
        }
        if (e instanceof ObjectOptimisticLockingFailureException) {
            error.setError(ErrorMap.VERSION_NOT_MATCH);
            error.setStatus(ErrorMap.VERSION_NOT_MATCH.value());
            error.setMessage("input version field must be same as current version");
//            error.setTraceId(tracer.currentSpan().context().traceIdString());

            return new ResponseEntity(error, HttpStatus.FORBIDDEN);
        }
        if (e instanceof IllegalArgumentException) {
            error.setError(ErrorMap.Unauthorized);
            error.setStatus(ErrorMap.Unauthorized.value());
            error.setMessage("Unable to get JWT Token");
//            error.setTraceId(tracer.currentSpan().context().traceIdString());

            return new ResponseEntity(error, HttpStatus.FORBIDDEN);
        }

        if (e instanceof SignatureException) {
            error.setError(ErrorMap.Unauthorized);
            error.setStatus(ErrorMap.Unauthorized.value());
            error.setMessage("Unauthorized Access");
//            error.setTraceId(tracer.currentSpan().context().traceIdString());

            return new ResponseEntity(error, HttpStatus.FORBIDDEN);
        }
        // if an unexpected exception happened
        else {
            error.setStatus(ErrorMap.INTERNAL_SERVER_ERROR.value());
            error.setError(ErrorMap.INTERNAL_SERVER_ERROR);
            System.out.println("exception happened : " + e.getMessage());
            error.setMessage("ERROR");
//            error.setTraceId(tracer.currentSpan().context().traceIdString());
            e.printStackTrace();

            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDto error = new ErrorDto();

        error.setStatus(ErrorMap.BAD_REQUEST.value());
        error.setError(ErrorMap.BAD_REQUEST);

        Map<String, String> errors = new HashMap<String, String>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        error.setMessage(errors);

        return new ResponseEntity<>(error, headers, status);
    }

}
