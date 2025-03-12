package com.nqe.employee_management_application.controller;

// this class will define the APIs which will be exposed to the clients. Annotations will be used to map URIs to controller methods

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nqe.employee_management_application.exception.ResourceNotFoundException;
import com.nqe.employee_management_application.model.Employee;
import com.nqe.employee_management_application.respository.EmployeeRepository;
import com.nqe.employee_management_application.service.SequenceGeneratorService;


@RestController // what is this needed for? - Marks this class as a RESTful web service controller which return json response
@RequestMapping("/api/v1") // api/v1 is the base URI for the APIs
public class EmployeeController {
    @Autowired // the IDE says field injection is not recommended for this variable - field injection is not recommended because constructor injection is preferred for better testability
    // also it showed an error until RestController annotation was added to the class, why is that? - //  due to class not being recognized as a Spring component until @RestController was added
    private EmployeeRepository employeeRepository;

//    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired //how do we know when to use Autowired? - Use @Autowired for dependency injection to automatically wire the required beans.
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //get employee by id
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException{ //why returning ResponseEntity? what is that? - ResponseEntity represents the entire HTTP response it allows control over the status code and response body
        // does @PathVariable get the id from the URL and map it to the method parameter or something? - yes
        // why exception is thrown? - in case employee is not found
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        return ResponseEntity.ok().body(employee); //why .ok()? - ok() creates a response with HTTP status 200
    }

    //add employee
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee){ //what do the 2 annotations mean? - @Valid validates the request body. @RequestBody maps the JSON body to the Employee object.
        employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));

        return employeeRepository.save(employee); // simultaneously saving and returning saved employee? - yes
    }

    //update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException{ // how to know which exception to throw? - from practise, there are some common ones
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId)); // why is there 2 :'s? - used in lambda expressions for concise syntax but doesn't do anything in a print statement

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        final Employee updatedEmployee = employeeRepository.save(employee);

        return ResponseEntity.ok(updatedEmployee);
    }

    //delete employee
    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException{
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    @GetMapping("/test")
    public String testEndPoint(){
        return "Test endpoint working!";
    }

}
