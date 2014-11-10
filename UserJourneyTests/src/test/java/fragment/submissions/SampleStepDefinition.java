package fragment.submissions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SampleStepDefinition {

	@Given("^I Windows crashes$") public void
	windows_crashes() {
		assertThat(true, is(false));
	}
	
	@When("^I reboot the machine$") public void
	rebooted() {
		assertThat(true, is(false));
	}
	
	@Then("^Windows works again$") public void
	works_again() {
		assertThat(true, is(false));
	}
}
