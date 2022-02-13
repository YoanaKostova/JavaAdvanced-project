package com.example.finalProject.services;

import com.example.finalProject.exceptions.DepartmentDoesNotExistException;
import com.example.finalProject.exceptions.WrongArgumentsException;
import com.example.finalProject.model.Department;
import com.example.finalProject.model.Employee;
import com.example.finalProject.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Finds all Departments
     * @return List of found Departments
     */
    public Iterable<Department> findAll(){
        return departmentRepository.findAll();
    }

    /**
     * Search for Department with this id. If not found returns an error
     * @param id - id of the Department
     * @return Found Department object
     */
    public Department findById(Long id){
        if(id == null || id <= 0L){
            throw new WrongArgumentsException();
        }

        try{
            return departmentRepository.findById(id).get();
        } catch (NoSuchElementException e){
            throw new DepartmentDoesNotExistException();
        }
    }

    /**
     * Saves new Department in the DB. First checks if all properties are valid
     * @param department - new Department object to saved
     * @return - new Department object saved in a DB with id from the DB
     */
    public Department save(Department department){
        if(department.getName() == null || department.getName().trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        return departmentRepository.save(department);
    }

    /**
     * Updates already existing Department
     * @param department - new Department object
     * @param id - id of the Department that to be updated
     * @return - updated Department object
     */
    public Department update (Department department, Long id){
        Department foundDepartment = findById(id);
        if(department.getName() == null || department.getName().trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        foundDepartment.setName(department.getName());
        departmentRepository.save(foundDepartment);
        return foundDepartment;
    }

    /**
     * Deletes Department object by id
     * @param id - id of the Department to be deleted
     */
    public void deleteById(Long id){
        if(id <= 0L){
            throw new WrongArgumentsException();
        }
        departmentRepository.deleteById(id);
    }

    /**
     * Finds all Employees in a Department
     * @param depId - id of the Department
     * @return - Employee object with all Employees in this Department
     */
    public Iterable<Employee> allEmployeesInDepartment(Long depId){
        Department dep = findById(depId);
        return employeeService.allEmployeesInDepartment(depId);
    }

    /**
     * Sum of salaries in a given department
     * @param id - id of the Department
     * @return - Sum of the salaries. If there are no Employees return 0.0
     */
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
