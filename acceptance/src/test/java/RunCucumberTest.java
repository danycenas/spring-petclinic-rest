

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features", 
		glue = "stepDefinitions", 
		plugin = { "pretty", "json:target/cucumber-reports/cucumber.json", "html:target/cucumber-html-report" })
public class RunCucumberTest {

}
