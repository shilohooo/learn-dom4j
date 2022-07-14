package org.shiloh.pojo;

import java.util.Date;
import java.util.List;

/**
 * 表信息
 *
 * @author shiloh
 * @date 2022/7/14 15:52
 */
public class Table {
    /**
     * 表名称
     */
    private String name;

    /**
     * 表的中文名称
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
    private List<Column> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPrimaryKeyRef() {
        return primaryKeyRef;
    }

    public void setPrimaryKeyRef(String primaryKeyRef) {
        this.primaryKeyRef = primaryKeyRef;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", creator='" + creator + '\'' +
                ", createDate=" + createDate +
                ", primaryKeyRef='" + primaryKeyRef + '\'' +
                '}';
    }
}
