package com.ruoyi.generator.domain;

import lombok.Data;
import java.util.List;

@Data
public class IndexInfo {
    private String indexName;
    private List<String> columnNames;
    private String indexType;  // NORMAL, UNIQUE
} 