package com.project.TravelEase.service;
 
import com.project.TravelEase.model.User;
import com.project.TravelEase.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class UserService {
 
    @Autowired
    private UserRepository userRepository;
 
    // Register a new user
    public User registerUser(User user) {
        // Here you can add more logic, like password hashing before saving
        return userRepository.save(user);
    }
 
    // Authenticate the user based on username and password
    public boolean authenticateUser(User user) {
        // Retrieve the user from the database by username
        User existingUser = userRepository.findByEmail(user.getEmail());
 
        // If user is found and the password matches
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return true;  // Authentication success
        }
        return false;  // Authentication failed
    }

	public Optional<User> getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}