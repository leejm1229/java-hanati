package com.hanati.library.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hanati.library.data.MemberRepositorylmpl;
import com.hanati.library.domian.model.Member;
import com.hanati.library.domian.repository.MemberRepository;

public class MemberController {
	Scanner scanner = new Scanner(System.in);
	List<Member> memberList = new ArrayList<>();
	private MemberRepository memberRepository = new MemberRepositorylmpl();

	public MemberController() {
		while (true) {
			System.out.println("0.뒤로 \t 1.회원조회 \t 2.회원등록 \t 3.회원수정 \t 4.회원삭제 \t 5.회원복구");
			int num = scanner.nextInt();
			if (num == 1) {
				showInfoMember();
			} else if (num == 2) {
				createMember();
			} else if (num == 3) {
				updateMember();
			} else if (num == 4) {
				deleteMemeber();
			} else if (num == 5) {
				rollback();
			} else if (num == 0) {
				break;
			} else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}
	}

	public void showInfoMember() {
		memberRepository.readMember();
	}

	public void createMember() {
		System.out.println("이름을 입력하세요.");
		String name = scanner.next();

		scanner.nextLine();
		System.out.println("주소를 입력하세요.");
		String address = scanner.nextLine();

		System.out.println("전화번호를 입력하세요.('-' 없이 입력)");
		String phoneNumber = scanner.nextLine();

		System.out.println("생일을 입력하세요.(YYYYMMDD)");
		String birthday = scanner.next();

		Member member = new Member(name, address, phoneNumber, birthday);

		memberList.add(member);
		member.setMemberList(memberList);
		memberRepository.createMember(member);
	}

	public void updateMember() {
		System.out.println("수정하고싶은 회원 ID를 입력하세요.");
		int id = scanner.nextInt();
		memberRepository.updateMember(id);
	}

	public void deleteMemeber() {
		System.out.println("삭제하고싶은 회원 ID를 입력하세요.");
		int id = scanner.nextInt();
		memberRepository.deleteMember(id);
	}

	public void rollback() {
		memberRepository.rollbackMember();
		
	}

}
