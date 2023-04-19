package com.chapter08;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class Main {
	public static void main(String[] args) {
		String urlName = "https://alimipro.com/favicon.ico";
		String fileName = "icon.ico";

		try {
			URL url = new URL(urlName);
			InputStream is = url.openStream();
			FileOutputStream fos = new FileOutputStream(fileName);

			byte[] buffer = new byte[1024];
			int len;

			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}

			is.close();
			fos.close();

			System.out.println("다운로드 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
