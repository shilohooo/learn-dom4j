package org.shiloh.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shiloh
 * @date 2022/7/17 14:56
 */
public class FreemarkerTests {
    private static final String TEMPLATE_PATH = "src/main/resources/templates";

    private static final String CLASSPATH = "src/main/java/org/shiloh/entity";

    @Test
    public void testGenerateJavaSourceCode() {
        // 创建 freemarker 实例，指定 freemarker 版本
        final Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        // 指定实体类文件输出文件
        try (final BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        Files.newOutputStream(Paths.get(CLASSPATH + "/User.java"))
                )
        )){
            // 获取模板文件
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // 创建数据模型
            final Map<String, Object> dataMap = new HashMap<>(16);
            // 插入模板变量与对应的值
            dataMap.put("classpath", "org.shiloh.freemarker.entity");
            dataMap.put("tableName", "sys_user");
            dataMap.put("tableComment", "系统用户信息表");
            dataMap.put("className", "User");
            dataMap.put("id", "id");
            dataMap.put("username", "username");
            dataMap.put("usernameColType", "varchar");
            dataMap.put("usernameColLength", "50");
            dataMap.put("usernameComment", "用户名");
            dataMap.put("password", "password");
            dataMap.put("passwordColType", "varchar");
            dataMap.put("passwordColLength", "255");
            dataMap.put("passwordComment", "密码");
            dataMap.put("columns", this.getColumns());
            // 加载模板文件
            final Template template = configuration.getTemplate("entity.ftl");
            // 生成源代码文件
            template.process(dataMap,bufferedWriter);
            System.out.println("User.java 文件生成成功");
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getColumns() {
        final List<String> columns = new ArrayList<>();
        columns.add("id");
        columns.add("username");
        columns.add("password");
        columns.add("gender");
        columns.add("email");
        return columns;
    }
}
