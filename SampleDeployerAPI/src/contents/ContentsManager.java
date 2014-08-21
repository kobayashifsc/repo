package contents;

import java.util.Map;

import data.DataController;

/**
 * コンテンツを管理するクラス
 * 
 * @version 1.0, 20 Aug, 2014
 * @author fsc
 */
public class ContentsManager {

//	private final String DEFAULT_URL = "http://www.google.co.jp";
	private Map<String, String> dataList;

	/**
	 * デフォルトコンストラクタ
	 */
	public ContentsManager() {
		// コンテンツリスト読み込み
		DataController dc = new DataController();
		dataList = dc.readJsonContents();
	}

	/**
	 * コンテンツIDにマッチするURLを返却
	 * 
	 * @param contentId
	 *            コンテンツＩＤ
	 * @return URL
	 */
	public String getContent(String contentsId) {
		// コンテンツ取得
		String url = dataList.get(contentsId);
		//if (null == url) {
		//	url = DEFAULT_URL;
		//}

		System.out.println("【選択したURL】");
    	System.out.println(url);
    	System.out.println("【選択したURL】");

    	return url;
	}
}
