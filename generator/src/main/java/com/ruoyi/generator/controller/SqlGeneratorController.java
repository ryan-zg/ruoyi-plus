package com.ruoyi.generator.controller;

import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.service.IGenTableColumnService;
import com.ruoyi.generator.service.IGenTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.generator.domain.TableInfo;
import com.ruoyi.generator.service.impl.SqlGeneratorService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tool/sql")
public class SqlGeneratorController extends BaseController {
    
    @Autowired
    private SqlGeneratorService sqlGeneratorService;
    
    @Autowired
    private IGenTableService genTableService;
    
    @Autowired
    private IGenTableColumnService genTableColumnService;
    
    /**
     * 预览SQL
     */
    @PreAuthorize("@ss.hasPermi('tool:sql:preview')")
    @PostMapping("/preview")
    public AjaxResult previewSql(@RequestBody TableInfo tableInfo) {
        String sql = sqlGeneratorService.generateSql(tableInfo);
        return AjaxResult.success("操作成功", sql);
    }
    
    /**
     * 下载SQL文件
     */
    @PreAuthorize("@ss.hasPermi('tool:sql:download')")
    @PostMapping("/download")
    public void downloadSql(@RequestBody TableInfo tableInfo, HttpServletResponse response) throws IOException {
        byte[] data = sqlGeneratorService.downloadSql(tableInfo);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=" + tableInfo.getTableName() + ".sql");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.getOutputStream().write(data);
    }
    
    /**
     * 获取表字段列表
     */
    @GetMapping("/column/{tableName}")
    public AjaxResult getTableColumns(@PathVariable("tableName") String tableName) {
        List<GenTableColumn> list = genTableColumnService.selectDbTableColumnsByName(tableName);
        return AjaxResult.success(list);
    }
} 