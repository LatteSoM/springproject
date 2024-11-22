package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}

