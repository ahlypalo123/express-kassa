package com.ahlypalo.express_kassa.entity;

import com.ahlypalo.express_kassa.config.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeName;
    @JsonSerialize(using = DateSerializer.class)
    private Date endDate;
    @JsonSerialize(using = DateSerializer.class)
    private Date startDate;
}
