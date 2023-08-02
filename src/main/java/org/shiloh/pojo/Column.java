package org.shiloh.pojo;

import lombok.Data;

/**
 * 列信息
 *
 * @author shiloh
 * @date 2022/7/14 15:55
 */
@Data
public class Column {
    /**
     * 所属表名
     */
    private String tableName;

    /**
     * 列标签的 ID 属性的值
     * <p>
     * 用于设置关联时方便查找
     */
    private String id;

    /**
     * 列名
     */
    private String name;

    /**
     * 列对应的实体字段名，即使用小写字母开头的驼峰命名的列名称
     */
    private String fieldName;

    /**
     * 列对应的实体字段类型
     */
    private String fieldType;

    /**
     * 实体字段数据类型所属包
     * <p>
     * 数据类型属于自动导入的包时，该值为 {@code null}
     */
    private String fieldTypeQualifiedName;

    /**
     * 列的注释
     */
    private String comment;

    /**
     * 列的数据类型
     */
    private String dataType;

    /**
     * 字段长度
     */
    private Integer length;

    /**
     * 能否为空
     */
    private Boolean nullable;

    /**
     * 是否为主键
     */
    private Boolean isPrimaryKey;
}
