package robo.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {
    @Id
    long id;
    String username;
    String email;
    String password;

    @Override
    public String toString() {
        return getUsername() + " " + getEmail();
    }
}
