package org.shiloh;

import cn.hutool.core.util.StrUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.shiloh.common.util.FieldTypeMappingUtils;
import org.shiloh.pojo.Column;
import org.shiloh.pojo.Table;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.shiloh.common.constant.AttributeNameConstants.ID;
import static org.shiloh.common.constant.AttributeNameConstants.REF;
import static org.shiloh.common.constant.ColumnConstants.NULLABLE_FLAG;
import static org.shiloh.common.constant.ElementNameConstants.*;
import static org.shiloh.common.constant.XPathConstants.TABLES_PATH;

/**
 * dom4j 解析 pdm 文件示例
 * @author shiloh
 * @date 2022/7/13 17:44
 */
public class Dom4jExample {
    public static void main(String[] args) {
        final SAXReader saxReader = new SAXReader();
        try {
            // 获取 classpath 下的 xml 文件
            final URL url = Dom4jExample.class
                    .getClassLoader()
                    .getResource("test1.xml");
            // 读取
            final Document document = saxReader.read(url);
            // 获取根元素
            final Element rootElement = document.getRootElement();
            // 使用 XPATH 语法获取 Tables 元素
            final Element tablesEle = (Element) rootElement.selectSingleNode(TABLES_PATH);
            // 获取 Tables 元素下的所有 Table 元素
            final List<Element> tableElements = tablesEle.elements();
            final List<Table> tables = new ArrayList<>(tableElements.size());
            // 获取所有表的信息
            tableElements.forEach(tableEle -> {
                final Table table = getTableInfo(tableEle);
                tables.add(table);
            });
            // 测试输出表信息
            tables.forEach(table -> {
                System.out.println("============= Table Info =============");
                System.out.println(table);
                // 输出列信息
                System.out.println("============= Column Info =============");
                table.getColumns().forEach(System.out::println);
            });
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取表信息
     *
     * @param tableEle 表元素
     * @return 表对象
     * @author shiloh
     * @date 2022/7/14 16:13
     */
    private static Table getTableInfo(Element tableEle) {
        final Table table = new Table();
        // 获取表英文名称
        final String tableName = tableEle.elementText(CODE);
        table.setName(tableName);
        // 获取表中文名称
        table.setComment(tableEle.elementText(COMMENT));
        // 获取表的创建人姓名
        table.setCreator(tableEle.elementText(CREATOR));
        // 获取表的创建日期
        final long creationDate = Long.parseLong(tableEle.elementText(CREATION_DATE));
        table.setCreateDate(new Date(creationDate));
        // 查找表的主键 ref Id
        final String primaryKeyRef = tableEle.element(KEYS)
                .element(KEY)
                .element(KEY_DOT_COLUMNS)
                .element(COLUMN)
                .attributeValue(REF);
        table.setPrimaryKeyRef(primaryKeyRef);
        // 查找表下面的列
        final List<Column> columns = getColumnsInfo(tableEle, primaryKeyRef);
        // 设置列所属的表名称
        columns.forEach(column -> column.setTableName(tableName));

        table.setColumns(columns);
        return table;
    }

    /**
     * 获取指定表下的所有列信息
     *
     * @param tableEle      指定表元素
     * @param primaryKeyRef 表主键的 ref Id
     * @return 指定表下的所有列信息
     * @author shiloh
     * @date 2022/7/14 16:26
     */
    private static List<Column> getColumnsInfo(Element tableEle, String primaryKeyRef) {
        // 获取所有列元素
        final List<Element> columnElements = tableEle.element(COLUMNS).elements();
        final List<Column> columns = new ArrayList<>(columnElements.size());

        columnElements.forEach(columnEle -> {
            final Column column = new Column();
            // 获取列的 ID
            final String colId = columnEle.attributeValue(ID);
            column.setId(colId);
            // 判断是否为主键
            column.setIsPrimaryKey(primaryKeyRef.equals(colId));
            // 获取列名称
            final String colName = columnEle.elementText(CODE);
            column.setName(colName);
            // 列名转驼峰，设置字段名称
            final String fieldName = StrUtil.toCamelCase(colName);
            column.setFieldName(StrUtil.isBlank(fieldName) ? colName : fieldName);
            // 获取列注释
            column.setComment(columnEle.elementText(NAME));
            // 获取列的数据类型
            final String columnType = columnEle.elementText(DATA_TYPE);
            column.setDataType(columnType);
            // 获取列的数据类型对应的实体字段类型
            final String fieldType = FieldTypeMappingUtils.getFieldType(columnType);
            column.setFieldType(fieldType);
            // 获取列的字段长度
            final Element lengthEle = columnEle.element(LENGTH);
            if (lengthEle != null) {
                column.setLength(Integer.valueOf(lengthEle.getText()));
            }
            // 获取列能否为空的标识
            column.setNullable(!NULLABLE_FLAG.equals(columnEle.elementText(COLUMN_DOT_MANDATORY)));
            columns.add(column);
        });
        return columns;
    }
}
