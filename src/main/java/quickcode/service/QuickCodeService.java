package quickcode.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import quickcode.dto.Method;
import quickcode.dto.Table;
import quickcode.utils.MapUtil;
import quickcode.utils.TemplateContext;
import quickcode.utils.TemplateParse;

@Service
public class QuickCodeService {
    private static Logger logger = LoggerFactory.getLogger(QuickCodeService.class);  
    
    /**
     * 
     * 功能描述:处理上传的excel文件 <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String uploadFileHandler(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;             
        MultipartFile multipartFile = multipartRequest.getFile("file");   
        String code = convertToMap(multipartFile);       
        return code;      
    }
    /**
     * 
     * 功能描述:将数据转化成map集合形式 <br>
     * 〈功能详细描述〉
     *
     * @param multipartFile
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private String convertToMap(MultipartFile multipartFile){
        Map<String, List<Table>> tableMap = new LinkedHashMap<String, List<Table>>();
        String code = "";
        String pkName = "";
        try{    
                //POI 解析excel
                Workbook wb = WorkbookFactory.create(multipartFile.getInputStream());      
                Sheet sheet = wb.getSheetAt(0);
                if(sheet != null && sheet.getLastRowNum() != 0){
                String tableName = sheet.getSheetName().toLowerCase();
                List<Table> tableList = new ArrayList<Table>();            
                for (int num = 1; num < sheet.getLastRowNum()+1; num++) {
                    Row row = sheet.getRow(num);
                    //字段名
                    String name =  row.getCell(0) == null ? null : row.getCell(0).getRichStringCellValue().getString();
                    //类型
                    String type = row.getCell(1) == null ? null : row.getCell(1).getRichStringCellValue().getString();
                    //长度
                    Integer length = (int) Math.floor(Float.valueOf(row.getCell(2) == null ? null : row.getCell(2).toString()));
                    //精度
                    Integer precision = (int) Math.floor(Float.valueOf(row.getCell(3) == null ? null : row.getCell(3).toString()));
                    //非空（0/1）
                    String notnull = row.getCell(4) == null ? null : row.getCell(4).toString();
                    //主键（0/1）
                    Integer pk = (int) Math.floor(Float.valueOf(row.getCell(5) == null ? null : row.getCell(5).toString()));
                    //判断是否为主键
                    if(Integer.valueOf(1).equals(pk)){
                        pkName = name;
                    }
                    //备注
                    String comment = row.getCell(6) == null ? null : row.getCell(6).getRichStringCellValue().getString();
                    tableList.add(new Table(pk, name, type, length, precision, notnull , comment));
                    tableMap.put(tableName , tableList);
                }
            }
            code = this.generateCode(tableMap , pkName);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return code;  
        
    }
    
    /**
     * 
     * 功能描述:获取SQL和Entity代码 <br>
     * 〈功能详细描述〉
     *
     * @param tableMap
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private String generateCode(Map<String, List<Table>> tableMap ,String pkName){
        StringBuilder  code = new StringBuilder("<br/>");
        //处理SQL
        Map<String ,Object> sqlMap = new HashMap<String ,Object>();
        sqlMap.put("codeMap", tableMap);
        sqlMap.put("pkName", pkName); 
        String sql = TemplateParse.parse(sqlMap, TemplateContext.getTemplate("sql")); 
        code.append(sql+"</br>");
        //处理Entity     
        String className = "";
        List<Table> fieldList = new ArrayList<Table>();
        List<Method> methodList = new ArrayList<Method>();
        for(Entry<String,List<Table>> entry : tableMap.entrySet()){
            className = entry.getKey();
            fieldList = entry.getValue();
            if(fieldList != null){
            for(Table table : fieldList){
              //首字母大写
              String upperFieldName =  table.getName().substring(0,1).toUpperCase()+ table.getName().substring(1,table.getName().length());
              //备注
              String comment = table.getComment();
              //字段名
              String fieldName =  table.getName();
              //主键
              Integer pk = table.getPk();
              //类型
              String type = MapUtil.getType(table.getType());
              methodList.add(new Method(upperFieldName, fieldName, type, comment,pk));
              }
           }
        }
        Map<String ,Object> beanMap = new HashMap<String ,Object>();
        beanMap.put("className", className);
        beanMap.put("methodList", methodList);
        String bean = TemplateParse.parse(beanMap, TemplateContext.getTemplate("entity"));  
        code.append(bean);
        return code.toString();        
    }
}
