package com.ahlypalo.express_kassa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@AllArgsConstructor
public class CodeDto {

    private Integer code;
    private DateTime created;

}
