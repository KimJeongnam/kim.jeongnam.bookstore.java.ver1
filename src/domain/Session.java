package domain;

import java.util.HashMap;
import java.util.Map;

public class Session {
	private Map<String, String> session = null;
	
	public Session() {
		session = new HashMap<String, String>();
	}
	
	public Map<String, String> getSession(){ return session; }
	
}
