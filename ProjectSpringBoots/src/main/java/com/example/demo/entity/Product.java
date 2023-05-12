package com.example.demo.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.naming.Name;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
@Table(name = "product")
@TypeDef( name = "json",
        typeClass = JsonStringType.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;
    @Column(name ="name",nullable = false,length = 255)
    private String name;
    @Column(name = "slug",nullable = false,length = 300)
    private String slug;
    @Column(name = "brand_id")
    private int brandId;
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
    @Column(name = "is_available")
    private Boolean isAvailable;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "price")
    private long price;
    @Type(type = "json")
    @Column(name = "onfeet_images",columnDefinition = "json")
    private ArrayList<String> onfeetImages;
    @Type(type = "json")
    @Column(name = "product_images",columnDefinition = "json")
    private ArrayList<String> productImages;
    @Column(name = "total_sold")
    private int totalSold;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

}
