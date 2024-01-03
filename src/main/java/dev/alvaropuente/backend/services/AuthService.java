package dev.alvaropuente.backend.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.alvaropuente.backend.auth.AuthResponse;
import dev.alvaropuente.backend.auth.LoginRequest;
import dev.alvaropuente.backend.auth.RegisterRequest;
import dev.alvaropuente.backend.models.Role;
import dev.alvaropuente.backend.models.User;
import dev.alvaropuente.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse login(LoginRequest request) {
    	// Authenticate the user using the AuthenticationManager
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        // Get details of the authenticated user from the UserRepository
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

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


    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}
