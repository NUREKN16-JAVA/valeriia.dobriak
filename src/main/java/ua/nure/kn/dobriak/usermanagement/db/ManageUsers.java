package ua.nure.kn.dobriak.usermanagement.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.nure.kn.dobriak.usermanagement.User;

import java.util.List;

public class ManageUsers {
    public static Integer create(User newUser){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer userID = null;

        try {
            tx = session.beginTransaction();
            User user = new User(newUser.getFirstName(), newUser.getLastName(), newUser.getDateOfBirth());
            userID = (Integer) session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userID;
    }

    public static List findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List users = null;
        try {
            tx = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    public static Long countAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Long count = null;
        try {
            tx = session.beginTransaction();
            count = (Long) session.createQuery("SELECT COUNT(*) FROM User").uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return count;
    }

    public static void destroy(Integer userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = session.get(User.class, userId);
            session.delete(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
