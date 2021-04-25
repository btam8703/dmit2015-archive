package payroll;

/**
 This class contains the repository for the Order entity
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<Order, Long> {

}
