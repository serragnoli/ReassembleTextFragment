package fragment.submissions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fragment.submissions.FabioSerragnoli.FragmentBO;
import fragment.submissions.FabioSerragnoli.ReassembleFragments;


public class ReassembleStepDefinition {

	private String textFragments;
	private ReassembleFragments reassembleFragments;
	
	@Test public void 
	setup() {
		FragmentBO fragmentBO = new FragmentBO();
		reassembleFragments = new ReassembleFragments(fragmentBO);
	}

	@Given("^the contents of the file \"(.*?)\" contain fragments of text$") public void 
	the_contents_of_the_file_contain_in(String fragments) {
	    this.textFragments = fragments;
	}

	@When("^it is reassembled$") public void 
	reassemble() {
	    reassembleFragments.reassemble(textFragments);
	}

	@Then("^it should print$") public void 
	the_line_printed_should_as(String expected) {
		String result = "";
		assertThat(result, is(expected));
	}
}
