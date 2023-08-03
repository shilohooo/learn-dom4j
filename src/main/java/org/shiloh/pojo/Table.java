package org.shiloh.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 表信息
 *
 * @author shiloh
 * @date 2022/7/14 15:52
 */
@Data
@ToString
public class Table {
    /**
     * 表名称
     */
    private String name;

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 表的创建人
     */
    private String creator;

    /**
     * 表的创建时间
     */
    private Date createDate;

    /**
     * 表 Keys 子标签下的 Key子标签的 Id 属性的值
     * <p>
     * 对应表 Columns 子标签下的 Id 等于该字段的 Column 子标签
     * <p>
     * 主要用于查资主键字段信息
     */
    private String primaryKeyRef;

    /**
     * 表中的列信息
     */
    @ToString.Exclude
    private List<Column> columns;

    /**
     * 需要导入的包的集合
     */
    private Set<String> dependencies;
}
