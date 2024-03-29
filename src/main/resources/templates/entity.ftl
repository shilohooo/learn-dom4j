package ${package};

import java.io.Serializable;
import javax.persistence.*;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
<#list dependencies as dependence>
import ${dependence};
</#list>

/**
 * ${table.comment}
 *
 * @author ${table.creator}
 * @date ${.now?string("yyyy/MM/dd HH:mm")}
 */
@Entity
@Setter
@Getter
@ToString
@Table(name = "${table.name}")
@org.hibernate.annotations.Table(appliesTo = "${table.name}", comment = "${table.comment}")
public class ${table.entityName} implements Serializable {
    private static final long serialVersionUID = 1L;

<#list table.columns as column>
    /*
     * ${column.comment}
     */
    <#if column.isPrimaryKey>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    @Column(name = "${column.name}", columnDefinition = "${column.dataType} ${column.nullable?string('', 'not null')} comment '${column.comment}'")
    private ${column.fieldType} ${column.fieldName};
    <#if column_has_next>

    </#if>
</#list>
}
