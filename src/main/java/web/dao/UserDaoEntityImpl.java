package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.Role;
import web.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository("userDao")
public class UserDaoEntityImpl implements UserDao{
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        User us = entityManager.find(User.class, id);
        entityManager.remove(us);
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }


    @Transactional
    @Override
    public User getUserByEmail(String email) {
        return (User) entityManager.createQuery("Select u from User u where u.email = : email").setParameter("email", email).getSingleResult();
    }
    @Transactional
    @Override
    public List<User> listUsers() {
        Query query = (Query) entityManager.createQuery("SELECT u FROM User u", User.class);

        return (List<User>) query.getResultList();
    }
    @Override
    public Set<Role> getRoles(){
        return null;
    }

}

