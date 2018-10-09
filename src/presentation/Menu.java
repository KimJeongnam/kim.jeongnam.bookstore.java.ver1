package presentation;

public interface Menu {
    public static String HEADER_BAR = "----------------------";
    public static String FOOTER_BAR = "--------------------------------------------------";
    public static String INPUT_STR = "\t메뉴 번호를 입력하세요. : ";
    
    public void printMenu();
	public void execute() throws Exception;
}
