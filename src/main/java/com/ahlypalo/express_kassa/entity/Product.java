package com.ahlypalo.express_kassa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  private String photoUrl;
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Merchant merchant;
}
