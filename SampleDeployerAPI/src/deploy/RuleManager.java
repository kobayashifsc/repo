package deploy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import data.DataController;

/**
 * 配信を管理するクラス
 * 
 * @version 1.0, 20 Aug, 2014
 * @author fsc
 */
public class RuleManager {

	private List<Map<String, List<String>>> dataList;

	/**
	 * デフォルトコンストラクタ
	 */
	public RuleManager() {
		// コンテンツリスト読み込み
		DataController dc = new DataController();
		dataList = dc.readJsonRule();
	}

	/**
	 * 属性リストにマッチするコンテンツＩDのリストを返却
	 * 
	 * @param attributeList
	 *            属性リスト
	 * @return コンテンツＩＤリスト
	 */
	public List<String> getContentsList(List<String> attributeList) {
		List<String> contentsIdList = new ArrayList<String>();

		// 属性リストを配列化
		String[] attributes = (String[]) attributeList.toArray(new String[0]);

		// マッチング処理
		for (Map<String, List<String>> rule : dataList) {
			// Jacksonの影響でマップを分解
			for (Map.Entry<String, List<String>> entry : rule.entrySet()) {

				boolean match = true;
				// ルールの持つ属性情報をユーザの属性情報が網羅しているかチェック

				for (String attribute : entry.getValue()) {
					if (Arrays.asList(attributes).contains(attribute)) {
						// マッチング継続
						continue;
					} else {
						match = false;
						break;
					}
				}
				// すべてマッチした配信ルールのコンテンツＩＤを配信対象に追加
				if (match) {
					contentsIdList.add(entry.getKey());
				}
			}
		}

		System.out.println("【マッチしたコンテンツIDリスト】");
		for (String contentsId : contentsIdList) {
				System.out.println(contentsId);
		}
		System.out.println("【マッチしたコンテンツIDリスト】");

		return contentsIdList;
	}

	/**
	 * コンテンツＩＤリストの中からランダムでひとつのコンテンツＩＤを取得
	 * 
	 * @param contentsIdList
	 *            コンテンツＩＤリスト
	 * @return コンテンツＩＤ
	 */
	public String getRandomContentsId(List<String> contentsIdList) {
		if(0 == contentsIdList.size()) {
			return null;
		}
		// Randomクラスの生成
		Random r = new Random();
		// 乱数の取得
		int i = r.nextInt(contentsIdList.size());
		
		System.out.println("【ランダムに選択したコンテンツID】");
		System.out.println(contentsIdList.get(i));
		System.out.println("【ランダムに選択したコンテンツID】");

		return contentsIdList.get(i);
	}
}
