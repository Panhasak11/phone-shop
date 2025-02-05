package com.nha.java.learning.phoneshop.service.imp;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.nha.java.learning.phoneshop.config.security.AuthUser;
import com.nha.java.learning.phoneshop.config.security.UserService;
import com.nha.java.learning.phoneshop.entity.Role;
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
//			.authorities(user.getRole().getAuthorities())
			.authorities(getAuthorities(user.getRoles()))
			.accountNonExpired(user.isAccountNonExpired())
			.accountNonLocked(user.isAccountNonLocked())
			.credentialsNonExpired(user.isCredentialsNonExpired())
			.enable(user.isEnabled())
			.build();
		
		
		return Optional.ofNullable(authUser);
	}
	
	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
		Set<SimpleGrantedAuthority> authorities1 = roles.stream()
			.map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName()))
			.collect(Collectors.toSet());
		
		Set<SimpleGrantedAuthority> authorities = roles.stream()
			.flatMap(role -> toStream(role))
			.collect(Collectors.toSet());
		authorities.addAll(authorities1);
		return authorities;
	}
	
	private Stream<SimpleGrantedAuthority> toStream(Role role){
		return role.getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getName()));
		
	}
	
}
