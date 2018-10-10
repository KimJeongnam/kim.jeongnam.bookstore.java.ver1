package service;

import java.util.HashMap;
import java.util.Map;

import domain.Code;
import presentation.Console;

public class Login {
	int code;

	public Login(int code) {

		this.code = code;
	}

	public void tryLogin() throws Exception {
		String Id = "";
		String Pw = "";
		String str = "";
		Map<String, String> user = null;
		if (code == Code.HOST_LOGIN) {
			str = "관리자";
			user = new HashMap<String, String>();
			user.put(Host.ID, Host.PW);
			// Interface host ID
			// Interface host PW
		} else if (code == Code.GUEST_LOGIN) {
			str = "고객";
			user = GuestImpl.getInstance().getUsers();
		}

		System.out.print(str + " ID : ");
		String id = Console.input();

		if (!user.containsKey(id))
			throw new Exception("ID가 다릅니다.");

		System.out.print(str + " PW : ");
		String pw = Console.input();

		if (!user.get(id).equals(pw))
			throw new Exception("Password가 다릅니다");

		System.out.println("===============================");
		System.out.println("\t로그인 되었습니다.");
		System.out.println("===============================");

	}
}
