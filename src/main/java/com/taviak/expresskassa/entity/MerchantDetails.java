package com.taviak.expresskassa.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Embeddable
public class MerchantDetails {
    private String address;
    private String name;
    private BigDecimal taxPercent;
    private String inn;
    private String taxType;
    @OneToOne(cascade = CascadeType.ALL)
    private Shift shift;
}
