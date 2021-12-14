package jm.security.example.dao;

import jm.security.example.model.User;

import java.util.List;

public interface UserDao {
    User getUserByName(String name);

    List<User> getUsers();

    void createUser(User user);

    User findUserById(Long id);

    void delete(Long id);
}
