package ru.mpt.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.Employee;
import ru.mpt.springproject.entity.WorkTime;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.service.EmployeeService;
import ru.mpt.springproject.service.WorkTimeService;

import java.util.List;

@Controller
@RequestMapping("/work-times")
public class WorkTimesController {

    @Autowired
    private WorkTimeService workTimeService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getAllWorkTimes(Model model) {
        List<WorkTime> workTimes = workTimeService.getAllWorkTimes();
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("workTimes", workTimes);
        model.addAttribute("employees", employees);
        model.addAttribute("workTime", new WorkTime());
        return "WorkTime";
    }

    @PostMapping
    public String createWorkTime(@ModelAttribute WorkTime workTime) {
        workTimeService.createWorkTime(workTime);
        return "redirect:/work-times";
    }

    @GetMapping("/edit/{id}")
    public String editWorkTime(@PathVariable Long id, Model model) {
        WorkTime workTime = workTimeService.getWorkTimeById(id).orElseThrow(() -> new ResourceNotFoundException("WorkTime not exist with id :" + id));
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("workTime", workTime);
        model.addAttribute("employees", employees);
        return "WorkTime";
    }

    @PostMapping("/edit/{id}")
    public String updateWorkTime(@PathVariable Long id, @ModelAttribute WorkTime workTimeDetails) {
        workTimeService.updateWorkTime(id, workTimeDetails);
        return "redirect:/work-times";
    }

    @GetMapping("/delete/{id}")
    public String deleteWorkTime(@PathVariable Long id) {
        workTimeService.deleteWorkTime(id);
        return "redirect:/work-times";
    }
}

