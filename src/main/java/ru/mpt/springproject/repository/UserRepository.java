package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
