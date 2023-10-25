package com.vorova.config;

import com.vorova.model.entity.LogModel;
import com.vorova.model.entity.PrisonModel;
import com.vorova.model.entity.PrisonerModel;
import com.vorova.model.entity.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {
    private static final String URL = "jdbc:postgresql://localhost:5434/postgres";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final SessionFactory sf;

    static {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserModel.class);
        configuration.addAnnotatedClass(PrisonModel.class);
        configuration.addAnnotatedClass(PrisonerModel.class);
        configuration.addAnnotatedClass(LogModel.class);

        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.connection.url", URL);
        properties.setProperty("hibernate.connection.username", USER);
        properties.setProperty("hibernate.connection.password", PASSWORD);
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.transaction.jta.platform",
                "org.hibernate.engine.transaction.jta.platform.internal.SunOneJtaPlatform");

        configuration.setProperties(properties);

        sf = configuration.buildSessionFactory();
    }

    public static Session session() {
        return sf.openSession();
    }
}
