package quickcode.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;

import freemarker.cache.TemplateLoader;
import freemarker.cache.WebappTemplateLoader;
import freemarker.ext.jsp.TaglibFactory;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

/**
 * 〈模版解析〉<br> 
 * 〈功能详细描述〉
 *
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TemplateParse implements ServletContextAware{
    private static final String DEFAULT_ENCODING = "UTF-8";

    private static Logger logger = LoggerFactory.getLogger(TemplateParse.class);
    
    private static Configuration cfg = new Configuration();
    
    static {
        cfg.setClassicCompatible(true);
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding(DEFAULT_ENCODING);
        cfg.setOutputEncoding(DEFAULT_ENCODING);
    }

    /**
     * 
     * 功能描述: 解析<br>
     * 〈功能详细描述〉
     * 
     * @param
     * @return String
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String parse(Object entity, String templateFile) {
        if (StringUtil.isEmpty(templateFile)) {
            logger.info("templateFile is blank");
            return "";
        }

        Template template = beforeParse(templateFile);
        StringWriter writer = new StringWriter();
        try {
             template.process(entity, writer);
        } catch (TemplateException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (RuntimeException runEx){
            logger.error("解析模板报错，map:{},templateFile:{}",entity,templateFile);
        }
        return writer.toString();
    }

    /**
     * 
     * 功能描述: 解析之前将templateFile转换成Template对象，并关联freemarker配置信息<br>
     * 〈功能详细描述〉
     * 
     * 
     * @param
     * @return Template
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static Template beforeParse(String templateFile) {
        Template template = null;
        try {
            StringReader reader = new StringReader(templateFile);
            template = new Template("", reader, cfg);
            template.setNumberFormat("#");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return template;
    }

    public static void registerAPI(Map<String,Object> variables) {
        try {
            cfg.setAllSharedVariables(new SimpleHash(variables, cfg.getObjectWrapper()));
        } catch (TemplateModelException e) {
            logger.error("注册api失败",e);
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        TemplateLoader ftlLoader = new WebappTemplateLoader(servletContext);
        cfg.setTemplateLoader(ftlLoader);
        cfg.setSharedVariable("JspTaglibs",new TaglibFactory(servletContext));
    }
}
