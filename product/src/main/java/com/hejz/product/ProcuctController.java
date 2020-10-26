package com.hejz.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("products")
@Slf4j
public class ProcuctController {

    @Autowired
    private RestTemplate restTemplate;
    @PostMapping
    private void creat(@RequestBody Product product){
        Long price = restTemplate.getForObject("http://localhost:8090/prices/" + product.getId(), Long.class);
        log.info("price:{}",price);
    }
}
