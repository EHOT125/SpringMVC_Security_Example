package jm.security.example.service;

import jm.security.example.model.User;

import java.util.List;

public interface UserService {

     List<User> getUsers();

     void createUser(User user);

     User findUserById(Long id);

     void delete(Long id);

     void updateUser (User user);
}
