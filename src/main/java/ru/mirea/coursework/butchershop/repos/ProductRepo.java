package ru.mirea.coursework.butchershop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.coursework.butchershop.entities.ProductEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findAllByIdIn(List<UUID> ids);
}
