package org.example.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@Slf4j
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id, @AuthenticationPrincipal String username) {
        log.info("username:{}", username);
//        BigDecimal price = restTemplate.getForObject("http://localhost:9080/prices/" + id, BigDecimal.class);
        Order order=new Order();
        order.setId(id);
        order.setName("name");
        order.setPrice(new BigDecimal(100));
        return order;
    }
}
