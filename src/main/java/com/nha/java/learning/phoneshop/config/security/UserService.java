package com.nha.java.learning.phoneshop.config.security;

import java.util.Optional;

public interface UserService {

	Optional<AuthUser> findUserByUsername(String username);
}
