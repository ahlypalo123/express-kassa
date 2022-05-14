package com.ahlypalo.express_kassa.entity;

import com.ahlypalo.express_kassa.config.HashMapConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
public class ReceiptTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Merchant merchant;
    private boolean isActive = false;
    @Convert(converter = HashMapConverter.class)
    @Column(columnDefinition="text")
    private Map<String, Object> data;

}
