package project1;

import java.util.Scanner;

import project1.ver04.PhoneBookManager;
import project1.ver04.PhoneInfo;

public class PhoneBookVer04
{
	PhoneBookManager pb = new PhoneBookManager(100);
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		//초기값으로 100명의 정보를 저장할 수 있는 객체배열 생성
		PhoneBookManager pb1 = new PhoneBookManager(100);
		
		while(true) {
			
			pb1.printMenu();
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice){
			case 1:
				//데이터 입력
				pb1.dataInput();
				
				break;
			case 2:
				pb1.dataSearch();
				break;
				
			case 3:
				pb1.dataDelete();
				break;
			case 4:
				pb1.dataAllShow();
				break;
			case 5:	
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}	
	}
}
