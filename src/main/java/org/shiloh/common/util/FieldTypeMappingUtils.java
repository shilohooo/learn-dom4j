package org.shiloh.common.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.shiloh.common.constant.SymbolConstants.LEFT_BRACE;

/**
 * 列对应的实体字段类型映射工具
 *
 * @author shiloh
 * @date 2022/8/1 18:14
 */
public final class FieldTypeMappingUtils {
    private FieldTypeMappingUtils() {
    }

    /**
     * 列数据类型与字段数据类型映射缓存
     */
    public static final Map<String, String> FIELD_TYPE_CACHE = new HashMap<>(16);

    static {
        // bit 类型
        FIELD_TYPE_CACHE.put("bit", Boolean.class.getSimpleName());
        // 整数类型
        final String intTypeName = Integer.class.getSimpleName();
        FIELD_TYPE_CACHE.put("tinyint", intTypeName);
        FIELD_TYPE_CACHE.put("int", intTypeName);
        FIELD_TYPE_CACHE.put("bigint", Long.class.getSimpleName());
        // 浮点数类型
        FIELD_TYPE_CACHE.put("float", Float.class.getSimpleName());
        FIELD_TYPE_CACHE.put("double", Double.class.getSimpleName());
        FIELD_TYPE_CACHE.put("decimal", BigDecimal.class.getSimpleName());
        // 字符类型
        final String stringTypeName = String.class.getSimpleName();
        FIELD_TYPE_CACHE.put("char", stringTypeName);
        FIELD_TYPE_CACHE.put("varchar", stringTypeName);
        FIELD_TYPE_CACHE.put("text", stringTypeName);
        FIELD_TYPE_CACHE.put("longtext", stringTypeName);
        // 日期类型
        final String dateTypeName = Date.class.getSimpleName();
        FIELD_TYPE_CACHE.put("date", dateTypeName);
        FIELD_TYPE_CACHE.put("datetime", dateTypeName);
    }

    /**
     * 根据列的数据类型获取对应实体字段的数据类型
     *
     * @param columnType 列的数据类型
     * @return 实体字段数据类型
     * @author shiloh
     * @date 2022/8/1 18:15
     */
    public static String getFieldType(String columnType) {
        // 解析到的列数据类型字符串可能会带有类的长度（在括号中），先截取括号前的数据类型
        final String columnTypeWithoutSymbol = StringUtils.substringBefore(columnType, LEFT_BRACE);
        return FIELD_TYPE_CACHE.get(columnTypeWithoutSymbol);
    }
}
