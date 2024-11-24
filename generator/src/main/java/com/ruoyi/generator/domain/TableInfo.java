package com.ruoyi.generator.domain;

import java.util.List;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class TableInfo extends BaseEntity {
    private String tableName;
    private String tableComment;
    private List<ColumnInfo> columns;
    private List<IndexInfo> indexes;
} 