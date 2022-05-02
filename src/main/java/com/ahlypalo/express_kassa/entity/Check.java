package com.ahlypalo.express_kassa.entity;

import com.ahlypalo.express_kassa.config.DateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @OneToMany(mappedBy = "check", cascade = CascadeType.ALL)
    private List<ReceiptProduct> products;
    @JsonSerialize(using = DateSerializer.class)
    private Date date;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Float discount;
    private Float cash;
    private Float change;
    private String customerName;
    private String employeeName;
    private Integer customerLast4;
    private BigDecimal total;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Merchant merchant;
    private String address;
    private String name;
    private BigDecimal taxPercent;
    private String inn;
    private String taxType;

    enum PaymentMethod {
        CASH, CARD
    }
}
