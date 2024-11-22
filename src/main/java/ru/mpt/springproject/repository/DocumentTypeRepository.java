package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.Department;
import ru.mpt.springproject.entity.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
}

