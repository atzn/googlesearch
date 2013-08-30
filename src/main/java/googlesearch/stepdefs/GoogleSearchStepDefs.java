package googlesearch.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import googlesearch.pages.GooglePage;
import org.junit.Assert;
import org.hamcrest.core.StringContains;

public class GoogleSearchStepDefs {

    private GooglePage googlePage = new GooglePage(new SharedDriver());

    @Given("I open Google Search homepage")
    public void openGoogleSearchHomePage() {
        googlePage.openHomepage();
    }

    @When("I search for \"([^\"]*)\"")
    public void searchFor(String searchTerm) {
        googlePage.searchFor(searchTerm);
    }

    @When("I clear the search text box")
    public void clearSearchTextBox() {
        googlePage.clearSearchTextBox();
    }

    @Then("the search text box is empty")
    public void verifySearchTextBoxEmpty() {
        Assert.assertEquals("", googlePage.getSearchInputTextBox().getText());

    }

    @Then("I see (\\d+) search results")
    public void verifyNumberOfResults(int searchResultSize) {
        Assert.assertEquals(searchResultSize, googlePage.getSearchResults().size());
    }

    @Then("I see no search results")
    public void verifyNoSearchResults() {
        Assert.assertEquals(0, googlePage.getSearchResults().size());
    }

    @Then("I see the following message on the search result page: \"([^\"]*)\"")
    public void verifySearchMessageResult(String message) {
        Assert.assertEquals(message, googlePage.getSearchResultMessage());
    }

    @When("I click on the search result number (\\d+) from the list")
    public void clickOnSearchResultLink(int number) {
        googlePage.goToSearchResult(number);
    }

    @Then("the title of search result contains the term \"([^\"]*)\"")
    public void verifySearchResultLoaded(String searchTerm) {
        Assert.assertThat(googlePage.getPageTitle(), StringContains.containsString(searchTerm));
    }

    @When("I navigate to page (\\d+) of the search results")
    public void navigateToPage(int pageNumber) {
        googlePage.navigateToPage(pageNumber);
    }

    @Then("I am on page (\\d+) of the search results")
    public void verifyCurrentPage(int pageNumber) {
        Assert.assertEquals(pageNumber, googlePage.getCurrentPageNumber());
    }
}
