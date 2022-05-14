Feature: User
  @User
  Scenario: Add user and verify table
    Given I open user page
    When I click Add User button
    And I add user with following data:
      | First Name | Last Name | User Name | Password   | Email Address  | Cell Phone |
      | Yatin      | Patel     | yatin822  | password01 | test@gmail.com | 2342342346 |
    Then The table contains "yatin822" user


  @User
  Scenario: Delete user and verify table
    Given I open user page
    When I delete "novak" user
    Then The table doesn't contain "novak" user
