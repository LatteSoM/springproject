package ru.mpt.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpt.springproject.entity.Task;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setProject(taskDetails.getProject());
        task.setAssignedTo(taskDetails.getAssignedTo());
        task.setStatus(taskDetails.getStatus());
        task.setCreatedAt(taskDetails.getCreatedAt());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));
        taskRepository.delete(task);
    }
}

