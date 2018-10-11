package presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	private static BufferedReader reader;
	
	public static String input() {
		try {
		reader = new BufferedReader(new InputStreamReader(System.in,"MS949"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		String str = "";
		
		try {
			str = reader.readLine();
		}catch(IOException e) {
			System.out.println("키보드 입력 에러");
			e.printStackTrace();
		}
		
		return str;
	}
}
