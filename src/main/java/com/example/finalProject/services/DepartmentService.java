package com.example.finalProject.services;

import com.example.finalProject.model.Department;
import com.example.finalProject.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    
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
}
