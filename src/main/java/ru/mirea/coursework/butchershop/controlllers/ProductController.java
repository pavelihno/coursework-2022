package ru.mirea.coursework.butchershop.controlllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mirea.coursework.butchershop.entities.ProductEntity;
import ru.mirea.coursework.butchershop.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public String getProductsPage(Model model, RedirectAttributes attrs) {
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @SneakyThrows
    @GetMapping("/initially_create")
    public ResponseEntity<String> createInitially() {

        String[] descriptions = {
                "Сочная котлета из 100% мраморной говядины с сыром чеддер и овощами",
                "Две сочные котлеты из 100% мраморной говядины, сыр чеддер, бекон",
                "Сочная котлета из 100% мраморной говядины, маринованные огурчики,хашбрауни, сыр чеддер и яичница с беконом"
        };
        String[] names = {
                "Классический чизбургер",
                "Двойной чизбургер с беконом",
                "Похмельный"
        };
        int[] prices = {
                420,
                550,
                500
        };

        String[] images = {
                "static/images/1.jpg",
                "static/images/2.jpg",
                "static/images/3.jpg"
        };

        for (int i = 0; i < descriptions.length; i++) {
            ProductEntity product = new ProductEntity();
            product.setDescription(descriptions[i]);
            product.setName(names[i]);
            product.setPrice(prices[i]);
            product.setImage(images[i]);
            productService.createProduct(product);
        }
        System.out.println("УСПЕШНО СОЗДАНЫ ОБЪЕКТЫ");

        return ResponseEntity.ok("New objects created");
    }
}
