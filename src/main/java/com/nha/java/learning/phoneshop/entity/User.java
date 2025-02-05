package com.nha.java.learning.phoneshop.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.nha.java.learning.phoneshop.config.security.RoleEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "tbUser")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;

//	@Enumerated(EnumType.STRING)
//	private RoleEnum role;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER	)
	private Set<Role> roles;
}
