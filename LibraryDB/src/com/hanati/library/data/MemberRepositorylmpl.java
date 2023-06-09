package com.hanati.library.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hanati.library.domian.model.Member;
import com.hanati.library.domian.repository.MemberRepository;

public class MemberRepositorylmpl implements MemberRepository {
	ArrayList<Member> memberList = new ArrayList<>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmtInsert = null;
	ResultSet rs = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.119.119:1521:dink";
	String user = "scott";
	String password = "tiger";
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Scanner scanner = new Scanner(System.in);

	@Override
	public List<Member> loadMember() {
		memberList.clear();
		try {
			String sql = "SELECT * FROM MEMBER ORDER BY id";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String signupDay = rs.getString("signupday");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phonenumber");
				String birthday = rs.getString("birthday");

				int age = LocalDate.now().getYear() - Integer.parseInt(birthday.substring(0, 4)) + 1;
				Member member = new Member(id, name, signupDay, address, phoneNumber, birthday, age);
				memberList.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return memberList;
	}

	@Override
	public void createMember(Member member) {
		save(member.getMemberList());

	}

	@Override
	public void readMember() {
		List<Member> members = new ArrayList<>();
		members = loadMember();
		for (Member member : members) {
			System.out.println("[id : " + member.getId() + "\t" + "이름 : " + member.getName() + "\t" + "가입날짜 : "
					+ member.getSignUpDay() + "\t" + "주소 : " + member.getAddress() + "\t" + "전화번호 : "
					+ member.getPhoneNumber().substring(0, 3) + "-" + member.getPhoneNumber().substring(3, 7) + "-"
					+ member.getPhoneNumber().substring(7) + "\t" + "생년월일 : " + member.getBirthday().substring(0, 4)
					+ "-" + member.getBirthday().substring(4, 6) + "-" + member.getBirthday().substring(6, 8) + "\t"
					+ "나이 : " + member.getAge() + "]");
		}
	}

	@Override
	public void updateMember(int id) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			String sql = "UPDATE MEMBER SET name=?, address=?, phonenumber=?, birthday=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);

			System.out.println("이름을 입력하세요.");
			String name = scanner.next();

			scanner.nextLine();
			System.out.println("주소를 입력하세요.");
			String address = scanner.nextLine();

			System.out.println("전화번호를 입력하세요.('-' 없이 입력)");
			String phoneNumber = scanner.nextLine();

			System.out.println("생일을 입력하세요.(YYYYMMDD)");
			String birthday = scanner.next();

			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, phoneNumber);
			pstmt.setString(4, birthday);
			pstmt.setInt(5, id);
			pstmt.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void deleteMember(int id) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);

			// MEMBERTRASH에는 무조건 마지막으로 삭제한 회원의 정보만을 담기 위해서 이미 한 사람이 있다면 데이터를 지워준다.
			String delTrashSql = "DELETE FROM MEMBERTRASH";
			pstmt = conn.prepareStatement(delTrashSql);
			pstmt.executeUpdate();

			// 삭제되는 회원 정보를 MEMBERTRASH 테이블에 추가합니다.
			String insertTrashSql = "INSERT INTO MEMBERTRASH (id, name, signupday, address, phonenumber, birthday) "
					+ "SELECT id, name, signupday, address, phonenumber, birthday FROM MEMBER WHERE id = ?";
			pstmt = conn.prepareStatement(insertTrashSql);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();

			// 해당하는 회원 정보를 MEMBER 테이블에서 삭제합니다.
			String delSql = "DELETE FROM MEMBER WHERE id = ?";
			pstmt = conn.prepareStatement(delSql);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void save(List<Member> memberList) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);

			for (Member member : memberList) {
				// 중복되는 PhoneNumber가 있는지 확인
				String checkSql = "SELECT * FROM member WHERE phonenumber = ?";
				pstmt = conn.prepareStatement(checkSql);
				pstmt.setString(1, member.getPhoneNumber());
				rs = pstmt.executeQuery();
				if (rs.next()) { // 중복되는 PhoneNumber가 있으면 continue
					continue;
				}

				// 중복되는 PhoneNumber가 없으면 새로운 데이터로 저장
				String sql = "INSERT INTO MEMBER(id, name, address, phonenumber, birthday) VALUES (MEMBER_SEQ.nextval, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getName());
				pstmt.setString(2, member.getAddress());
				pstmt.setString(3, member.getPhoneNumber());
				pstmt.setString(4, member.getBirthday());
				pstmt.executeUpdate();
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback(); // 에러 발생 시 rollback
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void rollbackMember() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);

			// MEMBERTRASH 테이블에서 회원 정보를 가져옵니다.
			String selectSql = "SELECT id, name, signupday, address, phonenumber, birthday FROM MEMBERTRASH";
			pstmt = conn.prepareStatement(selectSql);
			rs = pstmt.executeQuery();

			// MEMBER 테이블에 회원 정보를 추가합니다.
			String insertSql = "INSERT INTO MEMBER (id, name, signupday, address, phonenumber, birthday) VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(insertSql);
			if (rs.next()) {
				pstmt.setLong(1, rs.getLong("id"));
				pstmt.setString(2, rs.getString("name"));
				pstmt.setString(3, rs.getString("signupday"));
				pstmt.setString(4, rs.getString("address"));
				pstmt.setString(5, rs.getString("phonenumber"));
				pstmt.setString(6, rs.getString("birthday"));
				pstmt.executeUpdate();
			}
			String delSql = "DELETE FROM MEMBERTRASH";
			pstmt = conn.prepareStatement(delSql);
			rs = pstmt.executeQuery();
			System.out.println("복구완료를 성공적으로 하였습닌다.");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
