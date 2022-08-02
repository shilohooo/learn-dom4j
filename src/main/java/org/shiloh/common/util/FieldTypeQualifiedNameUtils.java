package org.shiloh.common.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 字段数据类型全限定类型工具类
 *
 * @author shiloh
 * @date 2022/8/2 17:49
 */
public final class FieldTypeQualifiedNameUtils {
    private FieldTypeQualifiedNameUtils() {
    }

    /**
     * 数据类型 - 全限定类名映射缓存
     */
    private static final Map<String, String> QUALIFIED_NAME_CACHE = new HashMap<>(8);

    static {
        // 日期
        QUALIFIED_NAME_CACHE.put(Date.class.getSimpleName(), Date.class.getName());
        // BigDecimal
        QUALIFIED_NAME_CACHE.put(BigDecimal.class.getSimpleName(), BigDecimal.class.getName());
    }

    /**
     * 根据字段数据类型获取该数据类型的全限定类名
     *
     * @param fieldType 字段数据类型
     * @return 全限定类名
     * @author shiloh
     * @date 2022/8/2 17:50
     */
    public static String getQualifiedNameByFieldType(String fieldType) {
        return QUALIFIED_NAME_CACHE.getOrDefault(fieldType, null);
    }
}
