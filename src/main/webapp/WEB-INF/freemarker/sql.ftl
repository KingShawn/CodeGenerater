<pre>
<#list codeMap?keys as key>
    <option value="${key}" >
     <#assign columnList= codeMap[key]> 
     </option>  
     DROP TABLE `${key}`;<br/>
     CREATE TABLE `${key}` (<br/>
	 <#list columnList as column>
    `${column.name}` ${column.type}<#if column.length != 0 && column.precision != 0 >(${column.length},${column.precision})<#elseif column.length != 0 >(${column.length})
    </#if>
    <#if column.notnull == 1 >NOT NULL
    </#if>
    <#if column.pk == 1 >AUTO_INCREMENT
    </#if>
    <#if column.comment??>COMMENT '${column.comment}',<br/>
    </#if> 
    </#list>      
</#list>
PRIMARY KEY (`${pkName}`)
 <br/>
     )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT=''<br/>
</pre>
