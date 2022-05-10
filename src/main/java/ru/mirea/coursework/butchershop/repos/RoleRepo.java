package ru.mirea.coursework.butchershop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.coursework.butchershop.entities.RoleEntity;

import java.util.UUID;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, UUID> {
    RoleEntity findByName(String name);
}
