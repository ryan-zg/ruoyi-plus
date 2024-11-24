package com.ruoyi.generator.service.impl;

import org.springframework.stereotype.Service;
import com.ruoyi.generator.domain.TableInfo;
import com.ruoyi.generator.util.SqlVelocityUtils;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SqlGeneratorService {
    
    private static final Logger log = LoggerFactory.getLogger(SqlGeneratorService.class);
    
    /**
     * 生成SQL
     */
    public String generateSql(TableInfo tableInfo) {
        // 初始化模板引擎
        SqlVelocityUtils.initVelocity();
        
        try {
            // 打印接收到的数据
            log.info("Received table info: {}", tableInfo);
            
            // 设置模板变量信息
            VelocityContext context = new VelocityContext();
            context.put("tableName", tableInfo.getTableName());
            context.put("tableComment", tableInfo.getTableComment());
            context.put("columns", tableInfo.getColumns());
            context.put("indexes", tableInfo.getIndexes());
            
            // 记录日志
            log.info("Table Info: {}", tableInfo);
            
            // 获取模板文件路径
            String template = "vm/sql/mysql.vm";
            
            // 渲染模板
            String result = SqlVelocityUtils.getTemplate(template, context);
            log.info("Generated SQL: {}", result);
            
            return result;
        } catch (Exception e) {
            log.error("Generate SQL failed", e);
            throw new RuntimeException("Generate SQL failed: " + e.getMessage());
        }
    }
    
    /**
     * 生成SQL文件
     */
    public byte[] downloadSql(TableInfo tableInfo) {
        return generateSql(tableInfo).getBytes();
    }
} 