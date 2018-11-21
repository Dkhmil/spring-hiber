package khmil.dao;

import khmil.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional
public class UserDapImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);

    }

    @Override
    public Optional<User> getByEmail(String email) {
        User user = (User) sessionFactory.getCurrentSession().
                createQuery(" from User  u join fetch u.roles where email =: email ")
                .setParameter("email", email)
                .uniqueResult();
        return Optional.ofNullable(user);
    }
}
