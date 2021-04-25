package payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 This class contains the PayrollApplication main class
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

@SpringBootApplication
public class PayrollApplication {

	public static void main(String... args) {
		SpringApplication.run(PayrollApplication.class, args);
	}

	//curl -v localhost:8080/employees
	//
	//curl -v localhost:8080/employees/1
	//
	//curl -v -X POST localhost:8080/employees -H 'Content-Type:application/json' -d '{"name": "Samwise Gamgee", "role": "gardener"}'
	//
	//curl -v -X PUT localhost:8080/employees/3 -H 'Content-Type:application/json' -d '{"name": "Samwise Gamgee", "role": "ring bearer"}'
	//
	//curl -v -X DELETE localhost:8080/employees/1
}