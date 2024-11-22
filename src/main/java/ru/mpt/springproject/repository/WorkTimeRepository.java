package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.WorkTime;

public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {
}
