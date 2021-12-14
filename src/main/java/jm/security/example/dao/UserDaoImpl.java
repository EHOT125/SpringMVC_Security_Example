package jm.security.example.dao;

import jm.security.example.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("from User uzer where uzer.name =: name", User.class)
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void createUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findUserById(id));
    }
}

