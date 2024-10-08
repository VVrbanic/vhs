package com.example.VHS.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name = "user_name")
    private String userName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="total_due")
    private BigDecimal totalDue;
    @Column(name="unpaid_due")
    private BigDecimal unpaidDue;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    };

    public User(){};

    public void generateUserFromUserValidation(UserValidation userValidation) {
        this.name = userValidation.getName();
        this.email = userValidation.getEmail();
        this.password = userValidation.getPassword();
        this.userName = userValidation.getUserName();
    }
}
