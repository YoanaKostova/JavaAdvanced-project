package com.example.finalProject.web;

import com.example.finalProject.model.Department;
import com.example.finalProject.model.Employee;
import com.example.finalProject.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public Iterable<Department> findAll(){
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable("id") Long id){
        return departmentService.findById(id);
    }

    //дали работи след добавяне на Exceptions
    @GetMapping("/allEmployees")
    public Iterable<Employee> allEmployeesInDepartment(@RequestParam Long departmentId){
        return departmentService.allEmployeesInDepartment(departmentId);
    }

    @GetMapping("/salary/{id}")
    public double salarySum(@PathVariable("id") Long id){
        return departmentService.salarySum(id);
    }

    @PostMapping("/")
    public Department create(@RequestBody Department department){
        return departmentService.save(department);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable("id") Long id, @RequestBody Department department){
        return departmentService.update(department,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        departmentService.deleteById(id);
    }

}
