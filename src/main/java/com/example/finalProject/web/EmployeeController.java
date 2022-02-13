package com.example.finalProject.web;

import com.example.finalProject.model.Employee;
import com.example.finalProject.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public Iterable<Employee> findAll(@RequestParam(required = false) String firstname,
                                      @RequestParam(required = false) String lastname,
                                      @RequestParam(required = false) Long departmentId) {
        if (firstname != null && lastname != null) {
            return employeeService.findByFirstnameAndLastname(firstname, lastname);
        }
        if (firstname != null) {
            return employeeService.findByFirstname(firstname);
        }
        if (lastname != null) {
            return employeeService.findByLastname(lastname);
        }
        if(departmentId != null){
            return employeeService.allEmployeesInDepartment(departmentId);
        }

        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        return employeeService.findById(id);
    }

    @PostMapping("/")
    public Employee create(@RequestBody Employee emp) {
        return employeeService.create(emp);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable("id") Long id, @RequestBody Employee emp) {
        return employeeService.update(emp, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
    }
}