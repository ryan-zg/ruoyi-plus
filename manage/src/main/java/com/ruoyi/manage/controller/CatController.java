package com.ruoyi.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.manage.domain.Cat;
import com.ruoyi.manage.service.ICatService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 测试Controller
 * 
 * @author ruoyi
 * @date 2024-11-28
 */
@RestController
@RequestMapping("/manage/cat")
public class CatController extends BaseController
{
    @Autowired
    private ICatService catService;

    /**
     * 查询测试列表
     */
    @PreAuthorize("@ss.hasPermi('manage:cat:list')")
    @GetMapping("/list")
    public TableDataInfo list(Cat cat)
    {
        startPage();
        QueryWrapper<Cat> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Cat> list = catService.list(queryWrapper);
        return getDataTable(list);
    }

    /**
     * 导出测试列表
     */
    @PreAuthorize("@ss.hasPermi('manage:cat:export')")
    @Log(title = "测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Cat cat)
    {
        List<Cat> list = catService.selectCatList(cat);
        ExcelUtil<Cat> util = new ExcelUtil<Cat>(Cat.class);
        util.exportExcel(response, list, "测试数据");
    }

    /**
     * 获取测试详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:cat:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(catService.selectCatById(id));
    }

    /**
     * 新增测试
     */
    @PreAuthorize("@ss.hasPermi('manage:cat:add')")
    @Log(title = "测试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Cat cat)
    {
        return toAjax(catService.saveCat(cat));
    }

    /**
     * 修改测试
     */
    @PreAuthorize("@ss.hasPermi('manage:cat:edit')")
    @Log(title = "测试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Cat cat)
    {
        return toAjax(catService.updateCat(cat));
    }

    /**
     * 删除测试
     */
    @PreAuthorize("@ss.hasPermi('manage:cat:remove')")
    @Log(title = "测试", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(catService.deleteCatByIds(ids));
    }
}
