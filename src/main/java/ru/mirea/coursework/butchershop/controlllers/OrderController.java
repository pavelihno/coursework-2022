package ru.mirea.coursework.butchershop.controlllers;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mirea.coursework.butchershop.DTO.OrderDTO;
import ru.mirea.coursework.butchershop.entities.OrderEntity;
import ru.mirea.coursework.butchershop.entities.ProductEntity;
import ru.mirea.coursework.butchershop.entities.UserEntity;
import ru.mirea.coursework.butchershop.models.Product;
import ru.mirea.coursework.butchershop.services.OrderService;
import ru.mirea.coursework.butchershop.services.ProductService;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/orders")
    public String getOrderPage(Model model, @AuthenticationPrincipal UserEntity user) {
        List<OrderEntity> orders = orderService.getOrders(user);

        Long totalSum = orderService.getTotalSum(user);

        model.addAttribute("orders", orders);
        model.addAttribute("sum", totalSum);

        return "orders";
    }

    @PostMapping("/orders")
    public String createOrder(OrderDTO orderDTO, @AuthenticationPrincipal UserEntity user, RedirectAttributes attrs) {
        try {
            orderService.createOrder(orderDTO, user);
            return "redirect:/orders";

        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping("/create_order")
    public String getCreationOrderPage(Model model) {
        OrderDTO orderDTO = new OrderDTO();
        List<ProductEntity> products =productService.getAllProducts();
        val orderDtoFromAttr = model.getAttribute("orderDto");
        val productsFromAttr = model.getAttribute("products");
        OrderDTO orderDtoCasted = orderDtoFromAttr == null ? orderDTO : (OrderDTO) orderDtoFromAttr;
        val checkedProducts = productService.getAllByIds(orderDtoCasted.getProductsIds());

        model.addAttribute("products", productsFromAttr == null ? Product.toModel(products) : productsFromAttr);
        model.addAttribute("checkedProducts", checkedProducts);
        model.addAttribute("orderDTO", orderDtoFromAttr == null ? orderDTO : orderDtoFromAttr);

        return "create_order";
    }

}
