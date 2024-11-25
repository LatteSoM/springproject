package ru.mpt.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.Employee;
import ru.mpt.springproject.entity.User;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.service.EmployeeService;
import ru.mpt.springproject.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        List<User> users = userService.getAllUsers();
        model.addAttribute("employees", employees);
        model.addAttribute("users", users);
        model.addAttribute("employee", new Employee());
        return "Employee";
    }

    @PostMapping
    public String createEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable UUID id, Model model) {
        Employee employee = employeeService.getEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        List<User> users = userService.getAllUsers();
        model.addAttribute("employee", employee);
        model.addAttribute("users", users);
        return "Employee";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable UUID id, @ModelAttribute Employee employeeDetails) {
        employeeService.updateEmployee(id, employeeDetails);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}

