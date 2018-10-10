package domain;

import java.util.HashMap;
import java.util.Map;

import presentation.Console;
import service.GuestImpl;
import service.Host;

public class Login {
	private static Session session= null;
	private int code;
	
	public Login(int code) {

		this.code = code;
	}
	
	public static Session getSession() { return session; }

	public void tryLogin() throws Exception {
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

		session = new Session();
		session.getSession().put("id", id);
		System.out.println("===============================");
		System.out.println("\t로그인 되었습니다.");
		System.out.println("===============================");

	}
}
