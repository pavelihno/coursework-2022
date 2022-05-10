package ru.mirea.coursework.butchershop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.coursework.butchershop.entities.UserEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, UUID> {
    List<UserEntity> findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndUsernameContainingIgnoreCase(String firstName, String lastName, String username);
    UserEntity findByUsername(String username);
    default List<UserEntity> filterByFields(UserEntity user) {
        return findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndUsernameContainingIgnoreCase(user.getFirstName(), user.getLastName(), user.getUsername());
    }
}
