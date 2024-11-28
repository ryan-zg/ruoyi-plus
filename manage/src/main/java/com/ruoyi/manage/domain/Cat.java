package com.ruoyi.manage.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("cat")
public class Cat {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long test1;
    private Integer test2;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    private String createBy;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private String updateBy;
    
    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;
    private String remark;
}
