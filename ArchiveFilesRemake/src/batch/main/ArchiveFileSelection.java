package batch.main;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import batch.common.FileProfile;
import batch.common.constant;

public class ArchiveFileSelection {

	public static void main(String[] args) {
		ArchiveFileSelection afs = new ArchiveFileSelection();
		afs.selectionFiles(constant.SERCH_DIR, constant.TARGET_DIR);
	}

	private void selectionFiles(String inpath, String outpath) {

		// ディレクトリを読み込む
		File file = new File(inpath);
		File files[] = file.listFiles();

		// 対象ファイルが存在する間繰り返す
		for (int i = 0; i < files.length; i++) {
			if (isArchiveFile(files[i].getName())) {
				// System.out.println("ファイル" + (i + 1) + "→" +
				// files[i].getName());

				// 移動先のディレクトリ作成
				String subdir = removeFileExtension(files[i].getName());
				File outdir = new File(outpath + subdir);
				//System.out.println("ファイル" + (i + 1) + "→" + outdir.getPath());
				outdir.mkdir();

				// Profile作成
				String lastModified = FileProfile
						.getLastModifiedString(files[i].lastModified());
				FileProfile fp = new FileProfile(files[i].getName(),
						lastModified);
				fp.writeCsvFile(outdir.getPath());
				// ファイルコピー TODO
				try {
					System.out.println(files[i].toPath().toString());
					System.out.println(outdir.toPath().toString());
					Files.copy(files[i].toPath(), outdir.toPath(),
							StandardCopyOption.COPY_ATTRIBUTES);

				} catch (Exception e) {
					e.printStackTrace();
				}
				// コピー元削除
				// 保留
			}
		}
	}

	// アーカイブファイル判定
	private boolean isArchiveFile(String filename) {
		final String[] IMAGE_EXTENSION_ARRAY = { ".rar" };
		// ファイルの末尾がアーカイブ拡張子リストの中にあるかチェック
		for (int i = 0; i < IMAGE_EXTENSION_ARRAY.length; i++) {
			if (filename.toLowerCase().endsWith(IMAGE_EXTENSION_ARRAY[i])) {
				return true;
			}
		}
		return false;
	}

	// 拡張子削除
	public static String removeFileExtension(String filename) {
		int lastDotPos = filename.lastIndexOf('.');

		if (lastDotPos == -1) {
			return filename;
		} else if (lastDotPos == 0) {
			return filename;
		} else {
			return filename.substring(0, lastDotPos);
		}
	}
}
