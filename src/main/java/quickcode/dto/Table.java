package quickcode.dto;


public class Table {
    //表名
    public String tableName;
    //主键
    public Integer pk;
    //列名
    public String name;
    //类型
    public String type;
    //长度
    public Integer length;
    //精度
    public Integer precision;
    //非空
    public String notnull; 
    //注释
    public String comment;
    
    
    public Table(Integer pk, String name, String type, Integer length, Integer precision, String notnull,
            String comment) {
        this.pk = pk;
        this.name = name;
        this.type = type;
        this.length = length;
        this.precision = precision;
        this.notnull = notnull;
        this.comment = comment;
    }


    public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public Integer getPk() {
        return pk;
    }


    public void setPk(Integer pk) {
        this.pk = pk;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Integer getLength() {
        return length;
    }


    public void setLength(Integer length) {
        this.length = length;
    }


    public Integer getPrecision() {
        return precision;
    }


    public void setPrecision(Integer precision) {
        this.precision = precision;
    }


    public String getNotnull() {
        return notnull;
    }


    public void setNotnull(String notnull) {
        this.notnull = notnull;
    }


    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }


   
  
}
