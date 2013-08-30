Feature: Google page test
  Background:
    Given I open Google Search homepage

  Scenario: Perform a normal search. Search for "Rabbit" on Google's search page
    When I search for "Rabbit"
    Then I see 10 search results
    When I click on the search result number 2 from the list
    Then the title of search result contains the term "Rabbit"

  Scenario: Perform a search with no results. Search for "abauowdaudwawdiuad" on Google's search page
    When I search for "abauowdaudwawdiuad"
    Then I see no search results
    Then I see the following message on the search result page: "No results containing all your search terms were found."

  Scenario: Perform a search for "Computers". Paginate through the results.
    When I search for "Computers"
    Then I am on page 1 of the search results
    When I navigate to page 3 of the search results
    Then I am on page 3 of the search results
