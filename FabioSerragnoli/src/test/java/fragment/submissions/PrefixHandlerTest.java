package fragment.submissions;

import static fragment.submissions.FabioSerragnoli.Orientation.APPEND_TO_BEGINNING;
import static fragment.submissions.Parameters.CONIAN_DEVIL;
import static fragment.submissions.Parameters.H_LAME_SA;
import static fragment.submissions.Parameters.O_DRACONIA;
import static fragment.submissions.Parameters.SAINT;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Candidate;
import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.PrefixHandler;
import fragment.submissions.FabioSerragnoli.Score;

@SuppressWarnings("boxing")
public class PrefixHandlerTest {
	
	private PrefixHandler handler;
	private Candidate candidate;

	@Before public void 
	setup() {
		O_DRACONIA.reset();
		CONIAN_DEVIL.reset();
		H_LAME_SA.reset();
		SAINT.reset();
		handler = new PrefixHandler();
	}
	
	@Test public void 
	should_register_as_APPEND_TO_BEGINNING_for_CONAN_DEVIL_as_base() {
		handler.process(CONIAN_DEVIL, createCandidates(O_DRACONIA));
		
		assertThat(candidate.orientation(), is(APPEND_TO_BEGINNING));
	}
	
	@Test public void 
	should_have_score_of_five_for_CONIAN_DEVIL_as_base() {
		handler.process(CONIAN_DEVIL, createCandidates(O_DRACONIA));
		
		Score score = candidate.score();
		
		assertThat(score.value(), is(5));
	}
	
	@Test public void 
	should_have_score_of_one_for_O_DRACONIA_as_base() {
		handler.process(O_DRACONIA, createCandidates(CONIAN_DEVIL));
		
		Score score = candidate.score();
		
		assertThat(score.value(), is(1));
	}
	
	@Test public void 
	should_have_score_of_four_for_H_LAME_SA_as_base() {
		handler.process(H_LAME_SA, createCandidates(CONIAN_DEVIL));
		
		Score score = candidate.score();
		
		assertThat(score.value(), is(4));
	}
	
	@Test public void 
	should_have_score_of_zero_for_CONIAN_DEVIL_as_base() {
		handler.process(CONIAN_DEVIL, createCandidates(H_LAME_SA));
		
		Score score = candidate.score();
		
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_have_score_of_zero_for_H_LAME_SA_as_base() {
		handler.process(H_LAME_SA, createCandidates(SAINT));
		
		Score score = candidate.score();
		
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_have_score_of_two_for_SAINT_as_base() {
		handler.process(SAINT, createCandidates(H_LAME_SA));
		
		Score score = candidate.score();
		
		assertThat(score.value(), is(2));
	}
	
	private List<Candidate> createCandidates(Fragment fragment) {
		candidate = new Candidate(fragment);
		return asList(candidate);
	}
}









