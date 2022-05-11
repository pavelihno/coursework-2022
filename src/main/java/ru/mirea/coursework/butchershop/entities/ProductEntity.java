package ru.mirea.coursework.butchershop.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
public class ProductEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private int price;
    private String name;
    private String description;
    private String image;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<OrderEntity> orders = new HashSet<>();
}
