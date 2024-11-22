package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
