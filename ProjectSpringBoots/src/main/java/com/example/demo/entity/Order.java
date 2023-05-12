package com.example.demo.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order")
@Table(name = "order")
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "note",columnDefinition = "TEXT")
    private String note;
    @Column(name = "product_price")
    private long productPrice;
    @Column(name = "total_price")
    private long totalPrice;
    @Column(name = "receiver_name",nullable = false,length = 255)
    private String receiverName;
    @Column(name = "receiver_address",nullable = false,length = 255)
    private String receiverAddress;
    @Column(name = "receiver_phone",nullable = false,length = 255)
    private String receiverPhone;
    @Column(name = "size",nullable = false)
    private int size;
    @Column(name = "status",nullable = false)
    private int status;
    @Type(type = "json")
    @Column(name = "promotion", columnDefinition = "json")
    private UsedPromotion promotion;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UsedPromotion {
        private String couponCode;

        private int discountType;

        private long discountValue;

        private long maximumDiscountValue;
    }
    @ManyToOne
    @JoinColumn(name = "buyer")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "modified_by")
    private User modifiedBy;
}
