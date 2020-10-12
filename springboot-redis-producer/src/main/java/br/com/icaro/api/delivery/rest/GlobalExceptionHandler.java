package br.com.icaro.api.delivery.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var message = Optional.ofNullable(ex.getCause()).orElse(ex).toString();
		
		return handleExceptionInternal(ex, message, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> messages = new ArrayList<>();
		
		ex.getBindingResult().getGlobalErrors().forEach(error -> messages.add(error.getDefaultMessage()));
		ex.getBindingResult().getFieldErrors().forEach(error -> messages.add(error.getField() + ": " + error.getDefaultMessage()));
		
		return handleExceptionInternal(ex, messages, headers, HttpStatus.BAD_REQUEST, request);
	}
}
