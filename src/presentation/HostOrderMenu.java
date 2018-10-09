package presentation;

public class HostOrderMenu implements Menu{
    private static HostOrderMenu hostOrderMenu = new HostOrderMenu();

    public static HostOrderMenu getInstance() {
        return hostOrderMenu;
    }
    
    @Override
    public void printMenu(){
        System.out.println(Menu.HEADER_BAR+"주문 관리"+Menu.HEADER_BAR);
        System.out.println("");
        System.out.println(Menu.FOOTER_BAR);
        System.out.println(Menu.INPUT_STR);
    }

    @Override
    public void execute() throws Exception {
        // TODO Auto-generated method stub
        printMenu();
        
        String option = Console.input();
        
        
    }
    
    
}
