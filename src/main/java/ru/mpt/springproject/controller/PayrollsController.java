package ru.mpt.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.Employee;
import ru.mpt.springproject.entity.Payroll;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.service.EmployeeService;
import ru.mpt.springproject.service.PayrollService;

import java.util.List;

@Controller
@RequestMapping("/payrolls")
public class PayrollsController {

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getAllPayrolls(Model model) {
        List<Payroll> payrolls = payrollService.getAllPayrolls();
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("payrolls", payrolls);
        model.addAttribute("employees", employees);
        model.addAttribute("payroll", new Payroll());
        return "Payroll";
    }

    @PostMapping
    public String createPayroll(@ModelAttribute Payroll payroll) {
        payrollService.createPayroll(payroll);
        return "redirect:/payrolls";
    }

    @GetMapping("/edit/{id}")
    public String editPayroll(@PathVariable Long id, Model model) {
        Payroll payroll = payrollService.getPayrollById(id).orElseThrow(() -> new ResourceNotFoundException("Payroll not exist with id :" + id));
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("payroll", payroll);
        model.addAttribute("employees", employees);
        return "Payroll";
    }

    @PostMapping("/edit/{id}")
    public String updatePayroll(@PathVariable Long id, @ModelAttribute Payroll payrollDetails) {
        payrollService.updatePayroll(id, payrollDetails);
        return "redirect:/payrolls";
    }

    @GetMapping("/delete/{id}")
    public String deletePayroll(@PathVariable Long id) {
        payrollService.deletePayroll(id);
        return "redirect:/payrolls";
    }
}

