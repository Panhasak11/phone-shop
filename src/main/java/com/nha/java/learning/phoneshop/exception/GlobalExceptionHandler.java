package com.nha.java.learning.phoneshop.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ApiException.class) 
	public ResponseEntity<?> handleApiException(ApiException e){
		ErrorRespone errorRespone = new ErrorRespone(e.getStatus(), e.getMessage());
		return ResponseEntity
				.status(e.getStatus())
				.body(errorRespone);
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException e){
//		List<String> error = e.getBindingResult()
//				.getFieldErrors()
//				.stream()
//				.map(FieldError::getDefaultMessage)
//				.collect(Collectors.toList());
//		return new ResponseEntity<>(getErrorsMap(error), new HttpHeaders(), HttpStatus.BAD_REQUEST);
//	}
//	
//	private Map<String, List<String>> getErrorsMap(List<String> errors) {
//        Map<String, List<String>> errorResponse = new HashMap<>();
//        errorResponse.put("errors", errors);
//        return errorResponse;
//    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
		Map<String, String> allError = new HashMap<>();
		ex.getBindingResult()
			.getAllErrors()
			.forEach(error -> {
				String fieldName = ((FieldError) error).getDefaultMessage();
				String errorMessage = error.getDefaultMessage();
				allError.put(fieldName, errorMessage);
			});
		return new ResponseEntity<>(allError,HttpStatus.BAD_REQUEST);
	}
}
