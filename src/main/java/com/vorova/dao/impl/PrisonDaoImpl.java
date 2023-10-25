package com.vorova.dao.impl;

import com.vorova.config.HibernateUtil;
import com.vorova.dao.PrisonDao;
import com.vorova.model.entity.PrisonModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PrisonDaoImpl implements PrisonDao {

    @Override
    public Long persist(PrisonModel prison) {
        Session session = HibernateUtil.session();

        session.beginTransaction();
        session.persist(prison);
        session.getTransaction().commit();

        session.close();

        return prison.getId();
    }

    @Override
    public void update(PrisonModel prison) {
        Session session = HibernateUtil.session();

        session.beginTransaction();
        session.merge(prison);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void delete(Long prisonId) {
        Session session = HibernateUtil.session();

        session.beginTransaction();
        PrisonModel prison = session.get(PrisonModel.class, prisonId);
        session.remove(prison);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public Optional<PrisonModel> findById(Long id) {
        try (Session session = HibernateUtil.session()){

            session.beginTransaction();

            session.setDefaultReadOnly(true);
            PrisonModel prison = session.get(PrisonModel.class, id);
            session.getTransaction().commit();

            return Optional.of(prison);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PrisonModel> findAll() {
        final String SELECT_SQL = "from PrisonModel as prison";

        Session session = HibernateUtil.session();

        Query<PrisonModel> query = session.createQuery(SELECT_SQL, PrisonModel.class);
        List<PrisonModel> prisons = query.getResultList();

        session.close();
        return prisons;
    }
}
