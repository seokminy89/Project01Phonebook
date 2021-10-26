package project1;

import java.util.Scanner;

import project1.ver02.PhoneInfo;

public class PhoneBookVer02
{
	public static void menuShow() {
		System.out.println("### 메뉴를 입력하세요 ### ");
		System.out.print("1.데이터 입력 ");
		System.out.println("2.프로그램 종료");
	}
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			String name, phoneNumber, birthday;
			
			menuShow();
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice){
			case 1:
				System.out.print("이름:"); 
				name = scanner.nextLine();
				System.out.print("전화번호:"); 
				phoneNumber = scanner.nextLine();
				System.out.print("생년월일:"); 
				birthday = scanner.nextLine();
				
				PhoneInfo pi1 = new PhoneInfo(name, phoneNumber, birthday);
				pi1.showPhoneInfo();
				break;
				
			case 2:
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}	
	}
}
