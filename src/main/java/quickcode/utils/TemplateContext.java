package quickcode.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;



/**
 * 
 * 〈模板文件初始化〉<br>
 * 〈把对应目录ftl模板文件读入内存〉
 * 
 * 
 */
public class TemplateContext {
    private static Logger logger = LoggerFactory.getLogger(TemplateContext.class);

    /**
     * 默认编码：UTF-8
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 布局模板缓存
     */
    public static Map<String, String> templateCache = null;
    /**
     * 加载类路径模板
     */
    static {
        templateCache = new HashMap<String, String>();
        String ftlPath = "/WEB-INF/freemarker/*.ftl";
        init(ftlPath);
    }





    /**
     * 
     * 功能描述: 从缓存中获取布局模板<br>
     * 〈功能详细描述〉
     * 
     * @param
     * @return String
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getTemplate(String cacheKey) {
        return templateCache.get(cacheKey);
    }


    /**
     * 
     * 功能描述: 使用指定的缓存key将模板文件缓存到内存（layoutTemplateCache）<br>
     * 〈功能详细描述〉
     * 
     * @param
     * @return void
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void loadToCache(String cacheKey, Resource resource) {
        if (resource != null && resource.isReadable()) {
            String template = parseResource(resource);
            templateCache.put(cacheKey, template);
        }
    }

    /**
     * 
     * 功能描述: 将资源文件解析成字符串<br>
     * 使用UTF-8编码解析 〈功能详细描述〉
     * 
     * @param
     * @return String
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String parseResource(Resource resource) {
        StringBuilder template = new StringBuilder("");
        InputStream in = null;
        BufferedReader br = null;
        try {
            in = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(in, DEFAULT_ENCODING);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                template.append(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            free(br);
        }
        return template.toString();
    }

    /**
     * 
     * 功能描述: 释放资源<br>
     * 〈功能详细描述〉
     * 
     * @param
     * @return void
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void free(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                logger.error("解析模板文件流关闭异常：" + e.getMessage());
            }
        }
    }
    /**
     * 
     * 功能描述: <br>
     *
     * @param path
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void init(String path) {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
        ServletContext servletContext = webApplicationContext.getServletContext();  
        ResourcePatternResolver classPathResource = new ServletContextResourcePatternResolver(servletContext);
        try {
            Resource[] resources = classPathResource.getResources(path);
            if (resources!=null && resources.length>0){
                for (Resource r : resources){
                    loadToCacheResource(r);
                }
            }
        } catch (IOException e) {
            logger.error("加载 freemarker资源文件失败！");
        }
    }
    
    public static void loadToCacheResource(Resource resource) {
        String cacheKey = resource.getFilename();
        loadToCache(cacheKey.replace(".ftl", ""), resource);
    }
}
