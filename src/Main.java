import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[5];
        accounts[0] = new BankAccount("Bob");
        accounts[1] = new BankAccount("Bob");
        LocalDateTime dt = LocalDateTime.now();
        accounts[2] = new BankAccount("Robert", dt, "77777777");
        accounts[3] = new BankAccount("Bob", dt, "77777777");
        accounts[4] = new BankAccount("Bob", dt, "77777777");

        for(int i = 0; i < accounts.length; i++){
            System.out.printf("%d: %s%n", i + 1, accounts[i]);
        }

        System.out.println();
        testHashCodes(accounts, 0, 1);
        testEquality(accounts, 0, 1);
        testHashCodes(accounts, 2, 3);
        testEquality(accounts, 2, 3);
        testHashCodes(accounts, 3, 4);
        testEquality(accounts, 3, 4);

        int[] depositAmounts = {10000, -1000, Integer.MAX_VALUE};
        for (int depositAmount : depositAmounts) {
            testDeposit(accounts[0], depositAmount);
        }

        int[] transferAmounts = {2000, -100, 20000};
        for (int transferAmount : transferAmounts) {
            testTransfer(accounts, 0, 1, transferAmount);
        }

        int[] withdrawAmounts = {2000, -1000, 10000, 0};
        for (int withdrawAmount : withdrawAmounts) {
            testWithdraw(accounts[0], withdrawAmount);
        }
    }

    public static void testDeposit(BankAccount acc, int amount){
        System.out.printf("\nПополнение аккаунта на %d:\n", amount);
        System.out.printf("Баланс до: %d\n", acc.getBalance());
        if (!acc.deposit(amount)) {
            System.out.println("Ошибка! Баланс не был пополнен");
        }
        System.out.printf("Баланс после: %d\n", acc.getBalance());
    }

    public static void testTransfer(BankAccount[] accs, int index1, int index2, int amount){
        System.out.printf("\nПеревод с аккаунта %d на аккаунт %d суммой %d:\n",
                index1 + 1, index2 + 1, amount);
        System.out.printf("Баланс до: Аккаунт %d: %d, Аккаунт %d: %d\n",
                index1 + 1, accs[index1].getBalance(), index2 + 1, accs[index2].getBalance());
        if (accs[index1].transfer(accs[index2], amount)) {
            System.out.println("Перевод совершён");
        } else System.out.println("Ошибка! Перевод не был произведён");
        System.out.printf("Баланс после: Аккаунт %d: %d, Аккаунт %d: %d\n",
                index1 + 1, accs[index1].getBalance(), index2 + 1, accs[index2].getBalance());
    }

    public static void testWithdraw(BankAccount acc, int amount){
        System.out.printf("\nСнятие с аккаунта на %d:\n", amount);
        System.out.printf("Баланс до: %d\n", acc.getBalance());
        if (!acc.withdraw(amount)) System.out.println("Ошибка! Снятие не было произведено");
        System.out.printf("Баланс после: %d\n", acc.getBalance());
    }

    public static void testHashCodes(BankAccount[] accs, int index1, int index2){
        if (accs[index1].hashCode() == accs[index2].hashCode()) {
            System.out.printf("Хэш коды аккаунтов %d и %d равны\n", index1 + 1, index2 + 1);
        }else System.out.printf("Хэш коды аккаунтов %d и %d не равны\n", index1 + 1, index2 + 1);
    }

    public static void testEquality(BankAccount[] accs, int index1, int index2){
        if (accs[index1].equals(accs[index2])) {
            System.out.printf("Аккаунты %d и %d равны\n", index1 + 1, index2 + 1);
        } else System.out.printf("Аккаунты %d и %d не равны\n", index1 + 1, index2 + 1);
    }
}