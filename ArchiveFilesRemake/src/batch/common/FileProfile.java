package batch.common;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Fileプロファイルを管理するクラス
 * 
 * @version 1.0, 19 Aug, 2014
 * @author fsc
 */
public class FileProfile {

	/* 取得時ファイル名 */
	private String defaultFileName;
	/* 取得日時 */
	private String getDate;

	public String getGetDate(long lastModified) throws Exception {
		/** 時刻フォーマット変換 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPAN);
		return sdf.format(lastModified);
	}

	@Override
	public String toString() {
		return "FileProfile{" + "defaultFileName=" + defaultFileName + ", getDate=" + getDate + '}';
	}

	public String getDefaultFileName() {
		return defaultFileName;
	}

	public void setDefaultFileName(String defaultFileName) {
		this.defaultFileName = defaultFileName;
	}

	public String getGetDate() {
		return getDate;
	}

	public void setGetDate(String getDate) {
		this.getDate = getDate;
	}
}
