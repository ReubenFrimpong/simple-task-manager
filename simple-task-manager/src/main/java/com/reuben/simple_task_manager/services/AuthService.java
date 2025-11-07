package com.reuben.simple_task_manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.reuben.simple_task_manager.dtos.AuthRequest;
import com.reuben.simple_task_manager.dtos.AuthResponse;
import com.reuben.simple_task_manager.dtos.UserResponse;
import com.reuben.simple_task_manager.models.User;
import com.reuben.simple_task_manager.repositories.UserRepository;
import com.reuben.simple_task_manager.security.JwtUtil;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  public AuthResponse login(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password())
    );

    final UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
    String token = jwtUtil.generateToken(userDetails);
    User user = userRepository.findByUsername(request.username()).orElse(null);
    return new AuthResponse(token, new UserResponse(user.getId(), user.getUsername()));
  }

  public User register(AuthRequest request) {
    var user = new User();
    user.setUsername(request.username());
    user.setPassword(new BCryptPasswordEncoder().encode(request.password())); 
    return userRepository.save(user);
  }
}
