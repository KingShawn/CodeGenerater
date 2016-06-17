<pre>
	package ${packageName};<br/> 
	@SuppressWarnings("serial")<br/>
	@Entity(name = "")<br/>
    public class ${className} implements Serializable {<br/>
 
<#list methodList as field>
	private ${field.type} ${field.fieldName}; // ${field.comment}<br/>
</#list> 
<br/>
<#list methodList as field> 
    public ${field.type} get${field.upperFieldName}() {<br/>
        return ${field.fieldName};<br/>
    }<br/>
    <br/>    public void set${field.upperFieldName}(${field.type} ${field.fieldName}) {<br/>
        this.${field.fieldName} = ${field.fieldName};<br/>
    }<br/><br/>
</#list>
</pre>
}