package com.fms.email.constants;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FMSUtils {

	public static final String EVENTS_MAIL = "1";
	public static final String EVENTSDETAILS_MAIL = "2";
	public static final String REPORTS_MAIL = "3";

	/*
	 * public static String fileNameWithOutExt(String fileName) { return
	 * FilenameUtils.removeExtension(fileName); }
	 */

	public static String uploadedFileNamewithDateTimeStr(File file) throws Exception {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyyhhmmss");
		String strDate = formatter.format(date);
		System.out.println("Date Format with MM/dd/yyyy : " + strDate);

		try {
			// renaming the file and moving it to a new location
			if (file.renameTo(new File(FMSConstants.UPLOADED_FILE_LOC + strDate + ".xlsx"))) {

				// if file copied successfully then delete the original file
				file.delete();
				System.out.println("File moved successfully");
			} else {
				System.out
						.println("Failed to move the file " + file.getAbsolutePath() + " /n name --> " + file.getName());
			}

		} catch (Exception e) {
			throw new Exception("File Uploaded already - No file availble in particular location"+e); 
		}

		return strDate;
	}
}
