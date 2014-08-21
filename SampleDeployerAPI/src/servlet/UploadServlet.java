package servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItemStream;

import data.DataController;
import servlet.bean.IndexBean;

public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5265955116764893786L;

	/**
	 * 
	 */

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		try {
			// 洗い替え対象を判定
			String type = "3";//req.getParameter("type");
			String fileName = null;
			DataController dc = new DataController();
			switch (type) {
				case "1" :
					fileName = dc.FILE_ATTRIBUTE;
					break;
				case "2" :
					fileName = dc.FILE_RULE;
					break;
				case "3" :
					fileName = dc.FILE_CONTENTS;
					break;
				default :
					fileName = dc.FILE_CONTENTS;
					//throw new Exception("A required item was not input");
			}
			String filePath = dc.getFilePath(fileName);

			// ファイルの洗い替え
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			factory.setSizeThreshold(1024);
			upload.setSizeMax(-1);
			upload.setHeaderEncoding("UTF-8");

			FileItemIterator iter = upload.getItemIterator(req);
			while (iter.hasNext()) {
				FileItemStream item = (FileItemStream) iter.next();
				if (item.isFormField()) {
					System.out.println(item.getName());
					continue;
				} else {
					InputStream is = item.openStream();
					int len = 0;
					byte[] buffer = new byte[1024];

					FileOutputStream fos = new FileOutputStream(filePath);
					try {
						while((len = is.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
					} finally {
						fos.close();
					}
				}
			}

			// 正常の場合は、再表示
			req.getRequestDispatcher("/InitServlet").forward(req, res);
		} catch (Exception e) {
			// エラーの場合は、エラー内容を画面表示
			IndexBean indexBean = new IndexBean();
			req.setAttribute("indexBean", indexBean);
			indexBean.setMessage(e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/index.jsp");
			rd.forward(req, res);
		}
	}
}
