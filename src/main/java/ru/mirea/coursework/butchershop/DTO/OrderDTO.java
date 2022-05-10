package ru.mirea.coursework.butchershop.DTO;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDTO {
    private List<UUID> productsIds = new ArrayList<>();

    private String withDelivery = null;
}
