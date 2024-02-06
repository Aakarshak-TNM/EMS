package com.example.EMS.Service;

import com.example.EMS.Entity.Employee;
import com.example.EMS.Repository.EmployeeRepository;
import com.example.EMS.Response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<?> get() {
        List<Employee> employees = employeeRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    public ResponseEntity<?> add(Employee employee) {

        Employee addedEmployee = employeeRepository.save(employee);
        EmployeeResponse employeeResponse = new EmployeeResponse(addedEmployee, HttpStatus.OK.value(), "", "Employee is Added");
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

    public ResponseEntity<?> getById(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            EmployeeResponse employeeResponse = new EmployeeResponse(employee, HttpStatus.OK.value(), "", "Employee with Id " + employeeId + " is Found");
            return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
        } else {
            EmployeeResponse employeeResponse = new EmployeeResponse(HttpStatus.NOT_FOUND.value(), "Not Found", "Employee with Id " + employeeId + " is not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employeeResponse);
        }
    }

    public ResponseEntity<?> updateById(Long employeeId, Employee employeeDetails) {
        Optional<Employee> optionalEmployee = employeeRepository.findById((employeeId));
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(employeeDetails.getName());
            employee.setSalary(employeeDetails.getSalary());

            Employee updatedEmployee = employeeRepository.save(employee);
            EmployeeResponse employeeResponse = new EmployeeResponse(updatedEmployee, HttpStatus.OK.value(), "", "Employee with Id " + employeeId + " is updated");
            return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
        } else {
            EmployeeResponse employeeResponse = new EmployeeResponse(HttpStatus.NOT_FOUND.value(), "Not Found", "Employee with Id " + employeeId + " is not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employeeResponse);
        }
    }


    public ResponseEntity<?> deleteById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            try {
                employeeRepository.deleteById(id);
                return ResponseEntity.ok().body("Employee with ID " + id + " has been deleted successfully.");
            } catch (EmptyResultDataAccessException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the employee.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + id + " not found.");
        }
    }

}
