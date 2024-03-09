package dev.alvaropuente.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alvaropuente.backend.dto.AuthResponse;
import dev.alvaropuente.backend.dto.LoginRequest;
import dev.alvaropuente.backend.dto.RegisterRequest;
import dev.alvaropuente.backend.services.AuthService;

@RestController
@CrossOrigin(origins = {"https://todo-angular-beta.vercel.app", "http://localhost:4200/", "https://alvaropuente.dev/"})
@RequestMapping("/apitodo/auth")
public class AuthController {
	
	@Autowired
    private AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @DeleteMapping("/user/{user_id}")
    public void deleteUser(@PathVariable(value = "user_id") Long user_id) {
        authService.deleteUser(user_id);
    }

}
