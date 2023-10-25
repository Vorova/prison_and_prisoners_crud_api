package com.vorova.dao.impl;

import com.vorova.config.HibernateUtil;
import com.vorova.dao.UserDao;
import com.vorova.model.entity.UserModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public Optional<UserModel> findByLogin(String login) {
        String SQL_SELECT = "from UserModel as user where user.login = :login";

        try (Session session = HibernateUtil.session()) {
            session.beginTransaction();

            Query<UserModel> query = session.createQuery(SQL_SELECT, UserModel.class);
            query.setParameter("login", login);
            UserModel user = query.getSingleResult();

            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
