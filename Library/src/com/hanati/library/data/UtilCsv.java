package com.hanati.library.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UtilCsv {

	public static boolean isState(String fileName) {
		// 파일 유효성 검사
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			int row = 0;
			while ((line = reader.readLine()) != null) {
				row++;
			}
			if (row <= 1) {
				return false;
			}
		} catch (IOException e) {
			return false;
		}

		return true;
	}

}
