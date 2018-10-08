package presentation;

import domain.Code;
import service.Host;
import service.Shop;

public class HostLoginMenu implements Menu{
	
	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		int code = Code.ERROR;
		
		System.out.println("------------관리자 로그인 -------------");
		System.out.print("관리자 ID : ");
		String id = Console.input();
		
		if(!id.equals(Host.ID))
			throw new Exception("ID가 다릅니다.");
		
		System.out.print("관리자 PW : ");
		String pw = Console.input();
		
		if(!pw.equals(Host.PW))
			throw new Exception("Password가 다릅니다");
	
		code = Code.HOST_MENU;
		System.out.println("===============================");
		System.out.println("\t로그인 되었습니다.");
		System.out.println("===============================");
	
		Shop.setOption(code);
	}
}
