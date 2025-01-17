package com.nha.java.learning.phoneshop.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
//@AllArgsConstructor
public enum PermissionEnum {

	
	BRAND_WRITE("brand:write"),
	BRAND_READ("brand:read"),
	MODEL_WRITE("model:write"),
	MODEL_READ("model:read");

	
	private String description;
	
	private PermissionEnum(String description) {
		this.description = description;
	}
	
}
