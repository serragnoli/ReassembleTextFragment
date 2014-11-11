package fragment.submissions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReassembleStepDefinition {

	@Given("^the contents of the file \"(.*?)\" contain fragments of text$")
	public void the_contents_of_the_file_contain_fragments_of_text(String fragment) throws Throwable {
	    throw new PendingException();
	}

	@When("^it is reassembled$")
	public void it_is_reassembled() throws Throwable {
	    throw new PendingException();
	}

	@Then("^the following line should be printed$")
	public void the_following_line_should_be_printed(String arg1) throws Throwable {
	    throw new PendingException();
	}
}
