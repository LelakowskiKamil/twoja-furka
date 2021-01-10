package KupAutoSklep.demo.web.command;

import javax.validation.constraints.*;

public class CreateUserCommand {

    @Email(message = "This email address is invalid")
    private String email;

    @Size(max = 30, min = 3)
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")
    private String password;

private boolean isEnabled;

    public CreateUserCommand(){

    };

    public CreateUserCommand(String email, String username, String password, boolean isEnabled) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
