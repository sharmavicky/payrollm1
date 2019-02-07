package com.cts.payroll.bean;

import java.io.FileWriter;
import java.io.IOException;

public class CreateLog {
	public static void logError(String message) throws PayrollAppException {
		FileWriter fw = null;
		String filePath = "D:\\workspace\\error.log";
		try {
			fw = new FileWriter(filePath);
			fw.append(message);

		} catch (IOException e) {
			e.printStackTrace();
			throw new PayrollAppException(
					"Error when writing the file '" + filePath + "'." + "\nException Message: " + e.getMessage());
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}