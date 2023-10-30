package com.prm392.gearcom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true, length = 25)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String username;

    @Column(name = "name", nullable = false)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String name;

    @Column(name = "phone", unique = true, length = 15)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String phone;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String password;

    @Lob
    @Column(name = "address")
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Bill> bills = new ArrayList<>();

}