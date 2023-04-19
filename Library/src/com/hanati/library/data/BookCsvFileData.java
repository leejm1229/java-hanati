package com.hanati.library.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

import com.hanati.library.domain.model.Book;

public class BookCsvFileData {
	private static String userFileName = "Book.csv";
	private static File file = new File(userFileName);

	public static ArrayList<Book> loadBook() {
		ArrayList<Book> bookList = new ArrayList<>();

		try {
			// 파일 열고
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(userFileName), "EUC-KR"));

			// 첫 줄은 컬럼명 빼고
			String lines = br.readLine();

			// 데이터 읽기
			while ((lines = br.readLine()) != null) {
				String[] line = lines.split(",");
				int id = Integer.parseInt(line[0]);
				String title = line[1];
				LocalDate publishDate = LocalDate.parse(line[2]);
				boolean isReturned = Boolean.parseBoolean(line[3]);
				Book book = new Book(id, title, publishDate, isReturned);
				bookList.add(book);

			}
			br.close();

		} catch (IOException e) {
		}
		return bookList;
	}

}
