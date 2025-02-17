package com.project.TravelEase.service;

import com.project.TravelEase.model.User;
import com.project.TravelEase.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired private UserRepository userRepository;

  public User registerUser(User user) {
    return userRepository.save(user);
  }

  public Optional<User> getUserById(Long userId) {
    return userRepository.findById(userId);
  }

  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}