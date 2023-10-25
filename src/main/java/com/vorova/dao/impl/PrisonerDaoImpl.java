package com.vorova.dao.impl;

import com.vorova.config.HibernateUtil;
import com.vorova.dao.PrisonerDao;
import com.vorova.model.entity.PrisonerModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PrisonerDaoImpl implements PrisonerDao {

    @Override
    public Long persist(PrisonerModel prisoner) {
        Session session = HibernateUtil.session();

        session.beginTransaction();
        session.persist(prisoner);
        session.getTransaction().commit();

        session.close();

        return null;
    }

    @Override
    public void update(PrisonerModel prisoner) {
        Session session = HibernateUtil.session();

        session.beginTransaction();
        session.merge(prisoner);
        session.beginTransaction().commit();

        session.close();
    }

    @Override
    public void delete(Long prisonerId) {
        Session session = HibernateUtil.session();

        session.beginTransaction();
        PrisonerModel prisoner = session.get(PrisonerModel.class, prisonerId);
        session.remove(prisoner);
        session.beginTransaction().commit();

        session.close();
    }

    @Override
    public Optional<PrisonerModel> findById(Long prisonerId) {
        try (Session session = HibernateUtil.session()){

            session.beginTransaction();

            session.setDefaultReadOnly(true);
            PrisonerModel prisoner = session.get(PrisonerModel.class, prisonerId);
            session.getTransaction().commit();

            return Optional.of(prisoner);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PrisonerModel> findAllByPrisonId(long prisonId) {
        final String SELECT_SQL = "from PrisonerModel";

        Session session = HibernateUtil.session();

        Query<PrisonerModel> query = session.createQuery(SELECT_SQL, PrisonerModel.class);
        List<PrisonerModel> prisoners = query.getResultList();

        session.close();
        return prisoners;
    }
}
