package com.nha.java.learning.phoneshop.config.security;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.nha.java.learning.phoneshop.config.security.PermissionEnum.*;

@Getter
//@AllArgsConstructor
public enum RoleEnum {
	
	ADMIN(Set.of(BRAND_WRITE,BRAND_READ,MODEL_WRITE,MODEL_READ)),
	SALE(Set.of(BRAND_READ,MODEL_READ));
	
	private Set<PermissionEnum> permission;
	
	private RoleEnum(Set<PermissionEnum> permission) {
		this.permission = permission;
	}
	
	public Set<SimpleGrantedAuthority> getAuthorities(){
		Set<SimpleGrantedAuthority> authorities = this.permission.stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
			.collect(Collectors.toSet());
		
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + this.name());
		authorities.add(role);
		System.out.println(authorities);
		
		return authorities;
	}



	
	
	
}
