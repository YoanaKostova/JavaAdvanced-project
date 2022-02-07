package com.example.finalProject.web;

import com.example.finalProject.model.Project;
import com.example.finalProject.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public Iterable<Project> findAll(){
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable("id") Long id){
        return projectService.findById(id);
    }

    @PostMapping("/")
    public Project create(@RequestBody Project project){
        return projectService.create(project);
    }

    //???
    @PutMapping("/{id}")
    public Project update(@PathVariable("id") Long id, @RequestBody Project project){
        return projectService.update(project,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        projectService.deleteById(id);
    }
}
