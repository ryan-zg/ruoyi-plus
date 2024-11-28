package com.ruoyi.manage.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.manage.domain.Cat;

/**
 * 测试Service接口
 * 
 * @author ruoyi
 * @date 2024-11-28
 */
public interface ICatService extends IService<Cat>
{
    /**
     * 查询测试
     * 
     * @param id 测试主键
     * @return 测试
     */
    public Cat selectCatById(Long id);

    /**
     * 查询测试列表
     * 
     * @param cat 测试
     * @return 测试集合
     */
    public List<Cat> selectCatList(Cat cat);

    /**
     * 新增测试
     * 
     * @param cat 测试
     * @return 结果
     */
    public int insertCat(Cat cat);

    boolean saveCat (Cat cat);

    /**
     * 修改测试
     *
     * @param cat 测试
     * @return 结果
     */
    public boolean updateCat(Cat cat);

    /**
     * 批量删除测试
     * 
     * @param ids 需要删除的测试主键集合
     * @return 结果
     */
    public int deleteCatByIds(Long[] ids);

    /**
     * 删除测试信息
     * 
     * @param id 测试主键
     * @return 结果
     */
    public int deleteCatById(Long id);

    boolean removeByIds (List<Long> ids);
}
