package ru.mpt.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpt.springproject.entity.EmployeePosition;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.repository.EmployeePositionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeePositionService {

    @Autowired
    private EmployeePositionRepository employeePositionRepository;

    public List<EmployeePosition> getAllEmployeePositions() {
        return employeePositionRepository.findAll();
    }

    public Optional<EmployeePosition> getEmployeePositionById(Long id) {
        return employeePositionRepository.findById(id);
    }

    public EmployeePosition createEmployeePosition(EmployeePosition employeePosition) {
        return employeePositionRepository.save(employeePosition);
    }

    public EmployeePosition updateEmployeePosition(Long id, EmployeePosition employeePositionDetails) {
        EmployeePosition employeePosition = employeePositionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EmployeePosition not exist with id :" + id));
        employeePosition.setEmployee(employeePositionDetails.getEmployee());
        employeePosition.setPosition(employeePositionDetails.getPosition());
        employeePosition.setStartDate(employeePositionDetails.getStartDate());
        employeePosition.setEndDate(employeePositionDetails.getEndDate());
        return employeePositionRepository.save(employeePosition);
    }

    public void deleteEmployeePosition(Long id) {
        EmployeePosition employeePosition = employeePositionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EmployeePosition not exist with id :" + id));
        employeePositionRepository.delete(employeePosition);
    }
}

