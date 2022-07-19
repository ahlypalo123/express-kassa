package com.taviak.expresskassa.entity;

import com.taviak.expresskassa.config.DateSerializer;
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
