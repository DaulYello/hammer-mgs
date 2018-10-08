package com.bm.fmkj.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class DownLoadUtil {
	public static Boolean downLoad(String path,HttpServletResponse response) throws Exception {
		File file=new File(path);
        System.out.println(file);
        if (file.exists()) {  
            String filename = file.getName();  
            InputStream fis = new BufferedInputStream(new FileInputStream(file));  
            response.reset();  
            response.setContentType("application/x-download");  
            response.addHeader("Content-Disposition","attachment;filename="+ new String(filename.getBytes(),"UTF-8"));  
            response.addHeader("Content-Length", "" + file.length());  
            OutputStream out = new BufferedOutputStream(response.getOutputStream());  
            response.setContentType("application/octet-stream");  
            byte[] buffer = new byte[1024 * 1024 * 20];  
            int i = -1;  
            while ((i = fis.read(buffer)) != -1) {  
                out .write(buffer, 0, i);  
            }
            fis.close();  
            out.flush();  
            out.close();  
        } else {  
            PrintWriter out = response.getWriter();  
            out.print("<script>");  
            out.print("alert(\"not find the file\")");  
            out.print("</script>");  
        }
        return true;
	}
}
