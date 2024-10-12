package robo.backend.Response;

public class SaveUserResponse {
    String message;
    String username;
    Boolean status;

    public SaveUserResponse(String message, String username, Boolean status) {
        this.message = message;
        this.username = username;
        this.status = status;
    }

    public SaveUserResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SaveUserResponse{" +
                "message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", status=" + status +
                '}';
    }
}
