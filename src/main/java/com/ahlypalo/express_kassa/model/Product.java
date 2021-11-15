package com.ahlypalo.express_kassa.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

  private Long id;
  private String name;
  private BigDecimal price;
  private String photoUrl;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(photoUrl, product.photoUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price, photoUrl);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Product{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", price=").append(price);
    sb.append(", photoUrl='").append(photoUrl).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
