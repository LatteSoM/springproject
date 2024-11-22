package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.EmployeePosition;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {
}
