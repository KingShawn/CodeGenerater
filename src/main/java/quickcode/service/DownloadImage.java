package quickcode.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadImage {

    
    public void download(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params) {
        InputStream in = null;
        OutputStream out = null;
        String imurl=params.get("url").toString();
        String imname=imurl.substring(imurl.lastIndexOf("/")+1);
        try {
            URL url = new URL(imurl);
            URLConnection con = url.openConnection(); 
            in = con.getInputStream(); 
            response.setContentType("image/jpeg");
            response.setHeader("Content-Disposition","attachment;filename=\""+imname+"\"");
            response.setCharacterEncoding("utf-8");
            out = response.getOutputStream();
            
            int len = -1;
            byte[] buffer = new byte[1024]; 
            
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            } 
            out.flush();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }
}  
     
