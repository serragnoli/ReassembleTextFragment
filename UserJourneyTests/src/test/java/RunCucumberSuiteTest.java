

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber-reports"})
public class RunCucumberSuiteTest {
  //Documented now :)
}
