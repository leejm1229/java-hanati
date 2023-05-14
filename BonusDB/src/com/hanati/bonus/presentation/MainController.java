package com.hanati.bonus.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

import com.hanati.bonus.data.BonusRepositorylmpl;
import com.hanati.bonus.data.CustomerRepositorylmpl;
import com.hanati.bonus.data.EmpRepositorylmpl;
import com.hanati.bonus.domain.model.Customer;
import com.hanati.bonus.domain.model.Emp;
import com.hanati.bonus.domain.repository.BonusRepository;
import com.hanati.bonus.domain.repository.CustomerRepository;
import com.hanati.bonus.domain.repository.EmpRepository;

public class MainController {
	Scanner scanner = new Scanner(System.in);
	private CustomerRepository customerRepository = new CustomerRepositorylmpl();
	private EmpRepository empRepository = new EmpRepositorylmpl();
	private BonusRepository bonusRepository = new BonusRepositorylmpl();
	List<Customer> customerList = new ArrayList<>();
	List<Emp> empList = new ArrayList<>();
	Customer customer;;
	
	// 1은 customer 데이터를 이용하여 각 사원이 몇 명의 고객을 관리하는지 확인 후 리스트에 저장
	// 2는 리스트를 이용하여 자바 로직을 이용하여 보너스 계산 후 리스트에 저장 
	// 3은 보너스를 계산한 리스트를 이용하여 BONUS 테이블에 저장
	public MainController() {
		while (true) {
			System.out.println("0.종료 \t 1.고객데이터 로드 \t 2.보너스 계산 \t 3.데이터 저장");
			int num = scanner.nextInt();
			if (num == 1) {
				loadCustomerInfo();
			} else if (num == 2) {
				calculatorBonus(customerList);
			} else if (num == 3) {
				saveBonus();
			} else if (num == 0) {
				saveBonus();
				break;
			} else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}
	}
	
	// customer 테이블의 내용을 조회 
	public void loadCustomerInfo() {
		long startTime = System.currentTimeMillis(); // 코드 실행 전 시간 측정

		customerList = customerRepository.loadCustomer();

		for (Customer customer : customerList) {
			System.out.println("직원번호 : " + customer.getMgr_empno() + "\t" + "고객관리(명) : " + customer.getCount());
		}

		long endTime = System.currentTimeMillis(); // 코드 실행 후 시간 측정
		double time = (endTime - startTime) / 1000.0;
		System.out.println("Time : " + time + "초");
	}
	
	// 보너스 계산 
	public void calculatorBonus(List<Customer> customerList) {
		empList = empRepository.calCulatorEmp(customerList);

		for (Emp emp : empList) {
			System.out.println("직원번호 : " + emp.getEmpno() + "\t" + "직원이름 : " + emp.getEname() + "\t" + "직업 : "
					+ emp.getJob() + "\t" + "고객관리(명) : " + emp.getCountCustomer() + "\t" + "급여 : " + emp.getSal() + "\t"
					+ "보너스 : " + emp.getComm());
		}
	}
	
	// 계산한 값을 보너스 테이블에 저장 
	public void saveBonus() {
		bonusRepository.saveBonus(empList);
	}
}
