package ru.mpt.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.Department;
import ru.mpt.springproject.service.DepartmentService;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String getAllDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("department", new Department());
        return "Department";
    }

    @PostMapping
    public String createDepartment(@ModelAttribute Department department) {
        departmentService.createDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String editDepartment(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id).orElseThrow(() -> new RuntimeException( "Department not exist with id :" + id));
        model.addAttribute("department", department);
        return "Department";
    }

    @PostMapping("/edit/{id}")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute Department departmentDetails) throws Exception {
        departmentService.updateDepartment(id, departmentDetails);
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) throws Exception {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
