package batch.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchiver {

	private final String ZIP_EXTENTION = ".zip";
	private static byte[] buf = new byte[1024];

	public void archiveDirectory(String path) throws Exception {
		System.out.println("ZipArchiver archiveDirectory start");
		//getZipFileName
		String fileName = getZipFileName(path);
		//createZipFile
		createZipFile(path, fileName);
		
	}
	private String getZipFileName(String path) {
		File file = new File(path);
		return file.getName() + ZIP_EXTENTION;
	}
	private void createZipFile(String path , String zipFileName) throws Exception {
		File zipFile = new File(zipFileName);
		File[] files = { new File(path) };
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
		try {
			encode(zos, files);
		} finally {
			zos.close();
		}
	}
	static void encode(ZipOutputStream zos, File[] files) throws Exception {
		for (File file : files) {
			if (file.isDirectory()) {
				encode(zos, file.listFiles());
			} else {
				ZipEntry entry = new ZipEntry(file.getPath().replace('\\', '/'));
				zos.putNextEntry(entry);
				try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
					for (;;) {
						int len = is.read(buf);
						if (len < 0) break;
						zos.write(buf, 0, len);
					}
				}
			}
		}
	}
}
