package com.ahlypalo.express_kassa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NotFound;

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
    private List<ReceiptProduct> products;
    private Date date;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Float discount;
    private String customerName;
    private String employeeName;
    private Integer customerLast4;
    private BigDecimal total;
    @ManyToOne
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
