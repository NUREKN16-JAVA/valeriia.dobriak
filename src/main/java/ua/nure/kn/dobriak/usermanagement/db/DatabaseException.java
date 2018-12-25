package ua.nure.kn.dobriak.usermanagement.db;

import org.hibernate.HibernateException;

public class DatabaseException extends Exception {
    public DatabaseException() {}

    public DatabaseException(HibernateException e) {}

    public DatabaseException(String number_of_the_inserted_rows) {
    }
}

