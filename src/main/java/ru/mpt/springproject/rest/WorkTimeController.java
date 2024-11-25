package ru.mpt.springproject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.WorkTime;
import ru.mpt.springproject.service.WorkTimeService;

import java.util.List;

@RestController
@RequestMapping("/api/work-times")
public class WorkTimeController {

    @Autowired
    private WorkTimeService workTimeService;

    @GetMapping
    public List<WorkTime> getAllWorkTimes() {
        return workTimeService.getAllWorkTimes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkTime> getWorkTimeById(@PathVariable Long id) {
        return workTimeService.getWorkTimeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public WorkTime createWorkTime(@Valid @RequestBody WorkTime workTime) {
        return workTimeService.createWorkTime(workTime);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkTime> updateWorkTime(@PathVariable Long id, @Valid @RequestBody WorkTime workTimeDetails) {
        return ResponseEntity.ok(workTimeService.updateWorkTime(id, workTimeDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkTime(@PathVariable Long id) {
        workTimeService.deleteWorkTime(id);
        return ResponseEntity.noContent().build();
    }
}

