Feature: Actor

  Scenario: Actor is fetched by id
    Given the actor with id 1 exists
    When the get request is made for actor 1
    Then an actor is returned

  Scenario: Actor's films are fetched by id
    Given the actor with id 1 exists
    When the get request is made for films starred in 1
    Then a list of films is returned
