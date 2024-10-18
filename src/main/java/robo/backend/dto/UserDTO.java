<<<<<<<< HEAD:src/main/java/robo/backend/model/User.java
package robo.backend.model;
========
package robo.backend.dto;
>>>>>>>> c403f79 (updated Http status code on basis of response and validation):src/main/java/robo/backend/dto/UserDTO.java

public class UserDTO {

    private long id;
    private String username;
    private String email;
    private String password;

    public UserDTO(long id, String password, String email, String username) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.username = username;
    }

    public UserDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsersDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
