package com.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoSevlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = new User();
        user.setUserName("user");
        user.setUserPassword("123");

        Map<String, Object> root = new HashMap<String, Object>();
        root.put("user", user);
		
      //html生成之后存放的路径  
        String dirPath = req.getSession().getServletContext().getRealPath("/templates");  
        File path = new File(dirPath);  
        //生成的文件的名字  
        String indexFileName = "index.html";  
        /** 
         * 判断是否已经存在该html文件，存在了就直接访问html ，不存在生成html文件 
         */  
            Writer out = new OutputStreamWriter(new FileOutputStream(dirPath+"/"+indexFileName),"UTF-8");  
            //生成html文件  
            FreeMarkertUtil.processTemplate("usr.ftl", root, out);   
            req.getRequestDispatcher("/templates/index.html").forward(req, resp);   
	}



	public void init(ServletConfig config) throws ServletException {  
        FreeMarkertUtil.initConfig(config.getServletContext(), "/templates");  
    }  
	
	public void destroy(){
		System.out.println("destroy....");
	}
}
