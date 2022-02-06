package com.example.finalProject.web;

import com.example.finalProject.model.Employee;
import com.example.finalProject.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// да проверя дали всичко работи
@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public Iterable<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id){
        return employeeService.findById(id);
    }

//    @PostMapping("/")
//    public Employee create(@RequestBody Employee emp){
//        return employeeService.create(emp);
//    }

    @PostMapping("/")
    public Employee create(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String address,
                           @RequestParam double salary,
                           @RequestParam Long departmentId){
        Employee emp = new Employee(firstName, lastName,address,salary,departmentId);
        return employeeService.create(emp);
    }

    //???
    @PutMapping("/{id}")
    public Employee update(@PathVariable("id") Long id, @RequestBody Employee emp){
        return employeeService.update(emp,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        employeeService.deleteById(id);
    }
}
