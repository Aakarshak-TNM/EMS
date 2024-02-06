package com.example.EMS.Service;

import com.example.EMS.Entity.Department;
import com.example.EMS.Entity.Department;
import com.example.EMS.Repository.DepartmentRepository;
import com.example.EMS.Response.DepartmentResponse;
import com.example.EMS.Response.DepartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

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

}
