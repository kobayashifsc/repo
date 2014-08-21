package deploy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.AppException;
import analysis.AttributeManager;
import contents.ContentsManager;

@Path("/getPersonalizeContents.json")
public class DeployerResource {
	// @Path("/getDeployerInfo/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPersonalizeContents(@QueryParam("hemsId") String hemsId,
			@QueryParam("category") String category) {

		Map<String, Object> res = new LinkedHashMap<String, Object>();
		String json = "";
		try {
			// 引数チェック
			System.out.println("【渡された引数】");
			System.out.println("hemsId" + hemsId);
			System.out.println("category:" + category);
			System.out.println("【渡された引数】");
			if (null == hemsId || "".equals(hemsId) || null == category
					|| "".equals(category)) {
				throw new AppException("998");
			}

			// hemsIdに紐つく属性情報を取得
			AttributeManager am = new AttributeManager();
			List<String> attributeList = am.getAttributeList(hemsId);
			if (null == attributeList || 0 == attributeList.size()) {
				throw new AppException("001");
			}
			// カテゴリーを属性に追加
			attributeList.add(category);

			// 属性情報にマッチするコンテンツIDリストを取得
			RuleManager rm = new RuleManager();
			List<String> contentsIdList = rm.getContentsList(attributeList);
			// コンテンツIDリストからランダムに1つのコンテンツIDを選択
			String contentsId = rm.getRandomContentsId(contentsIdList);
			if (null == contentsId) {
				throw new AppException("002");
			}

			// コンテンツIDに紐つくURLを取得
			ContentsManager cm = new ContentsManager();
			String url = cm.getContent(contentsId);
			if (null == url) {
				throw new AppException("003");
			}

			// 正常終了
			res.put("result", "success");
			res.put("url", url);
		} catch (AppException ae) {
			// 異常終了
			Map<String, String> error = new LinkedHashMap<String, String>();
			error.put("code", ae.getCode());
			error.put("message", ae.getMessage());
			res.put("result", "fail");
			res.put("error", error);
		}

		// 返却
		try {
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
