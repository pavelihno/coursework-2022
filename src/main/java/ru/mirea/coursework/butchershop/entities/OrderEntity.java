package ru.mirea.coursework.butchershop.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class OrderEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private Boolean withDelivery;
    private int price;
    private String dateCreation;
    private Long timestamp;

    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    @ManyToMany(fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductEntity> products = new ArrayList<>();

    public void addProducts(ProductEntity product) {
        this.products.add(product);
    }
}
