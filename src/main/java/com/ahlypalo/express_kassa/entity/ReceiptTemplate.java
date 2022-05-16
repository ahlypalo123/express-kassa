package com.ahlypalo.express_kassa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Data
@TypeDef(name = "json", typeClass = JsonType.class)
public class ReceiptTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Merchant merchant;
    private boolean isActive = false;
    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private JsonNode data;

}
