package com.canan.productapp.entity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;
    private String description;
    private BigDecimal price;


}