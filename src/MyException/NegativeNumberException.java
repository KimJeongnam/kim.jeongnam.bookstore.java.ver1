package MyException;

public class NegativeNumberException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
 private String worngStr;
    
    public NegativeNumberException(String worngStr){
        this.worngStr = worngStr;
        print();
    }
    
    public NegativeNumberException(String worngStr, String message){
        super(message);
        this.worngStr = worngStr;
        print();
    }
    
    public void print(){
        System.err.println("[Error] 가격및 수량은 0보다 큰 양수입니다.  worng input : "+worngStr);
    }
}