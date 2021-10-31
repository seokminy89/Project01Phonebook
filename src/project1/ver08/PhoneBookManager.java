package project1.ver08;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import project1.ver08.PhoneInfo;

import project1.ver08.SubMenuItem;


public class PhoneBookManager 
{
	//키보드 입력을 위한 스캐너 객체 생성인데
	//static으로 선언하였기 때문에 클래스 전체 영역에서 추후 객체 생성 없이 접근이 가능
	public static Scanner scanner = new Scanner(System.in);
	
	//주소록 저장을 위한 HashSet<E>컬렉션을 선언
	HashSet<PhoneInfo> addrArray = new HashSet<PhoneInfo>();
	
	//8단계 : I/O스트림을 통한 직렬화
	private final File dataFile =
		new File("src/project1/ver08/PhoneBook.obj");
	
	//기본 생성자.
	public PhoneBookManager() {
		/*
		프로그램이 실행된 후  PhoneBookManager의 객체가 생성될때
		readFile()메소드를 호출하여 phoneBook.obj 파일을 읽어 객체화 한다.
		 */
		readFile();
	}
	
	//폰북이 저장된 파일을 읽는다.
	public void readFile() {
		//만약 해당 경로에 파일이 없다면 실행 중지.
		if(dataFile.exists()==false) {
			System.out.println("PhoneBook.obj 파일없음");
			return;
		}
		//파일이 존재한다면 읽어서 컬렉션에 저장한다.
		try {
			FileInputStream file = new FileInputStream(dataFile);
			ObjectInputStream in = new ObjectInputStream(file);
			while(true) {
				PhoneInfo info = (PhoneInfo)in.readObject();
				if(info==null) {
					break;
				}
				addrArray.add(info);
			}
		}
		catch(Exception e){}
	}
	
	//컬렉션에 남아있는 정보를 파일로 저장한다.
	public void saveFile() {
		try {
			FileOutputStream file = new FileOutputStream(dataFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			Iterator<PhoneInfo> itr = addrArray.iterator();
			while(itr.hasNext()) {
				/*
				반복자를 통해 컬렉션에 저장된 모든 인스턴스를
				writeObject 메소드를 통해 파일에 저장한다.
				 */
				out.writeObject(itr.next());
			}
			out.close();
			System.out.println("obj 파일로 저장되었습니다.");
		}
		catch(IOException e) {
			System.out.println("PhoneBook.obj 저장중 예외발생");
		}
		
	}
	
	//메뉴 출력용
	public static void printMenu(){
		System.out.println("====================메뉴를 선택하세요===================");
		System.out.print("1.주소록 입력 ");
		System.out.print("2.검색 ");
		System.out.print("3.삭제 ");
		System.out.print("4.출력 ");
		System.out.print("5.저장옵션 ");
		System.out.println("6.종료");
		System.out.println("========================================================");
		System.out.print("메뉴선택:");
	}
	
	
//	입력 : 
	public void dataInput() {
		Scanner scanner = new Scanner(System.in);
		String name, phoneNumber, major, companyName;
		int grade;
		
		/*
		부모클래스인 PhoneInfo의 참조변수로 하위클래스의
		인스턴스를 참조할 수 있으므로 객체를 먼저 생성함.
		 */
		PhoneInfo pi = null;
		
		try {
			
			System.out.println("데이터 입력을 시작합니다.");
			System.out.println("1.일반 2.동창 3.직장동료");
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
		System.out.println("==주소록 출력이 완료되었습니다.==");
	}//end of dataAllShow
	
	public void dataSaveOption(AutoSaver sa) {
		System.out.println("저장옵션을 선택하세요.");
		System.out.print("1. 자동저장On, 2.자동저장Off\n선택:");
		int menu = scanner.nextInt();
		if(menu==1) {
			
			//자동저장 쓰레드 start
			if(!sa.isAlive()) {
				sa.setDaemon(true);
				sa.start();
				System.out.println("자동저장을 시작합니다.");
			}
			else {
				System.out.println("이미 자동저장이 실행중입니다.");
			}
		}
		else if(menu==2) {
			//자동저장 쓰레드 interrupt
			if(sa.isAlive()) {
				sa.interrupt();
				System.out.println("자동저장을 종료합니다.");
			}
		}
		else {
			System.out.println("메뉴를 잘못 입력하셨습니다.");
		}
	}//end of dataSaveOption
	
	public void saveInfoText() {
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter("src/project1/ver08/AutoSaveBook.txt"));
			
			for(PhoneInfo pi : addrArray) {
				out.println(pi);
			}
			out.close();
			System.out.println("주소록이 텍스트로 자동저장되었습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
