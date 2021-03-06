package org.bugtracker.bugtracker.model.exceptions;

import org.bugtracker.bugtracker.model.exceptions.custom.BoardAlreadyExistException;
import org.bugtracker.bugtracker.model.exceptions.custom.TaskException;
import org.bugtracker.bugtracker.model.exceptions.custom.UserRegistrationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Error", ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<Object> handleUserRegistrationException(UserRegistrationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "We can't create user with this credentials", ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<Object> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Error", ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BoardAlreadyExistException.class)
    public ResponseEntity<Object> handleBoardAlreadyExistException(BoardAlreadyExistException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Error", ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<Object> handleCreateTaskException(TaskException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Error", ex);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError error) {
        return new ResponseEntity<>(error, error.getStatusCode());
    }
}
