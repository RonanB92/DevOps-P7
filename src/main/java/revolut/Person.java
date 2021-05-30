package revolut;

import java.util.Currency;
import java.util.HashMap;

public class Person {

    private boolean transferCancelled;
    private String name;
    //Accounts currency & balance
    // EUR 30
    // USD 50
    // STG 30
    private HashMap<String, Account> userAccounts = new HashMap<String, Account>();

    public Person(String name){
        this.name = name;
        //Create a default euro account and add it the our userAccounts container
        Currency accCurrency = Currency.getInstance("EUR");
        Account euroAccount = new Account(accCurrency, 0);
        userAccounts.put("EUR", euroAccount);
    }

    public void setAccountBalance(double startingBalance) {
        userAccounts.get("EUR").setBalance(startingBalance);
    }

    public double getAccountBalance(String eur) {
        return userAccounts.get("EUR").getBalance();
    }

    public Account getAccount(String account) {
        return userAccounts.get(account);
    }

    public void transferMoney(double sendAmount, Person person) {
        if(userAccounts.get("EUR").getBalance() >= sendAmount) {
            userAccounts.get("EUR").subtractFunds(sendAmount);
            person.getAccount("EUR").addFunds(sendAmount);
            transferCancelled = false;
        } else {
            transferCancelled = true;
        }
    }

    public void topUp(double amount, DebitCard card, PaymentService transaction) {
        if(card.getBankBalance() >= amount) {
            userAccounts.get("EUR").addFunds(amount);
            card.setBankBalance(card.getBankBalance() - amount);
            System.out.println("top up successful");
        }
    }

    public boolean isTransferCancelled() {
        return transferCancelled;
    }
}
