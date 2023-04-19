package com.chapter06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class Main {
	public static void main(String[] args) {
		String originalFile = "originalFile.txt";
		String copyFile = "copyFile.txt";
		String zipFile = "zipFile.gz";

		copy(originalFile, copyFile);
		zip(originalFile, zipFile);
	}

	public static void copy(String originalFile, String copyFile) {
		try (FileInputStream fis = new FileInputStream(originalFile);
				BufferedInputStream bis = new BufferedInputStream(fis);

				FileOutputStream fos = new FileOutputStream(copyFile);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {

			byte[] buffer = new byte[1024];
			int len;
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			System.out.println("복사완료");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void zip(String originalFile, String zipFile) {
		try (FileInputStream fis = new FileInputStream(originalFile);
				BufferedInputStream bis = new BufferedInputStream(fis);

				GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream(zipFile));
				BufferedOutputStream bos = new BufferedOutputStream(gos)) {
			
			byte[] buffer = new byte[1024];
			int len;
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			System.out.println("압축 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}