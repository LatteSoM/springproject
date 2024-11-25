package ru.mpt.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.Department;
import ru.mpt.springproject.entity.Position;
import ru.mpt.springproject.service.DepartmentService;
import ru.mpt.springproject.service.PositionService;

import java.util.List;

@Controller
@RequestMapping("/positions")
public class PositionsController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String getAllPositions(Model model) {
        List<Position> positions = positionService.getAllPositions();
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("positions", positions);
        model.addAttribute("departments", departments);
        model.addAttribute("position", new Position());
        return "Position";
    }

    @PostMapping
    public String createPosition(@ModelAttribute Position position) {
        positionService.createPosition(position);
        return "redirect:/positions";
    }

    @GetMapping("/edit/{id}")
    public String editPosition(@PathVariable Long id, Model model) {
        Position position = positionService.getPositionById(id).orElseThrow(() -> new RuntimeException("Position not exist with id :" + id));
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("position", position);
        model.addAttribute("departments", departments);
        return "Position";
    }

    @PostMapping("/edit/{id}")
    public String updatePosition(@PathVariable Long id, @ModelAttribute Position positionDetails) throws Exception {
        positionService.updatePosition(id, positionDetails);
        return "redirect:/positions";
    }

    @GetMapping("/delete/{id}")
    public String deletePosition(@PathVariable Long id) throws Exception {
        positionService.deletePosition(id);
        return "redirect:/positions";
    }
}

