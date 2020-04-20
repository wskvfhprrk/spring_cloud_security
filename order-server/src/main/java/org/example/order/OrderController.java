package org.example.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * @author: hejz
 * @Description:
 * @Date: 2020/4/20 10:45
 */
@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        BigDecimal price = restTemplate.getForObject("http://localhost:9080/prices/" + id, BigDecimal.class);
        Order order=new Order();
        order.setId(id);
        order.setName("name");
        order.setPrice(price);
        return order;
    }
}
