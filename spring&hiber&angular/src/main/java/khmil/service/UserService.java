package khmil.service;

import khmil.model.User;

import java.util.Optional;

public interface UserService {
    void addUser(User user);

    Optional<User> getUserByEmail(String email);

    Optional<User> verifyPassword(User getByEmail, User user);

}
