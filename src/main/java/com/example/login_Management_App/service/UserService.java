package com.example.login_Management_App.service;

import com.example.login_Management_App.entity.AppUser;
import java.util.List;
import java.util.Optional;

public interface UserService {

	List<AppUser> getAllUsers();

	Optional<AppUser> getUserById(Long id);

	Optional<AppUser> getUserByUsername(String username);

	AppUser createUser(AppUser user);

	Optional<AppUser> updateUser(Long id, AppUser user);

	boolean deleteUser(Long id);
}
