package org.shiloh.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.experimental.UtilityClass;
import org.shiloh.pojo.Table;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体生成器
 *
 * @author shiloh
 * @date 2023/8/2 16:38
 */
@UtilityClass
public class EntityGenerator {
    private static final String TEMPLATE_PATH = "src/main/resources/templates";

    private static final String CLASSPATH = "src/main/java/org/shiloh/entity/";

    /**
     * 根据表信息生成实体类
     *
     * @param tables 表信息集合
     * @throws Exception 生成失败抛出的异常
     * @author shiloh
     * @date 2023/8/2 16:38
     */
    public static void generate(List<Table> tables) throws Exception {
        // 如果指定的包不存在，则新建一个
        createPackageIfNotExists();
        // 创建 freemarker 实例，指定 freemarker 版本
        final Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        for (Table table : tables) {
            // 指定实体类文件输出文件
            try (final BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            Files.newOutputStream(Paths.get(CLASSPATH + table.getEntityName() + ".java"))
                    )
            )) {
                // 获取模板文件
                configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
                // 创建数据模型
                final Map<String, Object> dataMap = new HashMap<>(16);
                // 插入模板变量与对应的值
                dataMap.put("classpath", "org.shiloh.entity");
                dataMap.put("table", table);
                dataMap.put("packages", table.getPackages());
                // 加载模板文件
                final Template template = configuration.getTemplate("entity.ftl");
                // 生成源代码文件
                template.process(dataMap, bufferedWriter);
                System.out.println(table.getEntityName() + ".java 文件生成成功");
            }
        }
    }

    /**
     * 如果指定的包不存在，则新建一个
     *
     * @author shiloh
     * @date 2023/8/2 16:59
     */
    private static void createPackageIfNotExists() throws IOException {
        final Path path = Paths.get(CLASSPATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
}
