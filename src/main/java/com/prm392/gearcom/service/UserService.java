package com.prm392.gearcom.service;

import com.prm392.gearcom.api.model.LoginBody;
import com.prm392.gearcom.api.model.RegistrationBody;
import com.prm392.gearcom.model.User;
import com.prm392.gearcom.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;

    public UserService(UserRepository _userRepository,
                       EncryptionService _encryptionService,
                       JWTService _jwtService) {
        this.userRepository = _userRepository;
        this.encryptionService = _encryptionService;
        this.jwtService = _jwtService;
    }

    public User register(@Valid RegistrationBody registrationBody) throws Exception {
        if (userRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()
                || userRepository.findByPhone(registrationBody.getPhone()).isPresent()) {
            throw new Exception();
        }

        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setName(registrationBody.getName());
        user.setPhone(registrationBody.getPhone());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));

        return userRepository.save(user);
    }

    public String login(@Valid LoginBody loginBody) {
        Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
