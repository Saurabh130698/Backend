package robo.backend.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import robo.backend.Dto.LoginDTO;
import robo.backend.Dto.UserDTO;
import robo.backend.Entity.User;
import robo.backend.Response.LoginResponse;
import robo.backend.Response.SaveUserResponse;
import robo.backend.repository.UserDetailRepo;
import robo.backend.service.UserService;

import java.util.Optional;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserDetailRepo userDetailRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SaveUserResponse addUser(UserDTO usersDTO) {
       User user= new User(
               usersDTO.getId(),
               usersDTO.getEmail(),
               usersDTO.getUsername(),
               this.passwordEncoder.encode(usersDTO.getPassword())
       );
        User checkEmail =  userDetailRepo.findByEmail(usersDTO.getEmail());
        User user1 =userDetailRepo.findByUsername(usersDTO.getUsername());
        if(checkEmail != null){
            return new SaveUserResponse("Email is already registered","", false);
        }else if(user1 != null){
            return new SaveUserResponse("Username is already taken","", false);
        }else{
            userDetailRepo.save(user);
            return new SaveUserResponse("Success",usersDTO.getUsername(), true);
        }
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userDetailRepo.findByUsername(loginDTO.getUsername());
        if(user1 != null){
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight =passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight){
                Optional<User> user = userDetailRepo.findOneByUsernameAndPassword(loginDTO.getUsername(), encodedPassword);
                if(user.isPresent()){
                    return new LoginResponse("Login Success", true);
                }else {
                    return new LoginResponse("Login Failed", false);
                }
            }else {
                return new LoginResponse("Password Not Match", false);
            }
        }else {
            return new LoginResponse("Username Not exits", false);
        }
    }
}