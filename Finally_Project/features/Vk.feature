Feature: Test vk.ru page

  Scenario: Test Post Message
    Given I created Post URL "message", "Test text"
    Then I see Response Ok
    Then I see Message on the wall

  Scenario: Test Edit Message
    Given I created edit URL "message", "New test text"
    Then I see Response Ok
    Then I see Message with new text "New test text"

  Scenario: Test delete Message
    Given I created delete URL
    Then I see delete Response Ok
    Then I not see Message on the wall