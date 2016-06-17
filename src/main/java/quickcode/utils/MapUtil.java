package quickcode.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
 private static Map<String,String> typeMap = new HashMap<String,String>();
 static{
     typeMap.put("bigint", "Long");
     typeMap.put("varchar", "String");
     typeMap.put("char", "String");
     typeMap.put("int", "Integer");
     typeMap.put("text", "String");
 }
 
 public static String getType(String type){
     return typeMap.get(type);
 }
}
