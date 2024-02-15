package io.github.matirosen.ecommercechallenge.auth;

import io.github.matirosen.ecommercechallenge.jwt.JwtService;
import io.github.matirosen.ecommercechallenge.user.User;
import io.github.matirosen.ecommercechallenge.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (!isValidPassword(request.getPassword())) {
            throw new RuntimeException("Invalid password. Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one number.");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .receiveNewsletter(request.isReceiveNewsletter())
                .build();


        userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Email already in use");
        });

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
    }
}
