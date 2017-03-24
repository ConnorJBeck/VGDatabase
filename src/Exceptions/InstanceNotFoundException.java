package Exceptions;

import java.sql.SQLException;

public class InstanceNotFoundException extends SQLException {

    public InstanceNotFoundException() {
        super();
    }

    public InstanceNotFoundException(String msg) {
        super(msg);
    }
}
