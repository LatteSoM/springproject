package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {
}
