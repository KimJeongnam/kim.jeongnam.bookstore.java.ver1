package presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import MyException.IntegerException;
import MyException.NegativeNumberException;
import domain.Code;

public class Console {
    private static BufferedReader reader;

    public static String input() {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in, "MS949"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = "";

        try {
            str = reader.readLine().trim();
        } catch (IOException e) {
            System.out.println("키보드 입력 에러");
            e.printStackTrace();
        }

        return str;
    }

    public static int inputPriceAndStock() throws IntegerException, NegativeNumberException {
        int result;

        String str = Console.input();

        if (!Code.isNumeric(str)) {
            throw new IntegerException(str);
        }

        result = Integer.parseInt(str);

        if (result < 0)
            throw new NegativeNumberException(str);

        return result;
    }
    
    public static int inputCode() throws IntegerException{
        int result = 0;
        
        String str = Console.input();
        
        if(!Code.isNumeric(str))
            throw new IntegerException(str, "[Error] 코드는 숫자 입니다.!");
        
        result = Integer.parseInt(str);
        
        return result;
    }
}
