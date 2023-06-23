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
@Entity(name = "image")
@Table(name = "image")

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private long id;
    @Column(name = "size")
    private long size;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "link")
    private String link;
    @Column(name = "uploaded_at")
    private Timestamp uploadedAt;
    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploaded_by;
}
