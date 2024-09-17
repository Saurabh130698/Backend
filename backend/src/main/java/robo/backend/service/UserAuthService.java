package robo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import robo.backend.model.PrincipalUser;
import robo.backend.model.Users;
import robo.backend.repository.UserDetailRepo;

import java.util.Optional;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    UserDetailRepo userDetailRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userDetailRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PrincipalUser(user);
    }
}
