package robo.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import robo.backend.model.Users;
import robo.backend.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<String> getPublicUser(String user) {
        return ResponseEntity.ok("This is for public users");
    }

    @PostMapping("/register")
    public Users registerUsers(@RequestBody Users user) {
        return userService.registerUser(user);
    }

    /**
     * Description or summary of the class, method, or field.
     *
     * login api helps us to verify usename and  password and will return bearer to user
     */
    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return userService.verify(user);
    }
}
