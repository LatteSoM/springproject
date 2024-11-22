package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.Employee;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
