package KupAutoSklep.demo.data;

import KupAutoSklep.demo.domain.model.Offer;

import java.util.List;

public class UserSummary {

    private String email;
    private String username;
    private String password;
    private boolean isEnabled;

    public UserSummary(String email, String username, String password, boolean isEnabled) {
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
