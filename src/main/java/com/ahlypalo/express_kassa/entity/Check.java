package com.ahlypalo.express_kassa.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fiscal_check")
@Data
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Product> products;
    private Date date;
    private BigDecimal total;
    @OneToOne
    private Shift shift;
    @OneToOne
    private MerchantDetails merchantDetails;
    @ManyToOne
    private Merchant merchant;
}
