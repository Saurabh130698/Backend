package robo.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robo.backend.model.Users;

@Repository
public interface UserDetailRepo extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);
}

