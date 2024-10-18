package robo.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import robo.backend.dto.LoginDTO;
import robo.backend.dto.UserDTO;
import robo.backend.response.LoginResponse;
import robo.backend.response.SaveUserResponse;
import robo.backend.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserLoginLogoutController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> saveUsers(@RequestBody UserDTO usersDTO)
    {
        SaveUserResponse response = userService.addUser(usersDTO);
        if(response.getStatus() == false){
            return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        if(loginResponse.getStatus() == false){
            return new ResponseEntity<>(loginResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }


    

}
