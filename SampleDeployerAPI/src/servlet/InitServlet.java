package servlet;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import data.DataController;
import servlet.bean.IndexBean;

public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 275861993393091284L;
	private static final DataController dc = new DataController();

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		IndexBean indexBean = new IndexBean();

		req.setAttribute("indexBean", indexBean);

		try {
			// 属性情報
			Map<String, String> attributeMap = editAttributeMap();
			indexBean.setAttributeMap(attributeMap);

			// 変更ルール
			Map<String, String> ruleMap = editRuleMap();
			indexBean.setRuleMap(ruleMap);

			// コンテンツ
			Map<String, String> contentsMap = editContentsMap();
			indexBean.setContentsMap(contentsMap);

		} catch (Exception e) {
			indexBean.setMessage(e.getMessage());
		}
		RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/index.jsp");
		rd.forward(req, res);
	}

	private Map<String, String> editAttributeMap() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// データ取得
		Map<String, List<String>> datalist = dc.readJsonAttribute();
		// データ加工
		for (Map.Entry<String, List<String>> entry : datalist.entrySet()) {
			map.put(entry.getKey(), Arrays.toString(entry.getValue()
					.toArray(new String[0])));
		}
		return map;
	}

	private Map<String, String> editRuleMap() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// データ取得
		List<Map<String, List<String>>> dataList = dc.readJsonRule();
		// データ加工
		for (Map<String, List<String>> rule : dataList) {
			for (Map.Entry<String, List<String>> entry : rule.entrySet()) {
				map.put(entry.getKey(), Arrays.toString(entry.getValue()
						.toArray(new String[0])));
			}
		}
		return map;
	}

	private Map<String, String> editContentsMap() throws Exception {
		// データ取得
		Map<String, String> map = dc.readJsonContents();
		return map;
	}
}
