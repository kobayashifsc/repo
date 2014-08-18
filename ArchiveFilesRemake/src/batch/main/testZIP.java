package batch.main;

import batch.common.ZipArchiver;

public class testZIP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			// ZipArchiver
			ZipArchiver za  = new ZipArchiver();
			za.archiveDirectory("example");
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
