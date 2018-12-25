package ua.nure.kn.dobriak.usermanagement.db;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.nure.kn.dobriak.usermanagement.User;

import java.util.Collection;
import java.util.LinkedList;
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

    public static void update(Integer userId, User editedUser){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = session.get(User.class, userId);
            user.clone(editedUser);
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public static User find(Integer userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();
            user = session.get(User.class, userId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public Collection<User> find(String firstName, String lastName) throws DatabaseException {
        Transaction tx = null;
        Collection<User> users = new LinkedList<>();
        try {
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT id, first_name, last_name, birthday FROM TEST.USERS WHERE first_name = :firstName AND last_name = :lastName");
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            users = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        return users;
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
