package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserByModelAndSeries(String carModel, int carSeries) {
        TypedQuery<User> query = sessionFactory
                .getCurrentSession()
                .createQuery(
                        "SELECT u FROM User u WHERE u.car.model= :model and u.car.series=: series", User.class
                );
        query.setParameter("model", carModel);
        query.setParameter("series", carSeries);
        return query.getResultList();
    }
}