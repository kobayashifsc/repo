package batch.main;

import batch.common.FileProfileManager;
import batch.common.RarFileManager;

public class UnzipRAR {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// WriteFileProfile
			FileProfileManager fpm = new FileProfileManager();
			fpm.makeInitFileProfile(args[0]);
			// UnzipRAR
			RarFileManager rfm = new RarFileManager();
			rfm.unzipRar(args[0]);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
