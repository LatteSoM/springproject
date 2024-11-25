package ru.mpt.springproject.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.EmployeePosition;
import ru.mpt.springproject.service.EmployeePositionService;

import java.util.List;

@RestController
@RequestMapping("/api/employee-positions")
public class EmployeePositionController {

    @Autowired
    private EmployeePositionService employeePositionService;

    @GetMapping
    public List<EmployeePosition> getAllEmployeePositions() {
        return employeePositionService.getAllEmployeePositions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeePosition> getEmployeePositionById(@PathVariable Long id) {
        return employeePositionService.getEmployeePositionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmployeePosition createEmployeePosition(@Valid @RequestBody EmployeePosition employeePosition) {
        return employeePositionService.createEmployeePosition(employeePosition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeePosition> updateEmployeePosition(@PathVariable Long id, @Valid @RequestBody EmployeePosition employeePositionDetails) {
        return ResponseEntity.ok(employeePositionService.updateEmployeePosition(id, employeePositionDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeePosition(@PathVariable Long id) {
        employeePositionService.deleteEmployeePosition(id);
        return ResponseEntity.noContent().build();
    }
}

