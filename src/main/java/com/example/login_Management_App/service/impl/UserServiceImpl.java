package com.example.login_Management_App.service.impl;

import com.example.login_Management_App.entity.AppUser;
import com.example.login_Management_App.repository.UserRepository;
import com.example.login_Management_App.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<AppUser> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<AppUser> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<AppUser> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public AppUser createUser(AppUser user) {
		user.setId(null);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Optional<AppUser> updateUser(Long id, AppUser user) {
		return userRepository.findById(id)
				.map(existingUser -> {
					existingUser.setUsername(user.getUsername());
					existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
					return userRepository.save(existingUser);
				});
	}

	@Override
	public boolean deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			return false;
		}

		userRepository.deleteById(id);
		return true;
	}
}
