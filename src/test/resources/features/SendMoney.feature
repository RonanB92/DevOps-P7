Feature: Send Money
  This feature describes various scenarios for users sending money to other Revolut account holders
  
  #As a user, I can send money from my Revolut account to other Revolut account holders
  
  Scenario Outline: Money transfer to another Revolut account holder accepted
    Given Danny has a balance of <dannyStartBalance> euro in his Revolut account
    And Danny selects <sendAmount> euro as the amount to send
    And Danny sends the money to a valid Revolut user
    And The receiver has a starting balance of <rxStartBalance>
    When Danny sends money
    Then The new balance of his Revolut account should now be <dannyNewBalance>
    And The balance of the receivers Revolut account should now be <rxNewBalance>
    Examples:
      | dannyStartBalance | sendAmount  | dannyNewBalance  | rxStartBalance | rxNewBalance |
      | 100               | 50          | 50               | 30             | 80           |
      | 20                | 20          | 0                | 120            | 140          |
      | 50                | 30          | 20               | 70             | 100          |
    
  
  Scenario: Money transfer to another Revolut account holder rejected
    Given Danny has a balance of 10 euro in his Revolut account
    And Danny selects 50 euro as the amount to send
    And Danny sends the money to a valid Revolut user
    When Danny sends money
    Then The transfer should be cancelled
    And The balance of Danny's Revolut account should remain unchanged
    And The balance of the receivers Revolut account should remain unchanged
