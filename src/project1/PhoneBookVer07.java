package project1;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import project1.ver07.MenuItem;
import project1.ver07.MenuSelectException;
import project1.ver07.PhoneBookManager;
import project1.ver07.PhoneInfo;

public class PhoneBookVer07
{
	
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		//초기값으로 100명의 정보를 저장할 수 있는 객체배열 생성
		PhoneBookManager pb1 = new PhoneBookManager();
		
		
		while(true) {
			
			pb1.printMenu();
			int choice = 0;
			
			try {
				
				choice = selectMenu();
				
			}
			catch(InputMismatchException e) {
				System.out.println("숫자를입력하세요");
				e.printStackTrace();
			}
			catch(MenuSelectException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			
			switch(choice){
			case MenuItem.INPUT:
				//데이터 입력
				System.out.println("==주소록을 입력함==");
				pb1.dataInput();
				
				break;
			case MenuItem.SEARCH:
				pb1.dataSearch();
				break;
				
			case MenuItem.DELETE:
				pb1.dataDelete();
				break;
			case MenuItem.PRINT:
				pb1.dataAllShow();
				break;
			case MenuItem.EXIT:	
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}	
	}//end of main
	public static int selectMenu() throws MenuSelectException{
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		scanner.nextLine();
		
		if(!(choice>=1 && choice <=5)) {
			throw new MenuSelectException();
		}
		return choice;
	}
}
