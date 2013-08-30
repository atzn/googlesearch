package googlesearch.pages;

import com.google.common.base.Predicate;
import googlesearch.stepdefs.SharedDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GooglePage {
    private WebDriver driver;
    private final int TIMEOUT = 30;
    private final String GOOGLE_URL = "http://www.google.com";

    public GooglePage(SharedDriver driver) {
        this.driver = driver;
    }

    private void webDriverWait(int timeoutInSeconds, Predicate<WebDriver> predicate) {
        new WebDriverWait(driver, timeoutInSeconds).until(predicate);
    }

    public void webDriverWait(ExpectedCondition<?> expectedCondition) {
        new WebDriverWait(driver, TIMEOUT).until(expectedCondition);
    }

    public WebElement findElementWithWait(By locator) {
        webDriverWait(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public void openHomepage() {
        this.driver.get(GOOGLE_URL);
    }

    public WebElement getSearchInputTextBox() {
        return findElementWithWait(By.id("gbqfq"));
    }

    public void clearSearchTextBox() {
        WebElement searchInputTextBox = getSearchInputTextBox();
        searchInputTextBox.clear();
    }

    public void searchFor(String searchItem) {
        WebElement searchInputTextBox = getSearchInputTextBox();
        searchInputTextBox.sendKeys(searchItem);
        WebElement searchButton = findElementWithWait(By.id("gbqfb"));
        searchButton.click();
    }

    public void goToSearchResult(int number) {
        WebElement resultsList = findElementWithWait(By.id("rso"));
        List<WebElement> individualResults = resultsList.findElements(By.cssSelector("li.g"));
        WebElement result = individualResults.get(number - 1);
        WebElement resultHeading = result.findElement(By.cssSelector("h3"));
        WebElement resultLink = resultHeading.findElement(By.cssSelector("a"));
        resultLink.click();
    }

    public List<WebElement> getSearchResults() {
        WebElement resultsList = findElementWithWait(By.id("rso"));
        return resultsList.findElements(By.cssSelector("li.g"));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getSearchResultMessage() {
        WebElement searchResultContainer = findElementWithWait(By.id("topstuff"));
        WebElement firstParagraph = searchResultContainer.findElement(By.cssSelector("p"));
        return firstParagraph.getText();
    }

    public void navigateToPage(final int pageNumber) {
        WebElement paginationTable = findElementWithWait(By.id("nav"));
        List<WebElement> pageElements = paginationTable.findElements(By.cssSelector("td"));
        for(WebElement pageElement : pageElements) {
            if(pageElement.getText().equals(String.valueOf(pageNumber))) {
                WebElement pageAnchor = pageElement.findElement(By.cssSelector("a"));
                pageAnchor.click();
                webDriverWait(10, new Predicate<WebDriver>() {
                    public boolean apply(WebDriver driver) {
                        WebElement paginationTable = findElementWithWait(By.id("nav"));
                        WebElement currentPageElement = paginationTable.findElement(By.className("cur"));
                        return pageNumber == Integer.valueOf(currentPageElement.getText());
                    }
                });
                break;
            }
        }
    }

    public int getCurrentPageNumber() {
        webDriverWait(10, new Predicate<WebDriver>() {
            public boolean apply(WebDriver driver) {
                WebElement paginationTable = findElementWithWait(By.id("nav"));
                WebElement currentPageElement = paginationTable.findElement(By.className("cur"));
                return currentPageElement.isDisplayed();
            }
        });
        WebElement paginationTable = findElementWithWait(By.id("nav"));
        WebElement currentPageElement = paginationTable.findElement(By.className("cur"));
        return Integer.valueOf(currentPageElement.getText());
    }
}
