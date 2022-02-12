package com.example.finalProject.repositories;

import com.example.finalProject.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Iterable<Employee> findByFirstname(String firstname);
    Iterable<Employee> findByLastname(String lastname);
    Iterable<Employee> findByFirstnameAndLastname(String firstname, String lastname);
}
