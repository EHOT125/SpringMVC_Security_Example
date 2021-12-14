package jm.security.example.service;

import jm.security.example.dao.UserDao;
import jm.security.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    @Transactional
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }
}
