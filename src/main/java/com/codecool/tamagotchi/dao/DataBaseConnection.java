package com.codecool.tamagotchi.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DataBaseConnection {
    private final SessionFactory sessionFactory;
    private Session session;
    private Configuration configuration;

    public DataBaseConnection() {
        try {
            configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void connectDB() {
        System.out.println("Connection to db" + sessionFactory.openSession());
        setSession(sessionFactory.openSession());

    }

    public List runQuery(String query) {
        return getSession().createQuery(query).list();
    }

    public void disconnectDB() {
        getSession().close();
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}