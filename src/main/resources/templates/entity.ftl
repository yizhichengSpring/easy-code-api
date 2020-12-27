package ${entity.packageName};
<#if entity.openSerializable>
import java.io.Serializable;
</#if>
import java.util.Date;
<#if entity.openLombok>
import lombok.Data;
</#if>
<#if entity.openSwagger>
import io.swagger.annotations.ApiModelProperty;
</#if>
/**
* @author ${entity.auther}
* @Description ${entity.description}
* @Date ${entity.createDate}
**/
<#if entity.openLombok>
@Data
</#if>
public class ${entity.className} <#if entity.openSerializable>implements Serializable</#if> {
<#if entity.openSerializable>
    private static final long serialVersionUID = -1L;
</#if>
<#list entity.columns as column>
    /**
    * ${column.columnRemark}
    */
 <#if entity.openSwagger>
    @ApiModelProperty("${column.columnRemark}")
 </#if>
    private ${column.columnType} ${column.columnName};
</#list>
}