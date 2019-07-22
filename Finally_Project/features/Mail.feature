Feature: Test Mail.ru page

  Scenario: Test Sign in
    Given I am on main application page
    When I login as correct user
    Then I see home URL
    Then I see my email address
    When After test

  Scenario: Test select mail and move to spam
    Given I am on main application page
    When I login as correct user
    When I select mail
    When I move mail to spam
    When I go to spam
    Then I see mail in spam
    When After test

  Scenario: Test select mail and remove
    Given I am on main application page
    When I login as correct user
    When I select mail
    When I click button remove
    When I go to recycled
    Then I see mail in recycled
    When I clear recycled
    When After test

  Scenario: Test clear spam
    Given I am on main application page
    When I login as correct user
    When I select mail
    When I move mail to spam
    When I click button clear spam
    When I go to spam
    Then I see text in spam "В вашей почте нет спама"
    When After test

  Scenario: Test Reply mail
    Given I am on main application page
    When I login as correct user
    When I open mail
    When I click button "Ответить"
    Then I see text theme "Re:"
    When After test

  Scenario: Test Save draft
    Given I am on main application page
    When I login as correct user
    When I click button new mail
    When I field send to
    When I field theme
    When I field text mail
    When I click save button
    Then I see notify message "Сохранено в черновиках"
    When I click cancel
    When I go to draft
    When I open mail
    Then I see my save mail
    When After test

  Scenario: Test select mail spam and return mail inbox
    Given I am on main application page
    When I login as correct user
    When I select mail
    When I move mail to spam
    When I go to spam
    When I select mail
    When I move mail to inbox
    When I go to inbox
    Then I see mail in inbox
    When After test

  Scenario: Test create new mail and send me
    Given I am on main application page
    When I login as correct user
    When I click button new mail
    When I fill in the xml "0" field SendTo
    When I fill in the xml "0" field theme
    When I fill in the xml "0" field text mail
    When I click send button
    Then I see text "Письмо отправлено"
    When After test

  Scenario: Test create new mail and send me
    Given I am on main application page
    When I login as correct user
    When I click button new mail
    When I fill in the xml "1" field SendTo
    When I fill in the xml "1" field theme
    When I fill in the xml "1" field text mail
    When I click send button
    Then I see text "Письмо отправлено"
    When After test

  Scenario: Test create new mail and send
    Given I am on main application page
    When I login as correct user
    When I click button new mail
    When I select Group
    When I field theme
    When I field text mail
    When I click send button
    Then I see text "Письмо отправлено"
    When After test

  Scenario: Test put flag on mail by id
    Given I am on main application page
    When I login as correct user
    When I put flag on mail by index "0"
    When I put flag on mail by index "5"
    When I put flag on mail by index "2"
    Then I see flag on mail
    When I take off flag on all mail
    When After test

  Scenario: Test take off put flag on mail by id
    Given I am on main application page
    When I login as correct user
    When I put flag on mail by index "1"
    When I put flag on mail by index "3"
    When I put flag on mail by index "4"
    When I take off flag on all mail
    Then I not see flag on mail
    When After test

