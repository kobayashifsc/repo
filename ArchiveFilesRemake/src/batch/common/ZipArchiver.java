package batch.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * ZIPファイルに圧縮するクラス
 * 
 * @version 1.0, 19 Aug, 2014
 * @author fsc
 */
public class ZipArchiver {

	private final String ZIP_EXTENTION = ".zip";
	private static byte[] buf = new byte[1024];

	/**
	 * ディレクトリを圧縮しZipファイルにする
	 * 
	 * @param path
	 *            圧縮対象ディレクトリパス
	 * @return なし
	 */
	public void archiveDirectory(String path) throws Exception {
		// getZipFileName
		String fileName = getZipFileName(path);
		// createZipFile
		createZipFile(path, fileName);
	}

	/**
	 * ディレクトリ名から圧縮ファイル名を生成
	 * 
	 * @param path
	 *            圧縮対象ディレクトリパス
	 * @return 圧縮ファイル名（.zip)
	 */
	private String getZipFileName(String path) {
		File file = new File(path);
		return file.getName() + ZIP_EXTENTION;
	}

	/**
	 * 圧縮ファイルを作成
	 * 
	 * @param path
	 *            圧縮対象ディレクトリパス
	 * @param zipFileName
	 *            圧縮ファイル名（.zip)
	 * @return なし
	 * @throws Exception
	 *             処理に問題があった場合に起こり得る例外
	 */
	private void createZipFile(String path, String zipFileName)
			throws Exception {
		File zipFile = new File(zipFileName);
		File[] files = { new File(path) };
		ZipOutputStream zos = new ZipOutputStream(
				new FileOutputStream(zipFile), Charset.forName("MS932"));
		try {
			encode(zos, path + "\\", files);
		} finally {
			zos.close();
		}
	}

	/**
	 * 対象ファイルを圧縮ファイルに登録 対象がディレクトリの場合は再帰的に処理を繰り返す
	 * 
	 * @param zos
	 *            圧縮ファイルストリーム
	 * @param currentPath
	 *            ファイルを格納する際に、格納先のパスから除くパス
	 * @param files
	 *            ファイルリスト
	 * @return なし
	 * @throws Exception
	 *             処理に問題があった場合に起こり得る例外
	 */
	private void encode(ZipOutputStream zos, String currentPath, File[] files)
			throws Exception {
		for (File file : files) {
			if (file.isDirectory()) {
				encode(zos, currentPath, file.listFiles());
			} else {
				String path = file.getPath().replace(currentPath, "");
				path = path.replace('\\', '/');
				System.out.println("entry=" + path);
				ZipEntry entry = new ZipEntry(path);
				zos.putNextEntry(entry);
				try (InputStream is = new BufferedInputStream(
						new FileInputStream(file))) {
					for (;;) {
						int len = is.read(buf);
						if (len < 0) {
							break;
						}
						zos.write(buf, 0, len);
					}
					zos.closeEntry();
				}
			}
		}
	}
}
