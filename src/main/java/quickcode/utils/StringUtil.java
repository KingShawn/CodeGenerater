package quickcode.utils;
/**
 * 
 * 〈字符串工具类〉<br> 
 * 〈功能详细描述〉
 *
 * @author Shawn Wang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class StringUtil {
    /**
     * 
     * 功能描述: 是否为空白字符串<br>
     * 〈功能详细描述〉
     *
     * @param o
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isEmpty(Object o) {
        return isNull(o) || o.toString().trim().equals("");
    }
    /**
     * 
     * 功能描述: 判读是否为null<br>
     * 〈功能详细描述〉
     *
     * @param o
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isNull(Object o) {
        return o == null;
    }
}
