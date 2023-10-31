package com.prm392.gearcom.api.controller.auth;

import com.prm392.gearcom.api.model.LoginBody;
import com.prm392.gearcom.api.model.LoginResponse;
import com.prm392.gearcom.api.model.RegistrationBody;
import com.prm392.gearcom.model.User;
import com.prm392.gearcom.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService _userService) {
        this.userService = _userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            userService.register(registrationBody);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginBody loginBody) {
        String jwt;
        jwt = userService.login(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/profile")
    public User getLoggedInUserProfile(@AuthenticationPrincipal User user) {
        return user;
    }

}
