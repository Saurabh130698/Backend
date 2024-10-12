package robo.backend.service;

import robo.backend.Dto.LoginDTO;
import robo.backend.Dto.UserDTO;
import robo.backend.Response.SaveUserResponse;
import robo.backend.Response.LoginResponse;

public interface UserService {

    SaveUserResponse addUser(UserDTO usersDTO);
    LoginResponse loginUser(LoginDTO loginDTO);
}
