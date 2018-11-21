package khmil.service;

import khmil.dao.UserDao;
import khmil.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        String hashedPassword = hashPassword(user.getPassword());
        user.setToken(getToken());
        user.setPassword(hashedPassword);
        userDao.addUser(user);
    }


    @Override
    public Optional<User> getUserByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public Optional<User> verifyPassword(User getByEmail, User user) {
        String hashedPassword = hashPassword(user.getPassword());
        return hashedPassword.equals(getByEmail.getPassword())
                ? Optional.of(getByEmail)
                : Optional.empty();
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    private String hashPassword(String password) {
        MessageDigest messageDigest = null;
        byte[] encodeHash = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            encodeHash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byteToHax(encodeHash);
    }

    private String byteToHax(byte[] encodeHash) {
        StringBuffer haxString = new StringBuffer();
        for (int i = 0; i < encodeHash.length; i++) {
            String hax = Integer.toHexString(0xff & encodeHash[i]);
            if (encodeHash.length == 1) {
                haxString.append("0");
            }
            haxString.append(hax);
        }

        return haxString.toString();
    }
}
