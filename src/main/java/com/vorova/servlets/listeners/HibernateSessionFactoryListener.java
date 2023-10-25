package com.vorova.servlets.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HibernateSessionFactoryListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("com.vorova.config.HibernateUtil");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
