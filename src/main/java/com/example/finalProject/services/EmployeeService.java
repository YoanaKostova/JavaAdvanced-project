package com.example.finalProject.services;

import com.example.finalProject.exceptions.DepartmentDoesNotExistException;
import com.example.finalProject.exceptions.EmployeeDoesNotExistException;
import com.example.finalProject.exceptions.WrongArgumentsException;
import com.example.finalProject.model.Department;
import com.example.finalProject.model.Employee;
import com.example.finalProject.repositories.DepartmentRepository;
import com.example.finalProject.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Find all Employees
     * @return - List of found Employees
     */
    public Iterable<Employee> findAll(){
        return employeeRepository.findAll();
    }

    /**
     * Search for Employee with this id. If not found returns an error
     * @param id - id of the Employee
     * @return Found Employee object
     */
    public Employee findById(Long id){
        if(id.equals(null) || id <= 0L){
            throw new WrongArgumentsException();
        }
        Optional<Employee> foundEmp = employeeRepository.findById(id);
        if(foundEmp.isPresent()){
            return foundEmp.get();
        }else {
            throw new EmployeeDoesNotExistException();
        }
    }

    /**
     * Saves new Employee in the DB. First checks if all properties are valid
     * @param emp - new Employee object to be saved
     * @return - new Employee object saved in a DB with id from the DB
     */
    public Employee create(Employee emp){
        if(emp.getFirstname() == null || emp.getFirstname().trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        if(emp.getLastname() == null || emp.getLastname().trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        if(emp.getAddress() == null || emp.getAddress().trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        if(emp.getSalary() <= 0){
            throw new WrongArgumentsException();
        }

        //Department exist
        if(emp.getDepartmentid() <= 0L ){
            throw new WrongArgumentsException();
        }
        Optional<Department> foundDepartment = departmentRepository.findById(emp.getDepartmentid());
        if(!foundDepartment.isPresent()){
            throw new DepartmentDoesNotExistException();
        }
        return employeeRepository.save(emp);
    }

    /**
     * Updates already existing Employee
     * @param emp - new Employee object
     * @param id - id of the Employee to be updated
     * @return - updated Employee object
     */
    public Employee update(Employee emp, Long id){
//        Employee foundEmployee = findById(id);
//        if(emp.getFirstname() == null || emp.getFirstname().trim().isEmpty()){
//            throw new WrongArgumentsException();
//        }
//        if(emp.getLastname() == null || emp.getLastname().trim().isEmpty()){
//            throw new WrongArgumentsException();
//        }
//        if(emp.getAddress() == null || emp.getAddress().trim().isEmpty()){
//            throw new WrongArgumentsException();
//        }
//        if(emp.getSalary() <= 0){
//            throw new WrongArgumentsException();
//        }
//        //Department exist
//        if(emp.getDepartmentid() <= 0L ){
//            throw new WrongArgumentsException();
//        }
//        Optional<Department> foundDepartment = departmentRepository.findById(emp.getDepartmentid());
//        if(!foundDepartment.isPresent()){
//            throw new DepartmentDoesNotExistException();
//        }
//        foundEmployee.setFirstname(emp.getFirstname());
//        foundEmployee.setLastname(emp.getLastname());
//        foundEmployee.setAddress(emp.getAddress());
//        foundEmployee.setSalary(emp.getSalary());
//        foundEmployee.setDepartmentid(emp.getDepartmentid());
//        employeeRepository.save(foundEmployee);
//        return foundEmployee;

        emp.setId(id);
        return create(emp);
    }

    /**
     * Deletes Employee object by id
     * @param id - id of the Employee to be deleted
     */
    public void deleteById(Long id){
        if(id <= 0L){
            throw new WrongArgumentsException();
        }
        employeeRepository.deleteById(id);
    }

    /**
     * Search for Employee by firstname. If not found returns an error
     * @param firstname - firstname of the Employee
     * @return Found Employee object
     */
    public Iterable<Employee> findByFirstname(String firstname){
        if(firstname.trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        return employeeRepository.findByFirstname(firstname);
    }

    /**
     * Search for Employee by lastname. If not found returns an error
     * @param lastname - lastname of the Employee
     * @return Found Employee object
     */
    public Iterable<Employee> findByLastname(String lastname){
        if(lastname.trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        return employeeRepository.findByLastname(lastname);
    }

    /**
     * Search for Employee by firstname and lastname. If not found returns an error
     * @param firstname - firstname of the Employee
     * @param lastname - lastname of the Employee
     * @return Found Employee object
     */
    public Iterable<Employee> findByFirstnameAndLastname(String firstname,String lastname){
        if(firstname.trim().isEmpty() || lastname.trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        return employeeRepository.findByFirstnameAndLastname(firstname, lastname);
    }

    /**
     * Finds all Employees in a Department
     * @param depId - id of the Department
     * @return - Employee object with all Employees in this Department
     */
    public Iterable<Employee> allEmployeesInDepartment(Long depId){
        if(depId <= 0L){
            throw new WrongArgumentsException();
        }
        Iterable<Employee> allEmployees = employeeRepository.findAll();
        List<Employee> res = new ArrayList<>();
        for(Employee emp : allEmployees){
            if(emp.getDepartmentid() == depId){
                res.add(emp);
            }
        }
        return res;
    }


}
