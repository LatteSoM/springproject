package ru.mpt.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpt.springproject.entity.WorkTime;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.repository.WorkTimeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkTimeService {

    @Autowired
    private WorkTimeRepository workTimeRepository;

    public List<WorkTime> getAllWorkTimes() {
        return workTimeRepository.findAll();
    }

    public Optional<WorkTime> getWorkTimeById(Long id) {
        return workTimeRepository.findById(id);
    }

    public WorkTime createWorkTime(WorkTime workTime) {
        return workTimeRepository.save(workTime);
    }

    public WorkTime updateWorkTime(Long id, WorkTime workTimeDetails) {
        WorkTime workTime = workTimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("WorkTime not exist with id :" + id));
        workTime.setEmployee(workTimeDetails.getEmployee());
        workTime.setDate(workTimeDetails.getDate());
        workTime.setHoursWorked(workTimeDetails.getHoursWorked());
        workTime.setDescription(workTimeDetails.getDescription());
        return workTimeRepository.save(workTime);
    }

    public void deleteWorkTime(Long id) {
        WorkTime workTime = workTimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("WorkTime not exist with id :" + id));
        workTimeRepository.delete(workTime);
    }
}
