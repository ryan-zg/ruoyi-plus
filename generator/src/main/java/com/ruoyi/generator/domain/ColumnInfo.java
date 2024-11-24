package com.ruoyi.generator.domain;

import lombok.Data;

@Data
public class ColumnInfo {
    private String columnName;
    private String columnType;
    private Integer columnLength;
    private Boolean isNull;
    private Boolean isPk;
    private Boolean isIncrement;
    private String defaultValue;
    private String columnComment;
    private Boolean isFk;
    private String fkTableName;
    private String fkColumnName;
    private String fkDeleteRule;
} 