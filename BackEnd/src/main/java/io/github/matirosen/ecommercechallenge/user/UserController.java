package io.github.matirosen.ecommercechallenge.user;

import io.github.matirosen.ecommercechallenge.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(userService.getUserDTOById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyInfo(@RequestHeader("Authorization") String token) {
        try{
            String jwt = token.substring(7);
            String userEmail = jwtService.getEmailFromToken(jwt);
            return ResponseEntity.ok(userService.getUserDTOByEmail(userEmail));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
