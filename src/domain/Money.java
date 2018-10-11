package domain;
public class Money {
	public static String moneyToString(int money) {
		String str = "";
		String result = "";
		
		if(money == 0) {
			return "0";
		}
		int cnt=0;
		
		while(money>0) {
			int n = money%10;
			money /= 10;
			if(cnt%3 ==0 && cnt!=0)
				str += ',';
			str += Integer.toString(n);
			cnt++;
		}
		for(int i=str.length()-1; i>=0; i--) {
			result += str.charAt(i);
		}
		return result; 
	}
}