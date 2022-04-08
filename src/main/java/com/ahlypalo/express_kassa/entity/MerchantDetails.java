package com.ahlypalo.express_kassa.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MerchantDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String address;
    private String name;
    private BigDecimal taxPercent;
    private String fiscalNumber;
    private String inn;
    private String taxType;
}
