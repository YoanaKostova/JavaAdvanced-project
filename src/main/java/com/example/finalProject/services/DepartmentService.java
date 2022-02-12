package com.example.finalProject.services;

import com.example.finalProject.exceptions.DepartmentDoesNotExistException;
import com.example.finalProject.exceptions.WrongArgumentsException;
import com.example.finalProject.model.Department;
import com.example.finalProject.model.Employee;
import com.example.finalProject.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        if(id.equals(null) || id <= 0L){
            throw new WrongArgumentsException();
        }

        try{
            return departmentRepository.findById(id).get();
        } catch (NoSuchElementException e){
            throw new DepartmentDoesNotExistException();
        }
    }

    public Department save(Department department){
        if(department.getName() == null || department.getName().trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        return departmentRepository.save(department);
    }

    public Department update (Department department, Long id){
        Department foundDepartment = findById(id);
        if(department.getName() == null || department.getName().trim().isEmpty()){
            throw new WrongArgumentsException();
        }
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
        Department department = findById(id);

        double sum = 0;
        Iterable<Employee> allEmployees = employeeService.allEmployeesInDepartment(id);
        for (Employee emp : allEmployees) {
            sum += emp.getSalary();
        }
        return sum;
    }
}
