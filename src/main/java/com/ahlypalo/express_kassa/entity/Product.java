package com.ahlypalo.express_kassa.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private BigDecimal price;
  private String barCode;
  private BigDecimal amount;
  private String photoUrl;
  @ManyToOne
  private Merchant merchant;
}
