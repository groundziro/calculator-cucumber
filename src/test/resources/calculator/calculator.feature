Feature: Integer Arithmetic Expressions
  This feature provides a range of scenarios corresponding to the
  intended external behaviour of arithmetic expressions on integers.

  # This is just a comment.
  # You can start with a Background: that will be run before executing each scenario.

  Background:
    Given I initialise a calculator

  # Each scenario can be seen as a test that can be executed with JUnit,
  # provided that each of the steps (Given, When, And and Then) are
  # implemented in a Java mapping file (CalculatorSteps.Java)

  Scenario: Adding two integer numbers
    Given an integer operation '+'
    When I provide a first number 4
    And I provide a second number 5
    Then the operation evaluates to 9

  Scenario: Subtracting two integer numbers
    Given an integer operation '-'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 2

  Scenario: Multiplying two integer numbers
    Given an integer operation '*'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 35

  Scenario: Dividing two integer numbers
    Given an integer operation '/'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 1

  Scenario: Compute an Equivalence
    Given a boolean operation "=="
    When I provide a bool false
    And I provide a bool false
    Then the boolean operation evaluates to true

  Scenario: Compute an And:
    Given a boolean operation "and"
    When I provide a bool true
    And I provide a bool true
    Then the boolean operation evaluates to true

  Scenario: Compute an Or:
    Given a boolean operation "or"
    When I provide a bool true
    And I provide a bool false
    Then the boolean operation evaluates to true

  Scenario: Compute an implication:
    Given a boolean operation "implies"
    When I provide a bool false
    And I provide a bool true
    Then the boolean operation evaluates to true
    But If I provide a bool true
    And I provide a bool false
    Then the boolean operation evaluates to false

  Scenario: Compute variants of Xor :
    Given a boolean operation "xor"
    When I provide a bool true
    And I provide a bool false
    Then the boolean operation evaluates to true
    But If I provide a bool true
    And I provide a bool true
    Then the boolean operation evaluates to false

  Scenario: Compute a Not:
    Given a boolean operation "not"
    When I provide a bool true
    Then the boolean operation evaluates to false
    But If I provide a bool false
    Then the boolean operation evaluates to true

  Scenario: Printing the sum of two integer numbers
    Given the sum of two numbers 8 and 6
    Then its INFIX notation is ( 8 + 6 )
    And its PREFIX notation is + ( 8 , 6 )
    And its POSTFIX notation is ( 8 , 6 ) +

  # This is an example of a scenario in which we provide a list of numbers as input.
  # (In fact, this is not entirely true, since what is given as input is a table of
  # strings. In this case, the table is of dimension 1 * 3 (1 line and three columns).
  Scenario: Evaluation arithmetic operations over a list of integer numbers
    Given the following list of integer numbers
      | 8 | 2 | 2 |
    Then the sum is 12
    And the product is 32
    And the difference is 4
    And the quotient is 2

  # A scenario outline (or template) is a scenario that is parameterised
  # with different values. The outline comes with a set of examples.
  # The scenario will be executed with each of the provided inputs.
  Scenario Outline: Adding two integer numbers
    Given an integer operation '+'
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      |n1|n2|result|
      |4|5|9|
      |5|3|8|

  Scenario Outline: Dividing two integer numbers
    Given an integer operation '/'
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      |n1|n2|result|
      |35|5|7|
      |7|5|1|
      |5|7|0|

  Scenario Outline: Evaluating arithmetic operations with two integer parameters
    Given an integer operation <op>
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      | op  |n1|n2|result|
      | "+" | 4| 5|     9|
      | "-" | 8| 5|     3|
      | "*" | 7| 2|    14|
      | "/" | 6| 2|     3|

  Scenario Outline: Evaluating boolean operations with two bool parameters
    Given a boolean operation <op>
    When I provide a bool <b1>
    And I provide a bool <b2>
    Then the boolean operation evaluates to <result>

    Examples:
      |  op     |  b1 |  b2 |result|
      |  "and"  |true |true |true  |
      |  "and"  |false|true |false |
      |  "or"   |false|false|false |
      |  "or"   |true |false|true  |
      |"implies"|false|false|true  |
      |"implies"|true |false|false |
      |  "=="   |true |false|false |
      |  "=="   |false|false|true  |

  Scenario Outline: Evaluating all the variants for not
    Given a boolean operation "not"
    When I provide a bool <b>
    Then the boolean operation evaluates to <result>

    Examples:
      |b    |result|
      |true |false |
      |false|true  |