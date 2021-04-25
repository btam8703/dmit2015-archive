package payroll;

/**
 This class is an exception for a not found Employee
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}