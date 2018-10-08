package service;

import domain.Code;
import presentation.Console;

public class Login {
	String id;
	String pw;
	int code;
	
	public Login(String id, String pw, int code) {
		this.id = id;
		this.pw = pw;
		this.code = code;
	}
	
	public void tryLogin() throws Exception{
		String Id = "";
		String Pw = "";
		String str="";
		if(code == Code.HOST_LOGIN) {
			str = "관리자";
			Id = Host.ID;
			Pw = Host.PW;
		}else if(code == Code.GUEST_LOGIN){
			str = "고객";
		}
		
		System.out.print(str+" ID : ");
		String id = Console.input();
		
		if(!id.equals(Id))
			throw new Exception("ID가 다릅니다.");
		
		System.out.print(str+" PW : ");
		String pw = Console.input();
		
		if(!pw.equals(Pw))
			throw new Exception("Password가 다릅니다");
	
		System.out.println("===============================");
		System.out.println("\t로그인 되었습니다.");

	}
}
