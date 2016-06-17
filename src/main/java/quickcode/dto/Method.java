package quickcode.dto;

public class Method {
    //首字母大写
    public String upperFieldName;
    //字段名
    public String fieldName;   
    //主键
    public Integer key;
    //类型
    public String type;
    //注释
    public String comment;
    
    public String getUpperFieldName() {
        return upperFieldName;
    }
    public void setUpperFieldName(String upperFieldName) {
        this.upperFieldName = upperFieldName;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }   
    public Integer getKey() {
        return key;
    }
    public void setKey(Integer key) {
        this.key = key;
    }
    public Method(String upperFieldName, String fieldName, String type, String comment,Integer key) {
        this.upperFieldName = upperFieldName;
        this.fieldName = fieldName;
        this.type = type;
        this.comment = comment;
        this.key = key;
    }
    
    

}
