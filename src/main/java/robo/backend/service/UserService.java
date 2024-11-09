package robo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import robo.backend.model.Users;
import robo.backend.repository.UserDetailRepo;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDetailRepo userDetailRepo;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTService jwtService;

    public Users registerUser(Users user) {
        return userDetailRepo.save(user);
    }

    public String verify(Users user) {
        Authentication auth =
                authenticationManager.authenticate(
                                                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (auth.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }

        return "Invalid username or password";
    }
}
