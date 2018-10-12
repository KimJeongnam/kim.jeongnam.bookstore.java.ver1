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
		// key:id  value:pw 인 map
		Map<String, String> user = null;
		
		if (code == Code.HOST_LOGIN) {                // host 로그인 설정일시
			str = "관리자";
			user = new HashMap<String, String>();
			user.put(Host.ID, Host.PW);
		} else if (code == Code.GUEST_LOGIN) {    //Guest 로그인 설정일시
			str = "고객";
			user = GuestImpl.getInstance().getUsers();	// User 목록 불러오기
		}

		System.out.print(str + " ID : ");
		String id = Console.input();

		if (!user.containsKey(id))
			throw new Exception("ID가 다릅니다.");

		System.out.print(str + " PW : ");
		String pw = Console.input();

		if (!user.get(id).equals(pw))
			throw new Exception("Password가 다릅니다");

		// 앞의 에러처리를 모두 뚫고 왔다면 로그인 성공으로 판단 
		session = new Session();          // static session에 세션 객체 생성
		session.getMap().put("id", id);
		System.out.println("===============================");
		System.out.println("\t로그인 되었습니다.");
		System.out.println("===============================");

	}
}
