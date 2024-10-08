package com.example.VHS.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="PRICES")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    @NotNull(message = "Price is mandatroy")
    private BigDecimal price;

    @Column(name = "date_from")
    private LocalDateTime dateFrom;

    @Column(name = "date_until")
    private LocalDateTime dateUntil;

    @Column (name = "active")
    private Boolean active;

}
