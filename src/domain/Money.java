package domain;
public class Money {
	// 인트형 숫자를 매개변수로하여 뒤에서 3자리마다 ',' 를 찍어 String형으로 반환하는 함수
	public static String moneyToString(int money) {
		String str = "";
		String result = "";
		
		if(money == 0) {
			return "0";
		}
		int cnt=0;
		
		while(money>0) {			// money 가 0보다 작을때까지 반복
			int n = money%10;		// 10으로 나눈 나머지 n에 저장
			money /= 10;			// money를 10으로 나눔
			
			// cnt가 3의배수 이고 cnt가 0이 아닐때 ex) 10,000 cnt가 0이아닐때 조건을 빼면 10,000, 과 같은 결과 발생
			if(cnt%3 ==0 && cnt!=0)		
				str += ',';
			
			str += Integer.toString(n);	//숫자의 맨뒤부터 차례대로 저장  ex) 000,0001
			cnt++;
		}
		
		/*
		 * str을 거꾸로 반전시켜 result 에 저장
		 */
		for(int i=str.length()-1; i>=0; i--) {
			result += str.charAt(i);
		}
		return result; 
	}
}