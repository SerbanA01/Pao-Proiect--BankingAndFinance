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
                case "read":
                case "delete":
                case "update":
                    processCommand(command, accountsService, investmentsService, scanner);
                    break;
                case "quit":
                    System.out.println("Exiting");
                    scanner.close();
                    return;
                default:
                    System.out.println("Wrong command");
            }
        }

    }

    private static void menu() {
        System.out.println("Available commands:");
        System.out.println("create ");
        System.out.println("read ");
        System.out.println("update ");
        System.out.println("delete ");
        System.out.println("quit");
        System.out.println("Enter command:");
    }
    private static void processCommand(String command, AccountsService accountsService, InvestmentsService investmentsService, Scanner scanner) {
        System.out.println("Is this for an account or investment?");
        String type = scanner.nextLine().toLowerCase();
        switch (type) {
            case "account":
                switch (command) {
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
                }
                break;
            case "investment":
                switch (command) {
                    case "create":
                        investmentsService.create(scanner);
                        break;
                    case "read":
                        investmentsService.read(scanner);
                        break;
                    case "delete":
                        investmentsService.delete(scanner);
                        break;
                    case "update":
                        investmentsService.update(scanner);
                        break;
                }
                break;
            default:
                System.out.println("Invalid type, please specify 'account' or 'investment'.");
        }
    }
}