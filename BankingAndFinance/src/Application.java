
import service.AccountsService;
import service.InvestmentsService;
import service.TransactionService;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {
        //aici vom avea meniul de comenzi

        Scanner scanner = new Scanner(System.in);
        TransactionService transactionService = new TransactionService();
        AccountsService accountsService = new AccountsService();
        transactionService.setAccountsService(accountsService);
        accountsService.setTransactionService(transactionService);
        InvestmentsService investmentsService = new InvestmentsService();




        while (true) {
            menu();
            String command = scanner.nextLine().toLowerCase();
            System.out.println("Command received: " + command);

            switch (command) {
                case "create":
                case "read":
                case "delete":
                case "update":
                    processCommand(command, accountsService, investmentsService, scanner);
                    break;
                case "access_account":
                    processAccountCommand("access_account", accountsService, scanner);
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
        System.out.println("access_account");
        System.out.println("update ");
        System.out.println("delete ");
        System.out.println("quit");
        System.out.println("Enter command:");
    }

    private static void processCommand(String command, AccountsService accountsService, InvestmentsService investmentsService, Scanner scanner) {
        System.out.println("Is this for an account or investment?");
        String type = scanner.nextLine().toLowerCase();
        if (type.equals("account"))
            processAccountCommand(command, accountsService, scanner);
        else if (type.equals("investment"))
            processInvestmentCommand(command, investmentsService, scanner);
        else {
                System.out.println("Invalid type, please specify 'account' or 'investment'.");
        }
    }


    private static void processAccountCommand(String command, AccountsService accountsService, Scanner scanner) {
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
            case "access_account":
                accountsService.access_account(scanner);
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    private static void processInvestmentCommand(String command, InvestmentsService investmentsService, Scanner scanner) {
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
            default:
                System.out.println("Invalid command");
        }
    }
}


