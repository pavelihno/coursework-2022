package ru.mirea.coursework.butchershop.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.coursework.butchershop.entities.AddressEntity;

import java.util.UUID;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity, UUID> {

}
