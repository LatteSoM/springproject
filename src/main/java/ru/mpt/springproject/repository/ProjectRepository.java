package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
