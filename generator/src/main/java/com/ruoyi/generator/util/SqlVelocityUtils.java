package com.ruoyi.generator.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import java.io.StringWriter;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;

public class SqlVelocityUtils {
    
    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 渲染模板
     * 
     * @param template 模板文件路径
     * @param context 模板上下文
     * @return 渲染后的内容
     */
    public static String getTemplate(String template, VelocityContext context) {
        try (StringWriter sw = new StringWriter()) {
            // 获取模板
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            // 渲染模板
            tpl.merge(context, sw);
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException("渲染模板失败，表名：" + template, e);
        }
    }
} 