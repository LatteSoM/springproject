package ru.mpt.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.Project;
import ru.mpt.springproject.entity.User;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.service.ProjectService;
import ru.mpt.springproject.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        List<User> users = userService.getAllUsers();
        model.addAttribute("projects", projects);
        model.addAttribute("users", users);
        model.addAttribute("project", new Project());
        return "Project";
    }

    @PostMapping
    public String createProject(@ModelAttribute Project project) {
        projectService.createProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/edit/{id}")
    public String editProject(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id).orElseThrow(() -> new ResourceNotFoundException("Project not exist with id :" + id));
        List<User> users = userService.getAllUsers();
        model.addAttribute("project", project);
        model.addAttribute("users", users);
        return "Project";
    }

    @PostMapping("/edit/{id}")
    public String updateProject(@PathVariable Long id, @ModelAttribute Project projectDetails) {
        projectService.updateProject(id, projectDetails);
        return "redirect:/projects";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }
}

