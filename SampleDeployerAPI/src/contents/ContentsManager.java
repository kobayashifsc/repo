package contents;

import java.util.Map;

import data.DataController;

/**
 * �R���e���c���Ǘ�����N���X
 * 
 * @version 1.0, 20 Aug, 2014
 * @author fsc
 */
public class ContentsManager {

//	private final String DEFAULT_URL = "http://www.google.co.jp";
	private Map<String, String> dataList;

	/**
	 * �f�t�H���g�R���X�g���N�^
	 */
	public ContentsManager() {
		// �R���e���c���X�g�ǂݍ���
		DataController dc = new DataController();
		dataList = dc.readJsonContents();
	}

	/**
	 * �R���e���cID�Ƀ}�b�`����URL��ԋp
	 * 
	 * @param contentId
	 *            �R���e���c�h�c
	 * @return URL
	 */
	public String getContent(String contentsId) {
		// �R���e���c�擾
		String url = dataList.get(contentsId);
		//if (null == url) {
		//	url = DEFAULT_URL;
		//}

		System.out.println("�y�I������URL�z");
    	System.out.println(url);
    	System.out.println("�y�I������URL�z");

    	return url;
	}
}
