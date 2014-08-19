package batch.common;

import java.io.File;
import java.io.FileOutputStream;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

/**
 * RARファイルを操作するクラス
 * 
 * @version 1.0, 19 Aug, 2014
 * @author fsc
 */
public class RarFileManager {

	private final String ROOT_DIR = "./";

	/**
	 * 指定のRARファイルを解凍する
	 * 
	 * @param path
	 *            対象RARファイルパス
	 * @return なし
	 */
	public void unzipRar(String path) throws Exception {
		Archive archive = null;
		archive = new Archive(new File(path));

		for (final FileHeader fh : archive.getFileHeaders()) {
			if (fh.isDirectory()) {
				new File(ROOT_DIR + fh.getFileNameString().trim()).mkdirs();
			} else {
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(new File(ROOT_DIR + fh.getFileNameString().trim()));
					archive.extractFile(fh, fos);
				} finally {
					if (fos != null) fos.close();
				}
			}
		}
	}
}
