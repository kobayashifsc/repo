package batch.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CopyOfFileProfile {
	private final String CSV_FILENAME = "profile.csv";
	/* 取得時ファイル名 */
	private String defaultFileName;
	/* 取得日時 */
	private String addDate;
	/* 再編後ファイル名 */
	private String remakeFileName;

	public CopyOfFileProfile(String defaultFileName, String addDate) {
		this.defaultFileName = defaultFileName;
		this.addDate = addDate;
	}

	public static String getLastModifiedString(long lastModified) {
		String lastModifiedString = "";

		/** 時刻フォーマット変換 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPAN);
		lastModifiedString = sdf.format(new Long(lastModified));

		return lastModifiedString;
	}

	public void writeCsvFile(String path) {
		try {
			File file = new File(path + "/" + CSV_FILENAME);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			pw.println(this.defaultFileName + "," + this.addDate);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public String getDefaultFileName() {
		return defaultFileName;
	}
	public void setDefaultFileName(String defaultFileName) {
		this.defaultFileName = defaultFileName;
	}
	public String getRemakeFileName() {
		return remakeFileName;
	}
	public void setRemakeFileName(String remakeFileName) {
		this.remakeFileName = remakeFileName;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

}
