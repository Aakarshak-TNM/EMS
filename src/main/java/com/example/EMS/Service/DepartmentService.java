package com.example.EMS.Service;

import com.example.EMS.Entity.Department;
import com.example.EMS.Entity.Employee;
import com.example.EMS.Repository.DepartmentRepository;
import com.example.EMS.Repository.EmployeeRepository;
import com.example.EMS.Response.DepartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<?> get() {
        List<Department> departments = departmentRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(departments);
    }

    public ResponseEntity<?> add(Department department) {
        System.out.println(department);
        Department addedDepartment = departmentRepository.save(department);
        DepartmentResponse departmentResponse = new DepartmentResponse(addedDepartment, HttpStatus.OK.value(), "", "Department is Added");
        return ResponseEntity.status(HttpStatus.OK).body(departmentResponse);
    }

    public ResponseEntity<?> updateById(Long departmentId, Department departmentDetails) {
        Optional<Department> optionalDepartment = departmentRepository.findById((departmentId));
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            department.setDepartmentName(departmentDetails.getDepartmentName());

            Department updatedDepartment = departmentRepository.save(department);
            DepartmentResponse departmentResponse = new DepartmentResponse(updatedDepartment, HttpStatus.OK.value(), "", "Department with Id " + departmentId + " is updated");
            return ResponseEntity.status(HttpStatus.OK).body(departmentResponse);
        } else {
            DepartmentResponse departmentResponse = new DepartmentResponse(HttpStatus.NOT_FOUND.value(), "Not Found", "Department with Id " + departmentId + " is not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(departmentResponse);
        }
    }

    public ResponseEntity<?> getDepartmentWithEmployees(Long departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            List<Employee> employeeList = employeeRepository.findAll();
            List<String> employeeNamesInDepartment = employeeList.stream()
                    .filter(employee -> employee.getDepartment() != null && employee.getDepartment().getId() == departmentId)
                    .map(Employee::getName).toList();
            department.setEmployeeNames(employeeNamesInDepartment);
            DepartmentResponse departmentResponse = new DepartmentResponse(department, HttpStatus.OK.value(), null, "Department with Employees");
            return ResponseEntity.status(HttpStatus.OK).body(departmentResponse);
        } else {
            DepartmentResponse departmentResponse = new DepartmentResponse(HttpStatus.NOT_FOUND.value(), "Not Found", "Department with Id " + departmentId + " is not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(departmentResponse);
        }
    }

    public ResponseEntity<?> deleteDepartment(Long departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            List<Employee> employeeList = employeeRepository.findAll();
            List<String> employeeNamesInDepartment = employeeList.stream()
                    .filter(employee -> employee.getDepartment() != null && employee.getDepartment().getId() == departmentId)
                    .map(Employee::getName).toList();
            if (employeeNamesInDepartment.isEmpty()) {
                departmentRepository.delete(department);
                DepartmentResponse departmentResponse = new DepartmentResponse(null, HttpStatus.OK.value(), null, "Department " + department.getDepartmentName() + " with Id: " + department.getId() + " has been deleted");
                return ResponseEntity.status(HttpStatus.OK).body(departmentResponse);
            } else {
                DepartmentResponse departmentResponse = new DepartmentResponse(null, HttpStatus.OK.value(), null, "Department " + department.getDepartmentName() + " with Id: " + department.getId() + " cant be deleted as Employees are present");
                return ResponseEntity.status(HttpStatus.OK).body(departmentResponse);
            }
        } else {
            DepartmentResponse departmentResponse = new DepartmentResponse(HttpStatus.NOT_FOUND.value(), "Not Found", "Department with Id " + departmentId + " is not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(departmentResponse);
        }
    }
}
