package com.nha.java.learning.phoneshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nha.java.learning.phoneshop.entity.User;

public interface UsersRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
}
