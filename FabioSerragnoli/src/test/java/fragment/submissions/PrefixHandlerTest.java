package fragment.submissions;

import static fragment.submissions.FabioSerragnoli.Orientation.APPEND_TO_BEGINNING;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Candidate;
import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.PrefixHandler;

public class PrefixHandlerTest {

	@Test public void 
	should_append_as_prefix() {
		PrefixHandler handler = new PrefixHandler();
		Fragment base = Parameters.CONIAN_DEVIL;
		Fragment fragment = Parameters.O_DRACONIA;
		Candidate candidate = new Candidate(fragment);
		List<Candidate> candidates = Arrays.asList(candidate);
		
		handler.process(base, candidates);
		
		assertThat(candidate.orientation(), is(APPEND_TO_BEGINNING));
	}
}
