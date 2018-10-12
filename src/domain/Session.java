package domain;

import java.util.HashMap;
import java.util.Map;

public class Session {
	private Map<String, String> map = new HashMap<String, String>();   // key= "id" , value = userid
	
	public Map<String, String> getMap(){ return map; }
	
}
