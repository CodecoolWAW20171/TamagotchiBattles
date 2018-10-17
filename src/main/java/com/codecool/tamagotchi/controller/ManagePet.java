package com.codecool.tamagotchi.controller;

import com.codecool.tamagotchi.model.tamagotchi.Pet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManagePet {

    private static SessionFactory factory;

    public ManagePet() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /* Method to CREATE pet in the database */
    public Integer addPet(Pet pet){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer petID = null;

        try {
            tx = session.beginTransaction();
            petID = (Integer) session.save(pet);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return petID;
    }

}
