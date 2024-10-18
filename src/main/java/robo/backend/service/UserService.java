package robo.backend.service;

import robo.backend.dto.LoginDTO;
import robo.backend.dto.UserDTO;
import robo.backend.response.LoginResponse;
import robo.backend.response.SaveUserResponse;

public interface UserService {

    SaveUserResponse addUser(UserDTO usersDTO);
    LoginResponse loginUser(LoginDTO loginDTO);
}
