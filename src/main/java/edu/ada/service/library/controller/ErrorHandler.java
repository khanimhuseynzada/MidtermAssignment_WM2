package edu.ada.service.library.controller;

import edu.ada.service.library.exception.InvalidCredentialsException;
import edu.ada.service.library.exception.InvalidPickupException;
import edu.ada.service.library.exception.NotExistsException;
import edu.ada.service.library.exception.UnauthorizedException;
import edu.ada.service.library.model.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletRequest;


@RestControllerAdvice
public class ErrorHandler {
    private Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handle(ServletRequest request, MethodArgumentNotValidException exception) {
        logger.debug(request.toString());
        logger.error(exception.getMessage());

        return ErrorResponse
                .builder()
                .message("Invalid params")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCredentialsException.class)
    public ErrorResponse handle(ServletRequest request, InvalidCredentialsException exception) {
        logger.debug(request.toString());
        logger.error(exception.getMessage());

        return ErrorResponse
                .builder()
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotExistsException.class)
    public ErrorResponse handle(ServletRequest request, NotExistsException exception) {
        logger.debug(request.toString());
        logger.error(exception.getMessage());

        return ErrorResponse
                .builder()
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidPickupException.class)
    public ErrorResponse handle(ServletRequest request, InvalidPickupException exception) {
        logger.debug(request.toString());
        logger.error(exception.getMessage());

        return ErrorResponse
                .builder()
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handle(ServletRequest request, UnauthorizedException exception) {
        logger.debug(request.toString());
        logger.error(exception.getMessage());

        return ErrorResponse
                .builder()
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handle(ServletRequest request, Exception exception) {
        logger.debug(request.toString());
        logger.error(exception.getMessage());

        return ErrorResponse
                .builder()
                .message("Something went wrong")
                .build();
    }
}
