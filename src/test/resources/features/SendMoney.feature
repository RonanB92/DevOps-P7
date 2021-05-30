Feature: Send Money
  This feature describes various scenarios for users sending money to other Revolut account holders
  
  #As a user, I can send money from my Revolut account to other Revolut account holders
  
  Scenario Outline: Money transfer to another Revolut account holder accepted
    Given Danny has a balance of <dannyStartBalance> euro in his Revolut account
    And Danny selects <transferAmount> euro as the amount to send
    And The receiver has a starting balance of <rxStartBalance>
    When Danny sends money
    Then The new balance of his Revolut account should now be <dannyNewBalance>
    And The balance of the receivers Revolut account should now be <rxNewBalance>
    Examples:
      | dannyStartBalance | transferAmount  | dannyNewBalance  | rxStartBalance | rxNewBalance |
      | 100.01            | 100.01      | 0.0              | 30.0           | 130.01       |
      | 20.00             | 19.99       | 0.01             | 120.0          | 139.99       |
      | 50.0              | 30.0        | 20.0             | 70.0           | 100.0        |


  Scenario Outline: Money transfer to another Revolut account holder rejected
    Given Danny has a balance of <dannyStartBalance> euro in his Revolut account
    And Danny selects <transferAmount> euro as the amount to send
    And The receiver has a starting balance of <rxStartBalance>
    When Danny sends money
    Then The transfer should be cancelled
    And The balance of Danny's Revolut account should remain <dannyStartBalance>
    And The balance of the receivers Revolut account should remain <rxStartBalance>
    Examples:
      | dannyStartBalance | rxStartBalance | transferAmount |
      | 10.0              | 50.05          | 50.50          |
      | 0.0               | 60.01          | 0.01           |
      | 100.05            | 100.00         | 100.06         |
