package com.example.finalProject.services;

import com.example.finalProject.model.Department;
import com.example.finalProject.model.Employee;
import com.example.finalProject.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    public Iterable<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findById(Long id){
        return employeeRepository.findById(id).get();
    }

    public Employee create(Employee emp){
        //Department exist
        Department department = departmentService.findById(emp.getDepartmentid());
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

}
