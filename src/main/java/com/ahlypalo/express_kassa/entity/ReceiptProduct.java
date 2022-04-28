package com.ahlypalo.express_kassa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class ReceiptProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer count;
    @OneToOne
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Check check;

}
