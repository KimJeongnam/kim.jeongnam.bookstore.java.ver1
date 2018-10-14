package MyException;

public class IntegerException extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String worngStr;
    
    public IntegerException(String worngStr){
        this.worngStr = worngStr;
        print();
    }
    
    public IntegerException(String worngStr, String meesage){
        super(meesage);
        this.worngStr = worngStr;
        print();
    }
    
    public void print(){
        System.out.println("[Error] 숫자를 입력해주세요  worng input : "+worngStr);
    }
    
}

