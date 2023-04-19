package com.quiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookLanConvert {

	public ArrayList<BookLoan> showInfoBookLoan(String fileName) {
		ArrayList<BookLoan> bookLoanList = new ArrayList<>();

		try {
			// 파일 열고
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));

			// 첫 줄은 컬럼명 빼고
			String lines = br.readLine();

			// 데이터 읽기
			while ((lines = br.readLine()) != null) {
				String[] line = lines.split(",");
				int bookId = Integer.parseInt(line[0]);
				int memberId = Integer.parseInt(line[1]);
				boolean extensionStatus = Boolean.parseBoolean(line[2]);
				LocalDate loanDate = LocalDate.parse(line[3]);
				LocalDate deadline = LocalDate.parse(line[4]);
				BookLoan bookLoans = new BookLoan(bookId, memberId, extensionStatus, loanDate, deadline);
				bookLoanList.add(bookLoans);
			}

			br.close();
		} catch (IOException e) {
			System.out.println("등록된 도서 대출정보가 없습니다.");
		}

		return bookLoanList;

	}

	public void bookLoanRegister(BookLoan bookLoan, int bookId, int memberId, String fileName) {
		if (checkBook(bookId) && checkMember(memberId)) {
			try {
				// 파일 생성
				BufferedWriter bw = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(fileName, true), "EUC-KR"));

				// 파일이 존재하지 않는 경우에만 컬럼명 작성
				if (new File(fileName).length() == 0) {
					bw.write("bookId,memberId,extensionStatus,loanDate,deadline");
					bw.newLine();
				}

				// 데이터 추가
				bw.write(bookLoan.getBookId() + "," + bookLoan.getMemberId() + "," + bookLoan.getExtensionStatus() + ","
						+ bookLoan.getLoanDate() + "," + bookLoan.getDeadline());
				bw.newLine();

				// book.csv 파일 열어서 isReturned 값 수정(GPT)
				List<String> lines = Files.readAllLines(Paths.get("book.csv"), Charset.forName("EUC-KR"));
				for (int i = 1; i < lines.size(); i++) {
					String[] parts = lines.get(i).split(",");
					int id = Integer.parseInt(parts[0]);
					boolean isReturned = Boolean.parseBoolean(parts[3]);
					if (id == bookId && isReturned) {
						parts[3] = "false";
						lines.set(i, String.join(",", parts));
						break;
					}
				}
				Files.write(Paths.get("book.csv"), lines, Charset.forName("EUC-KR"));

				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("대출을 할 수 없습니다.");
		}
	}

	public void bookExtension(int bookId, String fileName) {
		if (checkExtension(bookId)) {
			try {
				// 파일 읽기
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));

				// 새로운 파일 쓰기
				BufferedWriter bw = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream("temp.csv"), "EUC-KR"));
				String lines;
				int lineNum = 0; // 컬럼명 추가를 위해
				while ((lines = br.readLine()) != null) {
					if (lineNum == 0) {
						bw.write(lines); // 기존 컬럼명 추가
						bw.newLine();
					} else {
						String[] line = lines.split(",");
						int bookid = Integer.parseInt(line[0]);
						int memberid = Integer.parseInt(line[0]);
						boolean extensionStatus = Boolean.parseBoolean(line[2]);
						LocalDate loanDate = LocalDate.parse(line[3]);
						LocalDate deadline = LocalDate.parse(line[4]);

						if (bookid == bookId && extensionStatus == false) {
							extensionStatus = true;
							deadline = deadline.plusDays(7);
						}

						bw.write(String.format("%d,%d,%b,%s,%s%n", bookid, memberid, extensionStatus, loanDate,
								deadline));

					}
					lineNum++;
				}
				br.close();
				bw.close();
				File file = new File(fileName);
				file.delete();
				File tempFile = new File("temp.csv");
				tempFile.renameTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("대출연장이 불가능합니다.");
		}
	}
	
	public void bookReturn(int bookId) {
		if(checkBookLoan(bookId)) {
			try {
		        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("bookLoan.csv"), "EUC-KR"));
		        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("temp.csv"), "EUC-KR"));

		        String lines;
		        int lineNum = 0; // 컬럼명 추가를 위해
		        while ((lines = br.readLine()) != null) {
		            if (lineNum == 0) { // 첫 번째 라인인 경우
		            	bw.write(lines); // 기존 컬럼명 추가
		            	bw.newLine();
		            } else {
		                String[] fields = lines.split(",");
		                int id = Integer.parseInt(fields[0]);

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
		        File file = new File("bookLoan.csv");
		        file.delete();
		        File tempFile = new File("temp.csv");
		        tempFile.renameTo(file);
		     // book.csv 파일 열어서 isReturned 값 수정(GPT)
				List<String> line = Files.readAllLines(Paths.get("book.csv"), Charset.forName("EUC-KR"));
				for (int i = 1; i < line.size(); i++) {
					String[] parts = line.get(i).split(",");
					int id = Integer.parseInt(parts[0]);
					boolean isReturned = Boolean.parseBoolean(parts[3]);
					if (id == bookId && isReturned==false) {
						parts[3] = "true";
						line.set(i, String.join(",", parts));
						break;
					}
				}
				Files.write(Paths.get("book.csv"), line, Charset.forName("EUC-KR"));

		    } catch (FileNotFoundException e) {
		    	System.out.println("파일이 없습니다.");
		    } catch (IOException e) {
		    	e.getStackTrace();
		    }
		}else {
			System.out.println("대출 정보가 없습니다.");
		}
	}

	public boolean checkBookLoan(int bookId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("bookLoan.csv"));
			String lines = reader.readLine(); // 첫 줄을 건너뜀
			while ((lines = reader.readLine()) != null) {
				String[] line = lines.split(",");
				int id = Integer.parseInt(line[0]);
				if (id == bookId) {
					reader.close();
					return true;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkExtension(int bookId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("bookLoan.csv"));
			String lines = reader.readLine(); // 첫 줄을 건너뜀
			while ((lines = reader.readLine()) != null) {
				String[] line = lines.split(",");
				boolean extensionStatus = Boolean.parseBoolean(line[2]);
				if (extensionStatus == false) {
					reader.close();
					return true;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkBook(int bookId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("book.csv"));
			String lines = reader.readLine(); // 첫 줄을 건너뜀
			while ((lines = reader.readLine()) != null) {
				String[] line = lines.split(",");
				int id = Integer.parseInt(line[0]);
				boolean isReturned = Boolean.parseBoolean(line[3]);
				if (id == bookId && isReturned) {
					reader.close();
					return true;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkMember(int memberId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("member.csv"));
			String lines = reader.readLine(); // 첫 줄을 건너뜀
			while ((lines = reader.readLine()) != null) {
				String[] line = lines.split(",");
				int id = Integer.parseInt(line[0]);
				if (id == memberId) {
					reader.close();
					return true;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

}
