package googlesearch;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(glue = {"googlesearch.stepdefs"}, features = {"src/test/resources/googletest.feature"}, format = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber-report.json"})
public class GoogleTest {
}


