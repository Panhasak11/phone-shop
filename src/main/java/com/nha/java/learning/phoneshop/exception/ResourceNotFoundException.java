package com.nha.java.learning.phoneshop.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException{

	public ResourceNotFoundException(String resourceName, Long id) {
<<<<<<< HEAD
		super(HttpStatus.NOT_FOUND, "Brand with id = %d not found".formatted(id));
=======
		super(HttpStatus.NOT_FOUND, String.format("%s with id = %d not found",resourceName, id));
>>>>>>> main
		
	}

}
