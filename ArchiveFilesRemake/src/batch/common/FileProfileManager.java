package batch.common;


import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Fileプロファイルを管理するクラス
 * 
 * @version 1.0, 19 Aug, 2014
 * @author fsc
 */
public class FileProfileManager {
	// 出力ファイル名
	public static final String FILENAME = "profile.json";
	private final ObjectMapper mapper = new ObjectMapper();

	/**
	 * 初期状態のFile情報をJon形式でファイル出力
	 */
	public void makeInitFileProfile(String path) throws Exception {
		File file = new File(path);
		// getProperty
		FileProfile bean = new FileProfile();
		bean.setDefaultFileName(file.getName());
		bean.setGetDate(bean.getGetDate(file.lastModified()));
		// Json形式の文字列をファイルに出力
		convertBean2JsonFile(bean);
	}

	/**
	 * Json形式でFileプロファイルを出力
	 */
	public void convertBean2JsonFile(FileProfile bean) throws Exception {
		// Json形式の文字列をファイルに出力
		mapper.writeValue(new File(FILENAME), bean);
	}
	/**
	 * Json形式のFileプロファイルをFileプロファイルBeanにマッピング
	 */
	public FileProfile convertJsonFile2Bean(String path) throws Exception {
		// Json形式のファイルをBeanにマッピング
		FileProfile bean = mapper.readValue(new File(path), FileProfile.class);
		return bean;
	}
}
