package batch.main;

import batch.common.RarFileManager;

public class testRAR {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			// UnzipRAR
			RarFileManager rfm = new RarFileManager();
			rfm.unzipRar("example.rar");
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
