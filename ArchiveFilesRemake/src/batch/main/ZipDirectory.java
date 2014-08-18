package batch.main;

import batch.common.DirectoryManager;
import batch.common.ZipArchiver;

public class ZipDirectory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// ZipArchiver
			ZipArchiver za  = new ZipArchiver();
			za.archiveDirectory(args[0]);
			// DeleteDirectory
			DirectoryManager dm = new DirectoryManager();
			dm.delete(args[0]);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
