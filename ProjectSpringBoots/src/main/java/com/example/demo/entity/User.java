package com.example.demo.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Table(name = "users")
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "fullName",nullable = false,length = 200)
    private String name;
    @Column(name ="email",nullable = false,length = 200)
    private String email;
    @Type(type = "json")
    @Column(name = "roles", columnDefinition = "json")
    private List<String> roles;
    @Column(name = "phone",length = 200)
    private String phone;
    @Column(name = "status", columnDefinition = "BOOLEAN")
    private Boolean status;
    @Column(name = "createdAt")
    private Timestamp createdAt;

}
