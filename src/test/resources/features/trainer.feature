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
    And the last word had "<previous length"> letters
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
    When I submit my "<guess>"
    And The correct word to guess is "<word>"
    Then I want to receive "<feedback>"

    Examples:
    | guess     | word      | feedback  |
    | Apple     | Draak     | P,A,A,A,A |
    | Kraak     | Draak     | A,C,C,C,C |
    | Draak     | Draak     | C,C,C,C,C |

