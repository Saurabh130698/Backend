package robo.backend.controllers;

import lombok.extern.flogger.Flogger;
import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import robo.backend.dto.LoginDTO;
import robo.backend.dto.UserDTO;
import robo.backend.model.User;
import robo.backend.response.LoginResponse;
import robo.backend.response.SaveUserResponse;
import robo.backend.service.UserAuthService;
import robo.backend.service.UserService;
import robo.backend.service.Impl.UserImpl;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserService userService;

    @Autowired 
    UserImpl userImpl;

    @GetMapping("/health/check")
    public String getPublicUser() {
        return "Test authentication";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO usersDTO)
    {
        SaveUserResponse response = userService.addUser(usersDTO);
        if(!response.getStatus()){
            return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        if(!loginResponse.getStatus()){
            logger.info("login failed");
            return new ResponseEntity<>(loginResponse,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user =  userService.getUserById(id);
        return ResponseEntity.ok(user);
    }





}
