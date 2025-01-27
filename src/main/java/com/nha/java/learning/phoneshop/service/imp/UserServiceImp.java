package com.nha.java.learning.phoneshop.service.imp;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nha.java.learning.phoneshop.config.security.AuthUser;
import com.nha.java.learning.phoneshop.config.security.UserService;
import com.nha.java.learning.phoneshop.entity.User;
import com.nha.java.learning.phoneshop.exception.ApiException;
import com.nha.java.learning.phoneshop.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

	private final UsersRepository usersRepository;
	
	@Override
	public Optional<AuthUser> findUserByUsername(String username) {
		User user = usersRepository.findByUsername(username)
			.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found in DB"));
		
		AuthUser authUser = AuthUser.builder()
			.username(user.getUsername())
			.password(user.getPassword())
			.authorities(user.getRole().getAuthorities())
			.accountNonExpired(user.isAccountNonExpired())
			.accountNonLocked(user.isAccountNonLocked())
			.credentialsNonExpired(user.isCredentialsNonExpired())
			.enable(user.isEnabled())
			.build();
		
		
		return Optional.ofNullable(authUser);
	}

}
