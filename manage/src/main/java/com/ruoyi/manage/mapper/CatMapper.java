package com.ruoyi.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.manage.domain.Cat;
import org.apache.ibatis.annotations.Mapper;

public interface CatMapper extends BaseMapper<Cat> {
    // 只声明自定义的方法，基础的 CRUD 由 BaseMapper 提供
}
