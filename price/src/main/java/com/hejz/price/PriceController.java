package com.hejz.price;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prices")
@Slf4j
public class PriceController {
    @GetMapping("{id}")
    private Long getPriceByProdectId(@PathVariable Long id) {
        log.info("id:{}",id);
        return 100L;
    }
}
