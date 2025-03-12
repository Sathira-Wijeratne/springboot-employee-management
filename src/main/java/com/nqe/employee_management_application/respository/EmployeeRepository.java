package com.nqe.employee_management_application.respository;

import com.nqe.employee_management_application.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// This interface will be used to access data from the MongoDB database
@Repository // marks this interface as a Spring data repository // indicate that the interface provides the mechanism for CRUD operations
public interface EmployeeRepository extends MongoRepository<Employee, Long> { // MongoRepository provides generic CRUD operation methods for the entity (Employee) class // <Employee, Long> are type parameters, Employee is the entity type, Long is the type of ID of the entity

}
