package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;
import revolut.DebitCard;

public class StepDefinitions {

    private double topUpAmount;
    private double transferAmount;
    //private String topUpMethod;
    PaymentService topUpMethod;
    Person danny;
    Person rx;

    DebitCard bankCard = new DebitCard();

    @Before//Before hooks run before the first step in each scenario
    public void setUp() {
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        rx = new Person("Receiver");

    }

    //************** Add money to Revolut using Debit Card **************

    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        //System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance(startingBalance);
        //double newAccountBalance = danny.getAccountBalance("EUR");
        //System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        this.topUpAmount = topUpAmount;
        //throw new io.cucumber.java.PendingException();
    }

    //@Given("Danny selects his {word} as his topUp method")
    @Given("Danny selects his {paymentService} as his topUp method")
    //public void danny_selects_his_debit_card_as_his_top_up_method(String topUpSource) {
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @And("Danny has {double} euro in his debit card")
    public void dannyHasEuroInHisDebitCard(double debitStartBalance) {
        bankCard.setBankBalance(debitStartBalance);
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        danny.topUp(topUpAmount, bankCard, topUpMethod);
        //throw new io.cucumber.java.PendingException();
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0);
        System.out.println("The new final balance is: " + actualResult);
    }

    //************** Add various amounts to Revolut acc **************

    @Given("Danny has a starting balance of {double}")
    public void dannyHasAStartingBalanceOfStartBalance(double startBalance) {
        danny.setAccountBalance(startBalance);
    }

    @When("Danny now tops up by {double}")
    public void dannyNowTopsUpByTopUpAmount(double topUpAmount) {
        danny.getAccount("EUR").addFunds(topUpAmount);
    }

    @Then("The balance in his euro account should be {double}")
    public void theBalanceInHisEuroAccountShouldBeNewBalance(double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0);
        System.out.println("The new final balance is: " + actualResult);
    }

    //************** Payment Service rejects request **************

    @Given("Danny has {double} euro in his Revolut account")
    public void dannyHasEuroInHisRevolutAccount(double startingBalance) {
        danny.setAccountBalance(startingBalance);
    }

    @And("Danny has {double} euro in his BankAccount")
    public void dannyHasEuroInHisBankAccount(double bankBalance) {
        bankCard.setBankBalance(bankBalance);
    }

    @Then("The balance in his Revolut account should remain {double} euro")
    public void theBalanceInHisRevolutAccountShouldRemainEuro(double startingBalance) {
        //Arrange
        double expectedResult = startingBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @And("Danny selects {double} euro as the top up amount")
    public void dannySelectsEuroAsTheTopUpAmount(double topUpAmount) {
        this.topUpAmount = topUpAmount;
    }

    @Then("The new balance of his Revolut account should be {double} euro")
    public void theNewBalanceOfHisRevolutAccountShouldBeEuro(double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0);
        System.out.println("The new final balance is: " + actualResult);
    }

    //***************************** Send Money Feature *****************************

    @Given("Danny has a balance of {double} euro in his euro Revolut account")
    public void dannyHasABalanceOfEuroInHisEuroRevolutAccount(double startingBalance) {
        danny.setAccountBalance(startingBalance);
    }
    @And("Danny selects {double} euro as the amount to send")
    public void dannySelectsSendAmountEuroAsTheAmountToSend(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    @And("The receiver has a starting balance of {double}")
    public void theReceiverHasAStartingBalanceOfRxStartBalance(double rxStartBalance) {
        rx.setAccountBalance(rxStartBalance);
    }

    @When("Danny sends money")
    public void dannySendsMoney() {
        danny.transferMoney(transferAmount, rx);
    }

    @Then("The new balance of his Revolut account should now be {double}")
    public void theNewBalanceOfHisRevolutAccountShouldNowBeDannyNewBalance(double dannyNewBalance) {
        //Arrange
        double expectedResult = dannyNewBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0.0001);
        //System.out.println("Danny new final balance is: " + actualResult);
    }

    @And("The balance of the receivers Revolut account should now be {double}")
    public void theBalanceOfTheReceiversRevolutAccountShouldNowBeRxNewBalance(double rxNewBalance) {
        //Arrange
        double expectedResult = rxNewBalance;
        //Act
        double actualResult = rx.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0);
        //System.out.println("The new rx balance is: " + actualResult);
    }

    @Given("Danny has a balance of {double} euro in his Revolut account")
    public void dannyHasABalanceOfEuroInHisRevolutAccount(double dannyStartingBalance) {
        danny.setAccountBalance(dannyStartingBalance);
    }

    @Then("The transfer should be cancelled")
    public void theTransferShouldBeCancelled() {
        //Arrange
        boolean expectedResult = true;
        //Act
        boolean actualResult = danny.isTransferCancelled();
        //Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    @And("The balance of Danny's Revolut account should remain {double}")
    public void theBalanceOfDannySRevolutAccountShouldRemainDannyStartingBalance(double dannyStartBalance) {
        //Arrange
        double expectedResult = dannyStartBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0);
        //System.out.println("Danny new final balance is: " + actualResult);
    }

    @And("The balance of the receivers Revolut account should remain {double}")
    public void theBalanceOfTheReceiversRevolutAccountShouldRemainRxStartingBalance(double rxStartBalance) {
        //Arrange
        double expectedResult = rxStartBalance;
        //Act
        double actualResult = rx.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0);
        //System.out.println("The new rx balance is: " + actualResult);
    }

}
