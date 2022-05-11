package ru.mirea.coursework.butchershop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.coursework.butchershop.entities.ProductEntity;
import ru.mirea.coursework.butchershop.repos.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<ProductEntity> getAllByIds(List<UUID> ids) {
        List<ProductEntity> result = new ArrayList<>();
        for(UUID id: ids) {
            ProductEntity product = productRepo.findById(id).orElseGet(null);
            if(product != null) {
                result.add(product);
            }
        }
        return result;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepo.findAll();
    }

    public ProductEntity createProduct(ProductEntity product) {
        productRepo.save(product);
        return product;
    }
}
