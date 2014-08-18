package batch.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FileProfile {
	// 出力ファイル名
	private final String FILENAME = "profile.csv";
	// 区切り文字
	private final String SEPARATOR = ",";

	/* 取得時ファイル名 */
	private String defaultFileName;
	/* 取得日時 */
	private String getDate;

	public FileProfile() {
	}

	public void writeCsvFile(String path) throws Exception {
		File file = new File(path);
		// getProperty
		defaultFileName = file.getName();
		getDate = getGetDate(file.lastModified());
		// writeCSV
		write();
	}
	private String getGetDate(long lastModified) throws Exception {
		/** 時刻フォーマット変換 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPAN);
		return sdf.format(lastModified);
	}
	private void write() throws Exception {
		File file = new File(FILENAME);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		StringBuffer sb = new StringBuffer();
		sb.append(defaultFileName).append(SEPARATOR);
		sb.append(getDate);
		pw.print(sb.toString());
		pw.close();
	}
}
