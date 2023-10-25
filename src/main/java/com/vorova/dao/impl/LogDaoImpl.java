package com.vorova.dao.impl;

import com.vorova.config.HibernateUtil;
import com.vorova.dao.LogDao;
import com.vorova.model.entity.LogModel;
import org.hibernate.Session;

public class LogDaoImpl implements LogDao {

    @Override
    public void addLog(LogModel logModel) {
        Session session = HibernateUtil.session();

        session.beginTransaction();
        session.persist(logModel);
        session.getTransaction().commit();

        session.close();
    }
}
