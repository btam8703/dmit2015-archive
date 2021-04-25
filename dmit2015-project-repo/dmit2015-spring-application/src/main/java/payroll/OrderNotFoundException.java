package payroll;

/**
 This class contains exception for a not found Order
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

public class OrderNotFoundException extends RuntimeException{

    OrderNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
