package batch.main;

import java.io.File;
import java.io.FilenameFilter;

import batch.common.Constant;

public class UnzipArchiveFile {

	public static void main(String[] args) {
		searchDir(Constant.TARGET_DIR);

	}

	/**
	 * 引数で渡されたディレクトリ以下を再帰的に検索します。
	 *
	 * @param path ディレクトリパス
	 * @return
	 */
	private static boolean searchDir(String path) {
		File file = new File(path);
		File[] listFiles = file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// 対象要素の絶対パスを取得
				String absolutePath = dir.getAbsolutePath()
										+ File.separator + name;
				if (new File(absolutePath).isFile()) {
					// rarファイルのみ対象
					if (name.endsWith(".rar")) {
						return true;
					} else {
						return false;
					}
				} else {
					// ディレクトリの場合、再び同一メソッドを呼出す。
					return searchDir(absolutePath);
				}
			}
		});

		for (File f : listFiles) {
			if (f.isFile()) {
				System.out.println(f.getAbsolutePath());
				// TODO 7z.exe x -y -oD:\data\ArchiveFile\comptiq_2014-06\ D:\data\ArchiveFile\comptiq_2014-06\comptiq_2014-06.rar
			}
		}
		return true;
	}

}
