package robo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import robo.backend.model.User;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserDetailRepo extends JpaRepository<User, Long> {

    Optional<User> findOneByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    User findByEmail(String email);
}

