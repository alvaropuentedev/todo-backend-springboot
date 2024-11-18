package dev.alvaropuente.backend.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.alvaropuente.backend.dto.AuthResponse;
import dev.alvaropuente.backend.dto.LoginRequest;
import dev.alvaropuente.backend.dto.RegisterRequest;
import dev.alvaropuente.backend.models.Role;
import dev.alvaropuente.backend.models.User;
import dev.alvaropuente.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class AuthService {
	
	private final UserRepository userRepository;
	
	private final JwtService jwtService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(LoginRequest loginRequest) {
    	// Authenticate the user using the AuthenticationManager
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        		loginRequest.username(),
        		loginRequest.password()));

        // Get details of the authenticated user from the UserRepository
        User user = userRepository.findByUsername(loginRequest.username()).orElseThrow();

        // get id_user from User
        Long id_user = user.getId();

        // Generate a JWT (JSON Web Token) using a specific service (jwtService)
        String token = jwtService.getToken(user);

        // Build and return an authentication response that includes only the id_user
        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .id_user(id_user)
                .build();
    }


    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .firstname(registerRequest.firstname())
                .lastname(registerRequest.lastname())
                .creationDate(LocalDateTime.now())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    @Transactional
    public void deleteUser(Long user_id) {
        userRepository.deleteById(user_id);
    }

}
