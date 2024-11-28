package com.ruoyi.manage.service.impl;

import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.CatMapper;
import com.ruoyi.manage.domain.Cat;
import com.ruoyi.manage.service.ICatService;

/**
 * 测试Service业务层处理
 *
 * @author ruoyi
 * @date 2024-11-28
 */
@Service
public class CatServiceImpl extends ServiceImpl<CatMapper, Cat> implements ICatService {

    @Autowired
    private CatMapper catMapper;


    @Override
    public Cat selectCatById (Long id) {
        return null;
    }

    @Override
    public List<Cat> selectCatList (Cat cat) {
        return this.lambdaQuery()
                .orderByDesc(Cat::getId)
                .list();
    }

    @Override
    public int insertCat (Cat cat) {
        return 0;
    }

    /**
     * 新增测试
     *
     * @param cat 测试对象
     * @return 结果
     */
    @Override
    public boolean saveCat(Cat cat) {
        return this.save(cat);
    }

    /**
     * 修改测试
     *
     * @param cat 测试对象
     * @return 结果
     */
    @Override
    public boolean updateCat(Cat cat) {
        // 自动填充更新时间可以通过 MyBatis-Plus 的 MetaObjectHandler 实现
        return updateById(cat);
    }

    @Override
    public int deleteCatByIds (Long[] ids) {
        return catMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int deleteCatById (Long id) {
        return catMapper.deleteById(id);
    }

    /**
     * 删除测试
     *
     * @param ids 主键数组
     * @return 结果
     */
    @Override
    public boolean removeByIds(List<Long> ids) {
        return removeByIds(ids);
    }




}
