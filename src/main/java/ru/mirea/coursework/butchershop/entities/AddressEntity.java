package ru.mirea.coursework.butchershop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class AddressEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String city;
    private String street;
    private String buildingNumber;
    private String apartmentNumber;
    private String comment;
}
