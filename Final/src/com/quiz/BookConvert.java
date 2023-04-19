package com.quiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookConvert {

	// 도서등록 후 csv파일 생성
	public void registerBook(Book book, String fileName) {
		try {
			// 파일 생성
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(fileName, true), "EUC-KR"));

			// 파일이 존재하지 않는 경우에만 컬럼명 작성
			if (new File(fileName).length() == 0) {
				bw.write("bookId,title,publishDate,isReturned");
				bw.newLine();
			}

			// 데이터 추가
			bw.write(book.getBookId() + "," + book.getTitle() + "," + book.getPublishDate() + ","
					+ book.getIsReturned());
			bw.newLine();

			bw.close();
		} catch (IOException e) {
			System.out.println("도서등록에 실패하였습니다.");
		}
	}

	public void deleteBook(int bookId, String fileName) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("temp.csv"), "EUC-KR"));

			String lines;
			int lineNum = 0; // 컬럼명 추가를 위해

			while ((lines = br.readLine()) != null) {
				if (lineNum == 0) { // 첫 번째 라인인 경우
					bw.write(lines); // 기존 컬럼명 추가
					bw.newLine();
				} else {
					String[] line = lines.split(",");
					int id = Integer.parseInt(line[0]);

					if (id == bookId) { // 일치하는 id면 이 부분 빼고
						continue;
					}

					bw.write(lines);
					bw.newLine();
				}
				lineNum++;
			}

			br.close();
			bw.close();

			File file = new File(fileName);
			file.delete();

			File tempFile = new File("temp.csv");
			tempFile.renameTo(file);

		} catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		} catch (IOException e) {
			e.getStackTrace();
		}

	}

	public ArrayList<Book> showInfoBook(String fileName) {
		ArrayList<Book> bookList = new ArrayList<>();
		
		try {
			// 파일 열고
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));

			// 첫 줄은 컬럼명 빼고
			String lines = br.readLine();

			// 데이터 읽기
			while ((lines = br.readLine()) != null) {
				String[] line = lines.split(",");
				int bookId = Integer.parseInt(line[0]);
				String title = line[1];
				LocalDate publishDate = LocalDate.parse(line[2]);
				boolean isReturned = Boolean.parseBoolean(line[3]);
				Book book = new Book(bookId, title, publishDate, isReturned);
				bookList.add(book);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("등록된 도서가 없습니다.");
		}
		return bookList;
	}
}