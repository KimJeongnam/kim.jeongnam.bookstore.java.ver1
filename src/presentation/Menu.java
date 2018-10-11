package presentation;

public interface Menu {
    public static String HEADER_BAR = "----------------------";
    public static String FOOTER_BAR = "--------------------------------------------------";
    
    public static String RESULT_HEADER = "====================";
    public static String RESULT_FOOTER = "==============================================";
    public static String INPUT_STR = "\t메뉴 번호를 입력하세요. : ";
    
    //메뉴 출력
    public void printMenu();
    //모든 메뉴는 execute로 시작
	public void execute() throws Exception;
}
