package quickcode.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import quickcode.service.QuickCodeService;
@Controller
public class QuickCodeController {

    @Autowired
    private QuickCodeService quickCodeService;
    /**
     * 
     * 功能描述:首页 <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/main.action")  
    public ModelAndView main() {  
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        return mv;            
    }  
    
    /**
     * 
     * 功能描述:处理上传文件 <br>
     * 〈功能详细描述〉
     *
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/uploadFile.action")  
    @ResponseBody
    public String uploadFile(HttpServletRequest request) {    
        String code = "";
       try{
        code = quickCodeService.uploadFileHandler(request);
       }catch(Exception e){
           e.printStackTrace();
       }
       return code;       
    }  
}
