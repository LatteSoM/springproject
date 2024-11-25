package ru.mpt.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.Employee;
import ru.mpt.springproject.entity.Project;
import ru.mpt.springproject.entity.Task;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.service.EmployeeService;
import ru.mpt.springproject.service.ProjectService;
import ru.mpt.springproject.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        List<Project> projects = projectService.getAllProjects();
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("tasks", tasks);
        model.addAttribute("projects", projects);
        model.addAttribute("employees", employees);
        model.addAttribute("task", new Task());
        return "Task";
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));
        List<Project> projects = projectService.getAllProjects();
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("task", task);
        model.addAttribute("projects", projects);
        model.addAttribute("employees", employees);
        return "Task";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task taskDetails) {
        taskService.updateTask(id, taskDetails);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}

