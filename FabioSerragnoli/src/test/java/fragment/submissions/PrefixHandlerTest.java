package fragment.submissions;

import static fragment.submissions.FabioSerragnoli.Orientation.APPEND_TO_BEGINNING;
import static fragment.submissions.Parameters.CONIAN_DEVIL;
import static fragment.submissions.Parameters.O_DRACONIA;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Candidate;
import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.PrefixHandler;

public class PrefixHandlerTest {
	
	private PrefixHandler handler;
	private Candidate candidate;

	@Before public void 
	setup() {
		handler = new PrefixHandler();
	}
	
	@Test public void 
	should_append_as_prefix() {
		handler.process(CONIAN_DEVIL, createCandidates(O_DRACONIA));
		
		assertThat(candidate.orientation(), is(APPEND_TO_BEGINNING));
	}
	
	@Test public void 
	should_have_score_of_five() {
		handler.process(CONIAN_DEVIL, createCandidates(O_DRACONIA));
		
		assertThat(candidate.score().value(), is(5);
	}
	
	private List<Candidate> createCandidates(Fragment fragment) {
		candidate = new Candidate(fragment);
		return asList(candidate);
	}
}
