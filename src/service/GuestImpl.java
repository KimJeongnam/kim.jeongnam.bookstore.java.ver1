package service;

import java.util.HashMap;
import java.util.Map;

public class GuestImpl implements Guest{
	private static GuestImpl guestImpl = new GuestImpl();
	private Map<String, String> users = new HashMap<String, String>();
	
	private GuestImpl() {}
	
	public static GuestImpl getInstance() { return guestImpl; }
	public Map<String,String> getUsers(){ return users; }
	
	@Override
	public void wishList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cartAdd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cartDel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nowBuy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orderList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refund() {
		// TODO Auto-generated method stub
		
	}

}
