package batch.common;

import java.io.File;

public class DirectoryManager {

	public void delete(String path) throws Exception {
		File directory = new File(path);
		deleteDirectory(directory);
		directory.delete();
	}
	private void deleteDirectory(File directory) {
		File[] files = directory.listFiles();
		if( files == null ) {
			return;
		} else {
			for( File file : files ) {
				if( !file.exists() ) {
					 continue;
				} else if( file.isDirectory() ) {
					deleteDirectory(file);
				}
				file.delete();
			}
		}
	}
}
