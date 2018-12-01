package ua.nure.kn.dobriak.usermanagement.db;

import org.hibernate.HibernateException;

public class ManageUsers {
    private static HibernateUtil hibernateUtil;

    public ManageUsers() {
        try {
            HibernateUtil hibernateUtil = new HibernateUtil();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
