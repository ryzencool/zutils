package com.marsh.zutils.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateIntervalTrendResult {

    private String stringResult;

    private Integer intResult;

    private Double doubleResult;

    private LocalDateTime date;

}
