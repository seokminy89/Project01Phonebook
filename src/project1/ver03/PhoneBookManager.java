package project1.ver03;

import java.util.Scanner;


public class PhoneBookManager 
{
	//멤버변수
	//폰 정보를 저장할 객체 배열
	public PhoneInfo[] myFriends; 
	//저장된 배열의 갯수
	public int numOfFriends;
	
	
	
	public PhoneBookManager(int num) {
		//num 크기의 객체배열 생성.
		myFriends = new PhoneInfo[num];
		
		numOfFriends = 0;
		
	}
	//메뉴 출력용
	public static void printMenu(){
		System.out.println("선택하세요...");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 검색");
		System.out.println("3. 데이터 삭제");
		System.out.println("4. 주소록 출력");
		System.out.println("5. 프로그램 종료");
	}
	
	
//	입력 : 
	public void dataInput() {
		Scanner scanner = new Scanner(System.in);
		String name, phoneNumber, birthday;
		
		System.out.println("데이터 입력을 시작합니다.");
		System.out.print("이름:"); 
		name = scanner.nextLine();
		System.out.print("전화번호:"); 
		phoneNumber = scanner.nextLine();
		System.out.print("생년월일:"); 
		birthday = scanner.nextLine();
		
		PhoneInfo pi1 = new PhoneInfo(name, phoneNumber, birthday);
		myFriends[numOfFriends++] = pi1;
		System.out.println("데이터 입력이 완료되었습니다.");
		System.out.println();
	}
//	검색 
	public void dataSearch() {
		
		boolean isFind = false;
		Scanner scanner = new Scanner(System.in);
		System.out.println("데이터 검색을 시작합니다.");
		System.out.println("검색할 이름을 입력하세요.");
		String searchName = scanner.nextLine();
		
		//검색하기위해 배열 크기만큼 반복.
		for(int i = 0 ; i <= numOfFriends ; i++) {
			//내가 입력한 searchName과 배열안의 name을 비교하기.
			if(searchName.compareTo(myFriends[i].name)==0) {
				myFriends[i].showPhoneInfo();
			System.out.println("데이터 검색이 완료되었습니다.");
			
			}
			
		}
		if(isFind==false)
			System.out.println("찾는 정보가 없습니다.");
	}
//
//	삭제 : dataDelete()
//	주소록전체출력 
	public void dataAllShow() {
		for(int i=0; i<=numOfFriends ; i++) {
	
		
	}
		
		System.out.println("==전체정보가 출력되었습니다.==");
	}
}
