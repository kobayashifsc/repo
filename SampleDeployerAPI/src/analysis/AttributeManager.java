package analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.DataController;

/**
 * �������Ǘ�����N���X
 * 
 * @version 1.0, 20 Aug, 2014
 * @author fsc
 */
public class AttributeManager {

	private Map<String, List<String>> dataList;

	/**
	 * �f�t�H���g�R���X�g���N�^
	 */
	public AttributeManager() {
		// �R���e���c���X�g�ǂݍ���
		DataController dc = new DataController();
		dataList = dc.readJsonAttribute();
	}

	/**
	 * HEMS�[��ID�Ƀ}�b�`���鑮�����X�g��ԋp
	 * 
	 * @param hemsId
	 *            HEMS�[��ID
	 * @return�������X�g
	 */
	public List<String> getAttributeList(String hemsId) {
		List<String> attributes = dataList.get(hemsId);
		if (null == attributes) {
			attributes = new ArrayList<String>();
		}
		System.out.println("�y�}�b�`�����������z");
		System.out.println(attributes.toString());
		System.out.println("�y�}�b�`�����������z");

		return attributes;
	}
}
