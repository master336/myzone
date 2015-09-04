package com.web.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.alibaba.fastjson.JSON;
import com.web.web.exception.GxwebException;
import com.web.web.vo.Res;
public class ExceptionIntercepter extends SimpleMappingExceptionResolver {
	private static Logger log = Logger.getLogger(ExceptionIntercepter.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		log.info("发生异常了..."+request.getRequestURI());
		String msg = null;
		if(ex instanceof GxwebException){
			msg = ex.getMessage();
		}else{
			log.error(ex.getMessage());
			msg = "网络繁忙...请稍后重试!";
		}
		
		String accept = request.getHeader("accept");
		String with = request.getHeader("X-Requested-With");
		//页面返回形式
		if ((accept.indexOf("application/json") > -1 && (with!= null && with.indexOf("HBuilder") == -1 && with.indexOf("XMLHttpRequest") > -1))) {  
            return new ModelAndView("/common/message")
    		.addObject("reRequestTime",3000)
    		.addObject("isSuccess",false)
    		.addObject("reRequestPath","/gac/index.html");
        } else {// JSON格式返回
            try {
            	//log.info(msg);
    			response.setContentType("text/html;charset=utf-8");
    			response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(new Res(Res.fail,msg)));  
                writer.flush();  
            } catch (IOException e) {  
                //e.printStackTrace();
            }  
            return null;
        }
	}
}
