package ru.mirea.coursework.butchershop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mirea.coursework.butchershop.entities.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Product {
    private UUID id;
    private int price;
    private String name;
    private String description;
    private String image;

    public static Product toModel(ProductEntity productEntity) {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setPrice(productEntity.getPrice());
        product.setDescription(productEntity.getDescription());
        product.setName(productEntity.getName());
        product.setImage(productEntity.getImage());
        return product;
    }

    public static List<Product> toModel(List<ProductEntity> productsEntities) {
        List<Product> products = new ArrayList<>();

        for (ProductEntity productEntity: productsEntities)
            products.add(toModel(productEntity));

        return products;
    }

}
