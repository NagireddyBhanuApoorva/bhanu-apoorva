import java.util.Scanner;

class BankAccount {
 private double balance;

 public BankAccount(double initialBalance) {
 this.balance = initialBalance;
 }

 public void deposit(double amount) {
 if (amount > 0) {
 balance += amount;
 System.out.println("Deposit successful. New balance: $" + balance);
 } else {
 System.out.println("Invalid deposit amount.");
 }
 }

 public void withdraw(double amount) {
 if (amount > 0 && amount <= balance) {
 balance -= amount;
 System.out.println("Withdrawal successful. New balance: $" + balance);
 } else {
 System.out.println("Insufficient funds or invalid withdrawal amount.");
 }
 }

 public double checkBalance() {
 return balance;
 }
}

class ATM {
 public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);
 BankAccount account = new BankAccount(1000.0); // Initial balance

 while (true) {
 System.out.println("\nATM Menu:");
 System.out.println("1. Deposit");
 System.out.println("2. Withdraw");
 System.out.println("3. Check Balance");
 System.out.println("4. Exit");
 System.out.print("Enter your choice: ");
 int choice = scanner.nextInt();

 switch (choice) {
 case 1:
 System.out.print("Enter deposit amount: $");
 double depositAmount = scanner.nextDouble();
 account.deposit(depositAmount);
 break;
 case 2:
 System.out.print("Enter withdrawal amount: $");
 double withdrawalAmount = scanner.nextDouble();
 account.withdraw(withdrawalAmount);
 break;
 case 3:
 System.out.println("Current balance: $" + account.checkBalance());
 break;
 case 4:
 System.out.println("Thank you for using the ATM!");
 scanner.close();
 System.exit(0);
 default:
 System.out.println("Invalid choice. Try again.");
 }
 }
 }
}
