package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 This class is a repository for an Employee
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

interface EmployeeRepository extends JpaRepository<Employee, Long> {

}