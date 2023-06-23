package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "promotion")
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "coupon_code",unique = true ,length = 255)
    private String couponCode;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "discount_type")
    private int discountType;
    @Column(name = "discount_value")
    private long discountValue;
    @Column(name = "expired_at")
    private Timestamp expiredAt;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "is_public")
    private Boolean isPublic;
    @Column(name = "maximum_discount_value")
    private long maximumDiscountValue;
    @Column(name = "name",nullable = false,length = 300)
    private String name;
}
