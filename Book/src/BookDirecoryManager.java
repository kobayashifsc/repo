import java.io.File;

public class BookDirecoryManager {

	public static String root = "H:/コミック";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BookDirecoryManager bdm = new BookDirecoryManager();
		bdm.outputDirectoryList(root);

	}

	private void outputDirectoryList(String root) {
		File dir = new File(root);
		out(dir);
	}

	private void out(File file) {
		if(file.isDirectory()){
			System.out.println(file.getAbsolutePath());
		}
		File[] files = file.listFiles();
		if(null == files) {
			return;
		}
		for (int i = 0; i < files.length; i++) {
			File dir = files[i];
			out(dir);
		}
	}
}