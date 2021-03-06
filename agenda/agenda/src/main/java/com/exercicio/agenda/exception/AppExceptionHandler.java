package com.exercicio.agenda.exception;

import java.util.Date;

import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handlerAllException(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value={UnsupportedOperationException.class, MethodArgumentTypeMismatchException.class, NumberFormatException.class})
	public final ResponseEntity<ExceptionResponse> handlerBadRequestException(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	

}
