package org.shiloh.pojo;

/**
 * 列信息
 *
 * @author shiloh
 * @date 2022/7/14 15:55
 */
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldTypeQualifiedName() {
        return fieldTypeQualifiedName;
    }

    public void setFieldTypeQualifiedName(String fieldTypeQualifiedName) {
        this.fieldTypeQualifiedName = fieldTypeQualifiedName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Boolean getIsPrimaryKey() {
        return this.isPrimaryKey;
    }

    public void setIsPrimaryKey(Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    @Override
    public String toString() {
        return "Column{" +
                "tableName='" + tableName + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", fieldTypeQualifiedName='" + fieldTypeQualifiedName + '\'' +
                ", comment='" + comment + '\'' +
                ", dataType='" + dataType + '\'' +
                ", length=" + length +
                ", nullable=" + nullable +
                ", isPrimaryKey=" + isPrimaryKey +
                '}';
    }
}
