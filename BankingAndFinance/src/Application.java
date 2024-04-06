import service.AccountsService;
import service.InvestmentsService;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        //aici vom avea meniul de comenzi

        Scanner scanner = new Scanner(System.in);
        AccountsService accountsService = new AccountsService();
        InvestmentsService investmentsService = new InvestmentsService();

        while (true){
            menu();
            String command = scanner.nextLine().toLowerCase();
            System.out.println("Command received: " + command);
            switch (command){
                case "create":
                    accountsService.create(scanner);
                    break;
                case "read":
                    accountsService.read(scanner);
                    break;
                case "delete":
                    accountsService.delete(scanner);
                    break;
                case "update":
                    accountsService.update(scanner);
                    break;
                case "quit":
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Wrong command");
            }
        }

    }

    private static void menu() {
        System.out.println("Available commands:");
        System.out.println("create");
        System.out.println("read");
        System.out.println("update");
        System.out.println("delete");
        System.out.println("quit");
        System.out.println("Enter command:");
    }
}