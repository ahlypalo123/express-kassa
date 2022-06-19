package com.ahlypalo.express_kassa.entity;

import com.ahlypalo.express_kassa.config.HashMapConverter;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

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
    @OneToOne(cascade = CascadeType.ALL)
    private Shift shift;
}
