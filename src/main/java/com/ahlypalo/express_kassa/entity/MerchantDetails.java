package com.ahlypalo.express_kassa.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class MerchantDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String name;
    private BigDecimal taxPercent;
    private String inn;
    private String taxType;
}
