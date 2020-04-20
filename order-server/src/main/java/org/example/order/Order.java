package org.example.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: hejz
 * @Description:
 * @Date: 2020/4/20 10:46
 */
@Data
public class Order {
    private Long id;
    private BigDecimal price;
    private String name;
}
