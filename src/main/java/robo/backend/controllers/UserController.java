package robo.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import robo.backend.dto.LoginDTO;
import robo.backend.dto.UserDTO;
import robo.backend.response.LoginResponse;
import robo.backend.response.SaveUserResponse;
import robo.backend.service.UserAuthService;
import robo.backend.service.UserService;
import robo.backend.service.Impl.UserImpl;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired 
    UserImpl userImpl;


    @GetMapping("/")
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
            return new ResponseEntity<>(loginResponse,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }

}
