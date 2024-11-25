package ru.mpt.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.Employee;
import ru.mpt.springproject.entity.EmployeePosition;
import ru.mpt.springproject.entity.Position;
import ru.mpt.springproject.service.EmployeePositionService;
import ru.mpt.springproject.service.EmployeeService;
import ru.mpt.springproject.service.PositionService;

import java.util.List;

@Controller
@RequestMapping("/employee-positions")
public class EmployeePositionsController {

    @Autowired
    private EmployeePositionService employeePositionService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @GetMapping
    public String getAllEmployeePositions(Model model) {
        List<EmployeePosition> employeePositions = employeePositionService.getAllEmployeePositions();
        List<Employee> employees = employeeService.getAllEmployees();
        List<Position> positions = positionService.getAllPositions();
        model.addAttribute("employeePositions", employeePositions);
        model.addAttribute("employees", employees);
        model.addAttribute("positions", positions);
        model.addAttribute("employeePosition", new EmployeePosition());
        return "EmployeePosition";
    }

    @PostMapping
    public String createEmployeePosition(@ModelAttribute EmployeePosition employeePosition) {
        employeePositionService.createEmployeePosition(employeePosition);
        return "redirect:/employee-positions";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeePosition(@PathVariable Long id, Model model) {
        EmployeePosition employeePosition = employeePositionService.getEmployeePositionById(id).orElseThrow(() -> new RuntimeException("EmployeePosition not exist with id :" + id));
        List<Employee> employees = employeeService.getAllEmployees();
        List<Position> positions = positionService.getAllPositions();
        model.addAttribute("employeePosition", employeePosition);
        model.addAttribute("employees", employees);
        model.addAttribute("positions", positions);
        return "EmployeePosition";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployeePosition(@PathVariable Long id, @ModelAttribute EmployeePosition employeePositionDetails) {
        employeePositionService.updateEmployeePosition(id, employeePositionDetails);
        return "redirect:/employee-positions";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployeePosition(@PathVariable Long id) {
        employeePositionService.deleteEmployeePosition(id);
        return "redirect:/employee-positions";
    }
}

