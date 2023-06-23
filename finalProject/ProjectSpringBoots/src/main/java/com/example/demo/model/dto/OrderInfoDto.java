package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDto {
        private long id;

        private long totalPrice;

        private int sizeVn;


        private String productName;

        private String productImg;


    }

