package domain;

import java.util.HashMap;
import java.util.Map;

public class Order {
	private static Map<Object, Object> orderList=null;
	
	Order(){
		orderList = new HashMap<Object, Object>();
	}
	
	public void orderAdd(String id, Wish wish) {
		if(orderList.containsKey(id)) {
			Wish getWish = (Wish)orderList.get(id);
			
			Map<Integer, Integer> buf = getWish.getWishList();
			Map<Integer, Integer> order = wish.getWishList();
			
			for(int key : buf.keySet()) {
				if(order.containsKey(key)) {
					int getStock = order.get(key);
					order.put(key, getStock+=buf.get(key));
				}
			}
		}else {
			orderList.put("id", id);
			orderList.put("wish" , wish);
		}
	}
}
