package project1.ver08;

import java.util.Objects;

public class PhoneInfo
{
	String name;//이름
	String phoneNumber;//전화번호
	
	//2개의 매개변수를 가진 생성자 오버로딩
	public PhoneInfo(String name, String phoneNumber)
	{
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	//정보 출력용 멤버메서드
	public void showPhoneInfo() {
		System.out.println("이름:"+name);
		System.out.println("전화번호:"+phoneNumber);
	}
	
	//객체의 중복체크를 위해 해시코드와 이퀄스 메소드를 오버라이딩 해줌.
	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
	@Override
	public boolean equals(Object obj)
	{
		//매개변수를 Object로 받으므로 다운캐스팅 필요함.
		PhoneInfo other = (PhoneInfo) obj;
		if(this.name.equals(other.name)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
