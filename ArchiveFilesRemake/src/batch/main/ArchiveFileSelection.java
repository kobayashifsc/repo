package batch.main;

import java.io.File;

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
        	if (isArchiveFile( files[i].getName())){
            	// ファイル名取得
        		// System.out.println("ファイル" + (i + 1) + "→" + filename);
        		// 移動先のディレクトリ作成
        		String subdir ="";
        		// TODO
        		File outdir = new File(outpath + subdir);
        		// Profile作成
        		// TODO
        		// ファイル移動
        		// TODO
        	}
        }
	}

	// アーカイブファイル判定
	private boolean isArchiveFile(String filename) {
		final String[] IMAGE_EXTENSION_ARRAY = {".rar"};
		// ファイルの末尾がアーカイブ拡張子リストの中にあるかチェック
		for (int i = 0;i < IMAGE_EXTENSION_ARRAY.length;i++ ) {
			if (filename.toLowerCase().endsWith(IMAGE_EXTENSION_ARRAY[i])) {
				return true;
			}
		}
		return false;
	}
}
