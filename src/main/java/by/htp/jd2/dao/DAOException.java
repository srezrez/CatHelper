package by.htp.jd2.dao;

public class DAOException extends Exception {
    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(Exception e, String message) {
        super(message, e);
    }

    public DAOException(String message) {
        super(message);
    }
}

