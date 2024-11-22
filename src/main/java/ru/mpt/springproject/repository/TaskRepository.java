package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
