package com.example.finalProject.services;

import com.example.finalProject.exceptions.DepartmentDoesNotExistException;
import com.example.finalProject.exceptions.EmployeeDoesNotExistException;
import com.example.finalProject.exceptions.ProjectDoesNotExistException;
import com.example.finalProject.exceptions.WrongArgumentsException;
import com.example.finalProject.model.Department;
import com.example.finalProject.model.Employee;
import com.example.finalProject.model.Project;
import com.example.finalProject.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DepartmentService departmentService;

    /**
     * Find all Projects
     * @return - List of found Projects
     */
    public Iterable<Project> findAll(){
        return projectRepository.findAll();
    }

    /**
     * Search for Project with this id. If not found returns an error
     * @param id - id of the Project
     * @return Found Project object
     */
    public Project findById(Long id){
        if(id <= 0L){
            throw new WrongArgumentsException();
        }
        Optional<Project> foundProject = projectRepository.findById(id);
        if(foundProject.isPresent()){
            return foundProject.get();
        }else {
            throw new ProjectDoesNotExistException();
        }
    }

    /**
     * Search for Project by name. If not found returns an error
     * @param name - firstname of the Project
     * @return Found Project object
     */
    public Iterable<Project> findByName(String name){
        if(name.trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        return projectRepository.findByName(name);
    }

    /**
     * Saves new Project in the DB. First checks if all properties are valid
     * @param project - new Project object to be saved
     * @return - new Project object saved in a DB with id from the DB
     */
    public Project create(Project project){
        if(project.getName() == null || project.getName().trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        //Department exist
        Department dep = departmentService.findById(project.getDepartmentid());
        return projectRepository.save(project);
    }

    /**
     * Updates already existing Project
     * @param project - new Project object
     * @param id - id of the Project to be updated
     * @return - updated Project object
     */
    public Project update(Project project, Long id){
        Project foundProject = findById(id);
        if(project.getName() == null || project.getName().trim().isEmpty()){
            throw new WrongArgumentsException();
        }
        Department dep = departmentService.findById(project.getDepartmentid());
        foundProject.setName(project.getName());
        foundProject.setDepartmentid(project.getDepartmentid());
        projectRepository.save(foundProject);
        return foundProject;
    }

    /**
     * Deletes Project object by id
     * @param id - id of the Project to be deleted
     */
    public void deleteById(Long id){
        if(id <= 0L){
            throw new WrongArgumentsException();
        }
        projectRepository.deleteById(id);
    }

}
