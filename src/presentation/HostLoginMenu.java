package presentation;

import domain.Code;
import service.Login;
import service.Shop;

public class HostLoginMenu implements Menu{
	
	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		int code = Code.ERROR;
		Login login = new Login(Code.HOST_LOGIN);
		
		login.tryLogin();
	
		Shop.setOption(code);
	}
}
