package com.ahlypalo.express_kassa.entity;

import com.ahlypalo.express_kassa.config.JsonNodeDeserializer;
import com.ahlypalo.express_kassa.config.JsonNodeSerializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ReceiptTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Merchant merchant;
    @JsonSerialize(using = JsonNodeSerializer.class)
    @JsonDeserialize(using = JsonNodeDeserializer.class)
    private JsonNode node;

}
