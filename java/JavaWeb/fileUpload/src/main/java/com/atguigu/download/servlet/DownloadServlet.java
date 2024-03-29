package com.atguigu.download.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-msdownload"); 
		
		String fileName = "文件下载.pptx";
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		
		OutputStream out = response.getOutputStream();
		String pptFileName = "C:\\Users\\Think Pad\\Desktop\\__正在上课__\\11.尚硅谷_JavaWEB_监听器.pptx";
		
		InputStream in = new FileInputStream(pptFileName);
		
		byte [] buffer = new byte[1024];
		int len = 0;
		
		while((len = in.read(buffer)) != -1){
			out.write(buffer, 0, len);
		}
		
		in.close();
		// 注意：输出流 out 不要关闭，因为 out 是给浏览器用的，关闭了可能会影响浏览器接收
	}

}
