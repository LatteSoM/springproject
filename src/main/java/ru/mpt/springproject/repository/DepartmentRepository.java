package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

