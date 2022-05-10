package ru.mirea.coursework.butchershop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.coursework.butchershop.entities.OrderEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, UUID> {

    List<OrderEntity> findAllByUser_IdOrderByTimestampDesc(UUID userId);
    default List<OrderEntity> getAllOrders(UUID userId) {
        return findAllByUser_IdOrderByTimestampDesc(userId);
    }

    @Query("SELECT SUM(o.price) FROM OrderEntity o where o.user.id = ?1")
    Long selectTotalCost(UUID userId);
}
