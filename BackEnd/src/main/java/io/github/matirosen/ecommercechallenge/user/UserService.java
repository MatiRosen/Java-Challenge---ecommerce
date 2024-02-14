package io.github.matirosen.ecommercechallenge.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserDTO getUserDTOById(Long id) {
        return new UserDTO(getUserById(id));
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO getUserDTOByEmail(String email) {
        return new UserDTO(getUserByEmail(email));
    }

    private User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
