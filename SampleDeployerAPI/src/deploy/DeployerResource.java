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
			// �����`�F�b�N
			System.out.println("�y�n���ꂽ�����z");
			System.out.println("hemsId" + hemsId);
			System.out.println("category:" + category);
			System.out.println("�y�n���ꂽ�����z");
			if (null == hemsId || "".equals(hemsId) || null == category
					|| "".equals(category)) {
				throw new AppException("998");
			}

			// hemsId�ɕR�����������擾
			AttributeManager am = new AttributeManager();
			List<String> attributeList = am.getAttributeList(hemsId);
			if (null == attributeList || 0 == attributeList.size()) {
				throw new AppException("001");
			}
			// �J�e�S���[�𑮐��ɒǉ�
			attributeList.add(category);

			// �������Ƀ}�b�`����R���e���cID���X�g���擾
			RuleManager rm = new RuleManager();
			List<String> contentsIdList = rm.getContentsList(attributeList);
			// �R���e���cID���X�g���烉���_����1�̃R���e���cID��I��
			String contentsId = rm.getRandomContentsId(contentsIdList);
			if (null == contentsId) {
				throw new AppException("002");
			}

			// �R���e���cID�ɕR��URL���擾
			ContentsManager cm = new ContentsManager();
			String url = cm.getContent(contentsId);
			if (null == url) {
				throw new AppException("003");
			}

			// ����I��
			res.put("result", "success");
			res.put("url", url);
		} catch (AppException ae) {
			// �ُ�I��
			Map<String, String> error = new LinkedHashMap<String, String>();
			error.put("code", ae.getCode());
			error.put("message", ae.getMessage());
			res.put("result", "fail");
			res.put("error", error);
		}

		// �ԋp
		try {
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
