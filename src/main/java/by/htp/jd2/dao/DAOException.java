package by.htp.jd2.dao;

public class DAOException extends Exception {
    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(Exception e, String message) {
    }
}

