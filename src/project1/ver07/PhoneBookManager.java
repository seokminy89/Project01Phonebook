package project1.ver07;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import project1.ver07.PhoneInfo;

import project1.ver07.SubMenuItem;


public class PhoneBookManager 
{
	//키보드 입력을 위한 스캐너 객체 생성인데
	//static으로 선언하였기 때문에 클래스 전체 영역에서 추후 객체 생성 없이 접근이 가능
	public static Scanner scanner = new Scanner(System.in);
	
	//주소록 저장을 위한 컬렉션 선언.
	HashSet<PhoneInfo> addrArray;
	
	//기본 생성자.
	public PhoneBookManager() {
		addrArray = new HashSet<PhoneInfo>();
	}
	
	//메뉴 출력용
	public static void printMenu(){
		System.out.println("메뉴를 선택하세요...");
		System.out.println("1.주소록 입력");
		System.out.println("2.검색");
		System.out.println("3.삭제");
		System.out.println("4.출력");
		System.out.println("5.프로그램 종료");
		System.out.print("메뉴선택:");
	}
	
	
//	입력 : 
	public void dataInput() {
		Scanner scanner = new Scanner(System.in);
		String name, phoneNumber, major, companyName;
		int grade;
		PhoneInfo pi = null;
		
		try {
			
			System.out.println("데이터 입력을 시작합니다.");
			System.out.println("1.일반, 2,동창, 3.회사");
			System.out.println("선택>>");
			int choice = scanner.nextInt();
			scanner.nextLine();
			//공통 사항 입력.
			System.out.print("이름:"); name = scanner.nextLine();
			System.out.print("전화번호:"); phoneNumber = scanner.nextLine();
			
			
			if(choice==SubMenuItem.BASIC) {
				
				pi = new PhoneInfo(name, phoneNumber);
			}
			
			else if(choice==SubMenuItem.ALUMNI) { //학교친구 입력 후 추가 
				
				System.out.print("전공:"); major = scanner.nextLine();
				System.out.print("학년:"); grade = scanner.nextInt();
				
				pi = new PhoneSchoolInfo(name, phoneNumber, major, grade);
			}
			else if(choice==SubMenuItem.COMPANY) {  //회사동료 입력 후 추가
				
				System.out.print("회사:"); companyName = scanner.nextLine();
				
				pi = new PhoneCompanyInfo(name, phoneNumber, companyName);
			}
			
			boolean overwrite = addrArray.add(pi);
			
			if(overwrite==false) {
				System.out.println("이미 저장된 데이터 입니다.");
				System.out.println("덮어 쓸까요? Y / N");
				char yesNo = scanner.next().charAt(0);
				if(yesNo == 'y' || yesNo == 'Y') {
					addrArray.remove(pi);
					addrArray.add(pi);
					System.out.println("덮어쓰기 완료");
				}
				else if(yesNo == 'n' || yesNo == 'N') {
					System.out.println("기존 데이터를 유지합니다.");
				}
			}
			else {
				System.out.println("==입력이 완료되었습니다==.");
				System.out.println();
			}
			
		}catch(InputMismatchException e) {
			System.out.println("숫자를입력하세요");
			e.printStackTrace();
		}
	}
//	검색 
	public void dataSearch() {
		
		boolean isFind = false;
		Scanner scanner = new Scanner(System.in);
		System.out.println("데이터 검색을 시작합니다.");
		System.out.println("검색할 이름을 입력하세요.");
		String searchName = scanner.nextLine();

		for (PhoneInfo pi : addrArray) {
			//pi의 name과 입력한 searchName이 중복되었는지 비교.
			if (pi.name.equals(searchName)) {
				pi.showPhoneInfo();
				System.out.println("데이터 검색이 완료되었습니다.");
				isFind = true;
			}
		}
		if(isFind==false)
			System.out.println("찾는 정보가 없습니다.");
	}
//
//	삭제 : 
	public void dataDelete() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("데이터 삭제를 시작합니다.");
		System.out.print("이름:");
		String deleteName = scanner.nextLine();
		boolean delete = false;
		
		for (PhoneInfo pi : addrArray) {
			if (pi.name.equals(deleteName)) {
				addrArray.remove(pi);
				delete = true;
				break;
			}
		}
		if(delete){
			System.out.println("==데이터 삭제가 완료되었습니다.==");
		}
		else 
		{
			System.out.println("삭제된 데이터가 없습니다.");
		}
		
	}
//	주소록전체출력 
	public void dataAllShow() {
		for(PhoneInfo pi : addrArray) {
			pi.showPhoneInfo();
		}
		System.out.println("==전체정보가 출력되었습니다.==");
	}
}
