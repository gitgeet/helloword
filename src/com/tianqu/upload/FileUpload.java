package com.tianqu.upload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUpload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12332224243L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		System.out.println("method====" + method);
		if (method.equals("upload")) {
			// 上传
			upload(request, response);
		} else if (method.equals("downlist")) {
			// 进入下载列表
			downList(request, response);
		} else if (method.equals("down")) {
			// 进行下载
			down(request, response);
		}
	}

	// 上传
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = "abcd";
		try {
			// 1.创建工厂对象
			FileItemFactory factory = new DiskFileItemFactory();
			// 2.文件上传核心工具类
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置大小参数限制
			upload.setFileSizeMax(50 * 1024 * 1024);// 单个文件大小限制
			upload.setSizeMax(100 * 1024 * 1024);// 总文件大小限制
			upload.setHeaderEncoding("UTF-8");// 对中文文件编码处理

			// 判断
			if (upload.isMultipartContent(request)) {
				// 3.把请求数据转换为list集合
				List<FileItem> list = upload.parseRequest(request);
				// 遍历
				for (FileItem item : list) {
					// 判断“普通文本数据
					if (item.isFormField()) {
						// 获取名称
						String name = item.getFieldName();
						// 获取值
						String value = item.getString();
						System.out.println("value===" + value);
					} else {
						// 文件表单项
						/***** 文件上传 ********/
						// a.获取文件名称
						String name = item.getName();
						// 处理上传文件名重名问题
						// a1.先得到唯一标记
//						String id=UUID.randomUUID().toString();
						// a2.拼接文件名
//						name=id+"#"+name;
						System.out.println("name=====" + name);

						// b。得到上传目录
//						String basePath=getServletContext().getRealPath("/upload");
						String basePath = "C:\\Users\\Administrator\\Desktop\\ftp";
						int index = basePath.lastIndexOf("\\");
						String destPath = basePath.substring(0, index) + "\\" + "jiami";
						// c。创建要上传的文件对象
						File file = new File(basePath, name);
						File destFile = new File(destPath, name);

						System.out.println(file);
						System.out.println(file.getName());

						FileInputStream in = new FileInputStream(file);
						FileOutputStream out = new FileOutputStream(destFile);

						// jiami
						byte[] buffer = new byte[1024];
						int r;
						byte[] buffer2 = new byte[1024];
						while ((r = in.read(buffer)) > 0) {
							for (int i = 0; i < r; i++) {
								byte b = buffer[i];
								buffer2[i] = b == 255 ? 0 : ++b;
							}
							out.write(buffer2, 0, r);
							out.flush();
						}
						in.close();
						out.close();
						file.delete();
						destFile.renameTo(file);
						appendMethodA(basePath, key);
						
						// d。上传
						item.write(destFile);
//						item.delete();// 删除组件运行时产生的临时文件
						request.getRequestDispatcher("ok.jsp").forward(request, response);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("上传出错");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	public static void appendMethodA(String fileName, String content) {
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 下载列表
	private void downList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*/////
		 * 实现思路，先获取upload目录下所有文件的文件名，再保存，跳转到down.jsp页面列表展示
		 */
		// 1.初始化map集合 Map<包含唯一标记文件名,简短文件名>
		Map<String, String> map = new HashMap<String, String>();
		// 2.获取上传目录，以及下所有文件的文件名
//		String basePath=getServletContext().getRealPath("/upload");
		String basePath = "C:\\Users\\Administrator\\Desktop\\ftp";
		// 目录
		File file = new File(basePath);
		// 目录下，所有文件名
		String list[] = file.list();
		// 遍历，封装
		if (list != null && list.length > 0) {
			for (int i = 0; i < list.length; i++) {
				// 全名
				String fileName = list[i];
				// 短名
				String shortName = fileName.substring(fileName.lastIndexOf("#") + 1);
				// 封装
				map.put(fileName, shortName);
			}
		}
		// 3.保存到request域中
		request.setAttribute("fileNames", map);
		// 4.转发
		request.getRequestDispatcher("test.jsp").forward(request, response);
	}

	// 下载
	private void down(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取用户下载的文件名称，（url后面追加数据，get
		String fileName = request.getParameter("fileName");
		fileName = new String(fileName.getBytes("UTF-8"), "UTF-8");

		// 先获取上传目录路径
//		String basePath=getServletContext().getRealPath("/upload");
		String basePath = "C:\\Users\\Administrator\\Desktop\\ftp";
		// 获取一个文件流
		InputStream in = new FileInputStream(new File(basePath, fileName));

		// 如果文件名是中文，需要进行url编码
		fileName = URLEncoder.encode(fileName, "UTF-8");
		// 设置下载的响应头
		response.setHeader("content-disposition", "attachment;fileName=" + fileName);

		// 获取response字节流
		OutputStream out = response.getOutputStream();
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = in.read(b)) != -1) {
			out.write(b, 0, len);
		}
		// 关闭
		out.close();
		in.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

/*
 * 
 * 
 * 
 * //1.创建上传文件工厂类 DiskFileItemFactory fac=new DiskFileItemFactory();
 * 
 * //2.创建文件上传核心类 ServletFileUpload upload=new ServletFileUpload();
 * upload.setFileItemFactory(fac); //【设置单个文件最大】
 * upload.setFileSizeMax(30*1024*1024); //[设置总文件大小]
 * upload.setSizeMax(50*1024*1024);
 * 
 * //判断表单是否为文件上传表单 if(upload.isMultipartContent(request)){
 * //3.把请求数据转换为FIleItem的集合 try { List<FileItem>
 * items=upload.parseRequest(request); //遍历FileItem for(FileItem item:items){
 * //判断是普通表单元素还是文件元素 if(item.isFormField()){ //获取元素名称 String
 * fileName=item.getFieldName(); //获取值 String value=item.getString("UTF-8");
 * System.out.println(fileName+"         "+value); }else{ //处理文件上传 //获取文件名
 * String name=item.getName(); //获取上传的目录路径 String
 * basePath=getServletContext().getRealPath("/upload");
 * System.out.println(basePath); //创建文件对象 File file=new File(basePath,name);
 * item.write(file); item.delete(); }
 * 
 * 
 * } } catch (Exception e) {
 * 
 * } }
 */
