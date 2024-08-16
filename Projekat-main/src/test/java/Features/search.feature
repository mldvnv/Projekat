Feature: Search

  @Smoke
  Scenario Outline: Search
  User should be able to see entries that correspond to chosen parameters

    Given Im on booking page
    When I enter location "<location>" in search field
    And choose duration of stay "<checkIn>" "<checkOut>"
    And choose number of guests "<numberOfAdults>", and rooms"<numberOfRooms>"
    And I click on search button
    Then appropriate results should be displayed

    Examples:

      | location | checkIn    | checkOut   | numberOfAdults | numberOfRooms |
      | Kopaonik | 2024-08-14 | 2024-08-25 | 2              | 1             |
      | Istanbul | 2024-09-12 | 2024-09-22 | 4              | 2             |
      | Prague   | 2024-08-12 | 2024-08-17 | 5              | 3             |
      | Catania  | 2024-08-05 | 2024-08-19 | 8              | 5             |
      | Florence | 2024-08-19 | 2024-08-24 | 6              | 5             |
