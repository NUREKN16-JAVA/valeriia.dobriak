package ua.nure.kn.dobriak.usermanagement.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.nure.kn.dobriak.usermanagement.User;

public class ManageUsers {
    private static HibernateUtil hibernateUtil;

    public ManageUsers() {
        try {
            HibernateUtil hibernateUtil = new HibernateUtil();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

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
}
