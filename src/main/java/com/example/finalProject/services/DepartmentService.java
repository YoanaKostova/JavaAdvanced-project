package com.example.finalProject.services;

import com.example.finalProject.exceptions.WrongArgumentsException;
import com.example.finalProject.model.Department;
import com.example.finalProject.model.Employee;
import com.example.finalProject.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    //???
    @Autowired
    private EmployeeService employeeService;

    public Iterable<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Department findById(Long id){
        return departmentRepository.findById(id).get();
    }

    public Department save(Department department){
        return departmentRepository.save(department);
    }

    public Department update (Department department, Long id){
        Department foundDepartment = findById(id);
        foundDepartment.setName(department.getName());
        departmentRepository.save(foundDepartment);
        return foundDepartment;
    }

    public void deleteById(Long id){
        departmentRepository.deleteById(id);
    }

    public Iterable<Employee> allEmployeesInDepartment(Long depId){
        return employeeService.allEmployeesInDepartment(depId);
    }

    public double salarySum(Long id){
        Department department = departmentRepository.findById(id).get();
        if(department == null){
            throw new RuntimeException();
            //Exception за несъществуващ Отдел
        }
        double sum = 0;
        Iterable<Employee> allEmployees = employeeService.allEmployeesInDepartment(id);
        for (Employee emp : allEmployees) {
            sum += emp.getSalary();
        }
        return sum;
    }
}
