package com.taviak.expresskassa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@AllArgsConstructor
public class CodeDto {

    private Integer code;
    private DateTime created;

}
