Feature: Start a game
    As a User,
    I want to start a game,
    In order to play Lingo

Scenario: Start new game
    When I click on start game
    Then A new game will be started with "5" letters
    And I should see the first letter
    And the first round has started

Scenario Outline: Start a new round
    Given I am playing a game
    And the round was won
    And the last word had "<previous length>" letters
    When I start a new round
    Then the word to guess has "<next length>" letters

    Examples:
    | previous length | next length |
    | 5             | 6             |
    | 6             | 7             |
    |7              | 5             |

    # Failure path
    Given I am playing a game
    And the round was lost
    Then I cannot start a new round

Scenario Outline: Guessing a word
    Given I am playing a game
    And a "<word>" has been selected
    When I submit my "<guess>"
    Then I want to receive "<feedback>"

    Examples:
    | guess     | word      | feedback  |
    | Dak       | Draak     | INVALID, INVALID, INVALID |
    | eeeee     | Draak     | ABSENT, ABSENT, ABSENT, ABSENT, ABSENT |
    | Apple     | Draak     | PRESENT, ABSENT, ABSENT, ABSENT, ABSENT |
    | Kraak     | Draak     | ABSENT, CORRECT, CORRECT, CORRECT, CORRECT |
    | Draak     | Draak     | CORRECT, CORRECT, CORRECT, CORRECT, CORRECT |

