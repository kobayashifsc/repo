package batch.main;

import batch.common.FileProfile;
import batch.common.RarFileManager;

public class UnzipRAR {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// WriteProfileCSV
			FileProfile fp = new FileProfile();
			fp.writeCsvFile(args[0]);

			// UnzipRAR
			RarFileManager rfm = new RarFileManager();
			rfm.unzipRar(args[0]);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
