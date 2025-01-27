package com.nha.java.learning.phoneshop.config.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService{

	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<AuthUser> findUserByUsername(String username) {
		
		List<AuthUser> users = List.of(
			new AuthUser("dara",passwordEncoder.encode("dara123"),RoleEnum.SALE.getAuthorities(),true,true,true,true),
			new AuthUser("khann",passwordEncoder.encode("khann123"),RoleEnum.ADMIN.getAuthorities(),true,true,true,true)

				);
		
		return users.stream()
			.filter(user -> user.getUsername().equals(username))
			.findFirst();
	}

}
