package ru.mirea.coursework.butchershop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mirea.coursework.butchershop.DTO.OrderDTO;
import ru.mirea.coursework.butchershop.entities.OrderEntity;
import ru.mirea.coursework.butchershop.entities.ProductEntity;
import ru.mirea.coursework.butchershop.entities.UserEntity;
import ru.mirea.coursework.butchershop.repos.OrderRepo;
import ru.mirea.coursework.butchershop.repos.ProductRepo;
import ru.mirea.coursework.butchershop.repos.UserRepo;
import ru.mirea.coursework.butchershop.utilities.DateTimeUtility;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderRepo orderRepo;

    @Value("${delivery.price}")
    private int deliveryPrice;

    public List<OrderEntity> getOrders(UserEntity user) {
        return orderRepo.getAllOrders(user.getId());
    }

    public OrderEntity createOrder(OrderDTO orderDTO, UserEntity user) throws Exception {
        if (orderDTO.getProductsIds().size() == 0)
            throw new Exception("Выберите товар");

        OrderEntity order = new OrderEntity();
        String dateCreation = DateTimeUtility.getFormattedDate(new Date());
        Long timestamp = (new Timestamp(System.currentTimeMillis())).getTime();

        int price = 0;
        for (UUID productId: orderDTO.getProductsIds()) {
            ProductEntity product = productRepo.findById(productId).orElseThrow(() -> new Exception("Товар не найден"));
            order.addProducts(product);
            price += product.getPrice();
        }
        boolean withDelivery = orderDTO.getWithDelivery() != null;
        if (withDelivery)
            price += deliveryPrice;

        if (price > user.getBalance())
            throw new Exception("Недостаточно средств");

        user.setBalance(user.getBalance() - price);

        order.setUser(user);
        order.setDateCreation(dateCreation);
        order.setTimestamp(timestamp);
        order.setPrice(price);
        order.setWithDelivery(withDelivery);

        orderRepo.save(order);
        userRepo.save(user);

        return order;
    }

    public Long getTotalSum(UserEntity user) {
        Long result = orderRepo.selectTotalCost(user.getId());
        return result == null ? 0L : result;
    }
}
