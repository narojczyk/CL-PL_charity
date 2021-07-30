package pl.coderslab.charity.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class SecurityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user){
        String encodedPasswd = passwordEncoder.encode(user.getPasswd());
        user.setPasswd(encodedPasswd);
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    public void passwdReset(Long id, String newPasswd){
        String encodedPasswd = passwordEncoder.encode(newPasswd);
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isPresent()){
            optUser.get().setPasswd(encodedPasswd);
        }
    }
}
