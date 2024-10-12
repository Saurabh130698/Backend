package robo.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import robo.backend.Dto.LoginDTO;
import robo.backend.Dto.UserDTO;
import robo.backend.Response.LoginResponse;
import robo.backend.Response.SaveUserResponse;
import robo.backend.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserLoginLogoutController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> saveUsers(@RequestBody UserDTO usersDTO){
        SaveUserResponse response = userService.addUser(usersDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
}
