package ru.mpt.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.springproject.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
