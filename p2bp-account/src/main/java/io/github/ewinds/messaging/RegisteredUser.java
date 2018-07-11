package io.github.ewinds.messaging;

public class RegisteredUser {
    private String login;

    private String phone;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RegisteredUser() {
    }

    public RegisteredUser(String login, String phone) {
        this.login = login;
        this.phone = phone;
    }
}
