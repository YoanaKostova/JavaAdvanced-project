package com.example.finalProject.services;

import com.example.finalProject.exceptions.WrongArgumentsException;
import com.example.finalProject.model.Department;
import com.example.finalProject.model.Employee;
import com.example.finalProject.repositories.DepartmentRepository;
import com.example.finalProject.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired
//    private DepartmentService departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;

    public Iterable<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findById(Long id){
        return employeeRepository.findById(id).get();
    }

    public Employee create(Employee emp){
        //Department exist
        //Department department = departmentService.findById(emp.getDepartmentid());
        Department department = departmentRepository.findById(emp.getDepartmentid()).get();
        if(department == null){
            // да върна подходящ Exception
            throw new RuntimeException();
        }
        return employeeRepository.save(emp);
    }

    public Employee update(Employee emp, Long id){
        Employee foundEmployee = findById(id);

        //ако не открие такъв служител да върне подходящ Exception

        foundEmployee.setFirstname(emp.getFirstname());
        foundEmployee.setLastname(emp.getLastname());
        foundEmployee.setAddress(emp.getAddress());
        foundEmployee.setSalary(emp.getSalary());
        //дали да правя проверка дали Отдела съществува
        foundEmployee.setDepartmentid(emp.getDepartmentid());
        employeeRepository.save(foundEmployee);
        return foundEmployee;

        //по този начин на update-ване дали служителя се update-ва
        //или се създава нов ???
    }

    public void deleteById(Long id){
        employeeRepository.deleteById(id);
    }

    public Iterable<Employee> findByFirstname(String firstname){
        return employeeRepository.findByFirstname(firstname);
    }

    public Iterable<Employee> findByLastname(String lastname){
        return employeeRepository.findByLastname(lastname);
    }

    public Iterable<Employee> findByFirstnameAndLastname(String firstname,String lastname){
        return employeeRepository.findByFirstnameAndLastname(firstname, lastname);
    }

    public Iterable<Employee> allEmployeesInDepartment(Long depId){
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
