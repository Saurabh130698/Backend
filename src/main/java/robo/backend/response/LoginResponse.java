package robo.backend.response;

public class LoginResponse {

    String statusCode;
    String authToken;
    String message;
    Boolean status;
    String userName;

    public LoginResponse(String statusCode, String userName, Boolean status, String message, String authToken) {
        this.statusCode = statusCode;
        this.userName = userName;
        this.status = status;
        this.message = message;
        this.authToken = authToken;
    }

    public LoginResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "statusCode='" + statusCode + '\'' +
                ", authToken='" + authToken + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", userName='" + userName + '\'' +
                '}';
    }
}
