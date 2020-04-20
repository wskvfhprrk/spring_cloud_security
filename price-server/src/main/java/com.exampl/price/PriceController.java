package com.exampl.price;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author: hejz
 * @Description: 获取价格
 * @Date: 2020/4/20 10:22
 */
@RestController
@RequestMapping("prices")
@Slf4j
public class PriceController {

    @GetMapping("/{id}")
    public BigDecimal getPrice(@PathVariable Long id) {
        log.info("id:{}", id);
        return new BigDecimal(100);
    }

}
