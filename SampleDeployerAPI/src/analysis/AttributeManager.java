package analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.DataController;

/**
 * 属性を管理するクラス
 * 
 * @version 1.0, 20 Aug, 2014
 * @author fsc
 */
public class AttributeManager {

	private Map<String, List<String>> dataList;

	/**
	 * デフォルトコンストラクタ
	 */
	public AttributeManager() {
		// コンテンツリスト読み込み
		DataController dc = new DataController();
		dataList = dc.readJsonAttribute();
	}

	/**
	 * HEMS端末IDにマッチする属性リストを返却
	 * 
	 * @param hemsId
	 *            HEMS端末ID
	 * @return属性リスト
	 */
	public List<String> getAttributeList(String hemsId) {
		List<String> attributes = dataList.get(hemsId);
		if (null == attributes) {
			attributes = new ArrayList<String>();
		}
		System.out.println("【マッチした属性情報】");
		System.out.println(attributes.toString());
		System.out.println("【マッチした属性情報】");

		return attributes;
	}
}
