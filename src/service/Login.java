package service;

import domain.Code;
import presentation.Console;

public class Login {
	int code;
	
	public Login(int code) {

		this.code = code;
	}
	
	public void tryLogin() throws Exception{
		String Id = "";
		String Pw = "";
		String str="";
		if(code == Code.HOST_LOGIN) {
			str = "관리자";
			Id = Host.ID;			// Interface host ID
			Pw = Host.PW;		// Interface host PW
		}else if(code == Code.GUEST_LOGIN){
			str = "고객";
			Id = null;
			Pw = null;
		}
		
		System.out.println("------------"+str+" 로그인 -------------");
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
