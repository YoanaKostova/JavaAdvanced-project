package com.example.finalProject.repositories;

import com.example.finalProject.model.Department;
import com.example.finalProject.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department,Long> {
}
