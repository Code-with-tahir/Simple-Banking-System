import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("Deposited: %.2f. New balance: %.2f%n", amount, balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds for this withdrawal.");
        } else {
            balance -= amount;
            System.out.printf("Withdrew: %.2f. New balance: %.2f%n", amount, balance);
        }
    }

    public void checkBalance() {
        System.out.printf("Current balance: %.2f%n", balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}

class Customer {
    private String name;
    private BankAccount account;

    public Customer(String name, String accountNumber) {
        this.name = name;
        this.account = new BankAccount(accountNumber);
    }

    public void displayInfo() {
        System.out.println("Customer Name: " + name);
        System.out.println("Account Number: " + account.getAccountNumber());
        account.checkBalance();
    }

    public BankAccount getAccount() {
        return account;
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to Simple Bank ===");
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter desired account number: ");
        String accountNumber = scanner.nextLine();

        Customer customer = new Customer(name, accountNumber);
        System.out.println("\nAccount created successfully!");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Display Account Info");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    customer.getAccount().deposit(depositAmount);
                    break;
                case "2":
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    customer.getAccount().withdraw(withdrawAmount);
                    break;
                case "3":
                    customer.getAccount().checkBalance();
                    break;
                case "4":
                    customer.displayInfo();
                    break;
                case "5":
                    running = false;
                    System.out.println("Thank you for using Simple Bank. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
