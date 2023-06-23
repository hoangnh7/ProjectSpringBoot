package com.example.demo.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductInfo {
    private String id;

    private String name;

    private String slug;

    private long price;

    private int totalSold;

    private String image;

    private long promotionPrice;
}
