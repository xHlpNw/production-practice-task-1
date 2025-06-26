import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class BankAccount {
    private String ownerName;
    private int balance;
    private final LocalDateTime openDate;
    private boolean isBlocked;
    private final String number;

    public BankAccount(String ownerName){
        this.ownerName = ownerName;
        balance = 0;
        openDate = LocalDateTime.now();
        isBlocked = false;
        number = generateAccountNumber();
    }

    // Конструктов для тестирования hashCode() и equals()
    public BankAccount(String ownerName, LocalDateTime openDate, String accountNumber){
        this.ownerName = ownerName;
        this.openDate = openDate;
        this.balance = 0;
        this.isBlocked = false;
        this.number = accountNumber;
    }

    private String generateAccountNumber(){
        StringBuilder sb = new StringBuilder(8);
        Random rand = new Random();
        for(int i = 0; i < 8; i++){
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public boolean deposit(int amount){
        if (isBlocked || amount <= 0) return false;
        if (balance > Integer.MAX_VALUE - amount) return false;
        balance += amount;
        return true;
    }

    public boolean withdraw(int amount){
        if (isBlocked || amount <= 0 || balance < amount) return false;
        balance -= amount;
        return true;
    }

    public boolean transfer(BankAccount otherAccount, int amount){
        if (this.isBlocked || otherAccount.isBlocked() || this.balance < amount || amount <= 0
                || otherAccount.getBalance() > Integer.MAX_VALUE - amount) return false;
        this.balance -= amount;
        otherAccount.balance += amount;
        return true;
    }

    @Override
    public String toString(){
        return String.format("Номер аккаунта: %s, имя владельца: %s, баланс: %d, дата открытия: %s, блокировка: %s",
                this.getNumber(), this.getOwnerName(), this.getBalance(), this.getOpenDate(), this.isBlocked());
    }

    @Override
    public int hashCode(){
        return Objects.hash(number);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof BankAccount)) return false;
        BankAccount other = (BankAccount) obj;
        if (!this.getNumber().equals(other.getNumber())) return false;
        if (!this.getOwnerName().equals(other.getOwnerName())) return false;
        if (this.balance != other.balance) return false;
        if (!this.getOpenDate().equals(other.getOpenDate())) return false;
        return this.isBlocked() == other.isBlocked();
    }


    public String getOwnerName(){
        return this.ownerName;
    }
    public void setOwnerName(String name){
        this.ownerName = name;
    }
    public String getNumber(){
        return this.number;
    }
    public int getBalance(){
        return this.balance;
    }
    public LocalDateTime getOpenDate(){
        return this.openDate;
    }
    public boolean isBlocked(){
        return this.isBlocked;
    }
    public void setBlocked(boolean blocked){
        this.isBlocked = blocked;
    }
}
