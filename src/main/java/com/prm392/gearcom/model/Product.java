package com.prm392.gearcom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String name;

    @Column(name = "description", length = 500)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String description;

    @Column(name = "price", nullable = false)
    @JdbcTypeCode(SqlTypes.DECIMAL)
    private Double price;

    @Column(name = "image_url", length = 1000)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String imageUrl;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}