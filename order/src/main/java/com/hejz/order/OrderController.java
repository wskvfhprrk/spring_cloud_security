package com.hejz.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
@Slf4j
public class OrderController {

//    @Autowired
//    private RestTemplate restTemplate;
    @PostMapping
    public void creat(@RequestBody Order order, @AuthenticationPrincipal String username){
        log.info("username:{}",username);
//        Long price = restTemplate.getForObject("http://localhost:8090/prices/" + order.getId(), Long.class);
//        log.info("price:{}",price);
    }
    @GetMapping("{id}")
    public Long getById(@PathVariable Long id){
        return id;
    }
}
