Feature: Start a game
    As a User,
    I want to start a game,
    In order to play Lingo

Scenario: Start new game
    When I click on start game
    Then A new game will be started with "5" letters
    And I should see the first letter
    And the first round has started