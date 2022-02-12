package com.example.finalProject.services;

import com.example.finalProject.model.Department;
import com.example.finalProject.model.Employee;
import com.example.finalProject.model.Project;
import com.example.finalProject.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DepartmentService departmentService;

    public Iterable<Project> findAll(){
        return projectRepository.findAll();
    }

    public Project findById(Long id){
        return projectRepository.findById(id).get();
    }

    public Iterable<Project> findByName(String name){
        return projectRepository.findByName(name);
    }

    public Project create(Project project){
        //Department exist
        Department department = departmentService.findById(project.getDepartmentid());
        if(department == null){
            // да върна подходящ Exception
            throw new RuntimeException();
        }
        return projectRepository.save(project);
    }

    public Project update(Project project, Long id){
        Project foundProject = findById(id);

        //ако не открие такъв проект да върне подходящ Exception

        foundProject.setName(project.getName());
        foundProject.setDepartmentid(project.getDepartmentid());

        projectRepository.save(foundProject);
        return foundProject;
    }

    public void deleteById(Long id){
        projectRepository.deleteById(id);
    }

}
