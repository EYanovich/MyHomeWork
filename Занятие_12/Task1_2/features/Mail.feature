Feature: Test Mail.ru page

  Scenario: Test select mail and move to spam
    Given I am on main application page
    When I login as correct user
    When I select mail
    When I move mail to spam
    When I go to spam
    Then I see mail in spam

  Scenario: Test select mail spam and return mail inbox
    Given I am on main application page
    When I login as correct user
    When I go to spam
    When I select mail
    When I move mail to inbox
    When I go to inbox
    Then I see mail in inbox

  Scenario: Test create new mail and send group
    Given I am on main application page
    When I login as correct user
    When I click button new mail
    When I select "Group"
    When I field theme "Test"
    When I field text mail "Test text"
    When I click send button
    Then I see text "Письмо отправлено"

  Scenario: Test put flag on mail by id
    Given I am on main application page
    When I login as correct user
    When I put flag on mail by index "0"
    When I put flag on mail by index "5"
    When I put flag on mail by index "2"
    Then I see flag on mail

  Scenario: Test take off put flag on mail by id
    Given I am on main application page
    When I login as correct user
    When I put flag on mail by index "1"
    When I put flag on mail by index "3"
    When I put flag on mail by index "4"
    When I take off flag on all mail
    Then I not see flag on mail

