package ru.mpt.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpt.springproject.entity.Payroll;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.repository.PayrollRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    public Optional<Payroll> getPayrollById(Long id) {
        return payrollRepository.findById(id);
    }

    public Payroll createPayroll(Payroll payroll) {
        return payrollRepository.save(payroll);
    }

    public Payroll updatePayroll(Long id, Payroll payrollDetails) {
        Payroll payroll = payrollRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payroll not exist with id :" + id));
        payroll.setEmployee(payrollDetails.getEmployee());
        payroll.setYear(payrollDetails.getYear());
        payroll.setMonth(payrollDetails.getMonth());
        payroll.setSalary(payrollDetails.getSalary());
        payroll.setBonuses(payrollDetails.getBonuses());
        payroll.setDeductions(payrollDetails.getDeductions());
        payroll.setNetSalary(payrollDetails.getNetSalary());
        return payrollRepository.save(payroll);
    }

    public void deletePayroll(Long id) {
        Payroll payroll = payrollRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payroll not exist with id :" + id));
        payrollRepository.delete(payroll);
    }
}

