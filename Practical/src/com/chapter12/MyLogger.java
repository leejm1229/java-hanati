package com.chapter12;

import java.io.FileWriter;
import java.io.IOException;

public class MyLogger {

	private static MyLogger instance;
	private static FileWriter fw;

	private MyLogger() throws IOException {
		
	}

	public static MyLogger getInstance() throws IOException {
		if (instance == null) {
			instance = new MyLogger();
			fw = new FileWriter("dummylog.txt");
		}
		
		return instance;
	}

	public void log(String log) throws IOException {
		fw.write(log);
		fw.write("\n");
		fw.flush();
	}
	
	public static void main(String[] args) throws IOException {
		MyLogger logger1 = MyLogger.getInstance();
		logger1.log("first");
		
		MyLogger logger2 = MyLogger.getInstance();
		logger2.log("second");
	}
}
