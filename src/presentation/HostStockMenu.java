package presentation;

import domain.Code;
import service.HostImpl;
import service.Shop;

public class HostStockMenu implements Menu {
    private int thisCode;
    
    public HostStockMenu(int thisCode){
        this.thisCode = thisCode;
    }

    @Override
    public void printMenu() {
        // TODO Auto-generated method stub
        System.out.println(Menu.HEADER_BAR + "제고 관리" + Menu.HEADER_BAR);
        System.out.println("\t1.목록\t2.추가\t3.수정\t4.삭제\t5.이전");
        System.out.println(Menu.FOOTER_BAR);
        System.out.println(Menu.INPUT_STR);
    }

    @Override
    public void execute() throws Exception {
        // TODO Auto-generated method stub
        printMenu();

        String option = Console.input();
        int code = Code.ERROR;
        
        switch(option){
        case "1":
            HostImpl.getInstance().bookList();
            code = thisCode;
            break;
        case "2":
            break;
        case "3":
            break;
        case "4":
            break;
        case "5":
            break;
            default:
                break;
        }
        
        Shop.setCode(code);
    }

}
