package khmil.controller.externalModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import khmil.model.User;

import javax.validation.constraints.NotNull;

public class UserDto {
    private Long id;
    @NotNull
    private String email;
    @NotNull
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String verifyPassword;
    private String token;
    private String firstName;
    private String lastName;

    private UserDto(String email, String password, String verifyPassword, String token, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.verifyPassword = verifyPassword;
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static UserDto of(User user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        return dto;
    }
}
