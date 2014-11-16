package fragment.submissions;

import static fragment.submissions.Parameters.O_DRACONIA;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Candidate;
import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.Orientation;

@SuppressWarnings("boxing")
public class CandidateTest {

	private Candidate candidate;

	@Before public void
	setup() {		
		candidate = new Candidate(O_DRACONIA);
	}
	
	@Test public void 
	should_record_fragment() {
		assertThat(candidate.current(), is(O_DRACONIA));
	}

	@Test public void 
	should_acknowledge_next_character() {
		assertThat(candidate.hasNextCharacter(), is(true));
	}
	
	@Test public void 
	should_return_false_when_current_is_null() {
		candidate = new Candidate(null);
		
		assertThat(candidate.hasNextCharacter(), is(false));
	}
	
	@Test public void 
	should_return_false_when_last_character_is_consumed() {
		candidate = new Candidate(new Fragment("A"));
		
		candidate.nextCharacter();
		
		assertThat(candidate.hasNextCharacter(), is(false));
	}
	
	@Test(expected = IllegalStateException.class) public void 
	should_throw_exception_when_next_on_null() {
		candidate = new Candidate(null);
		
		candidate.nextCharacter();
	}
	
	@Test public void 
	should_register_append_to_the_beginning_of_the_text() {
		candidate.appendToBeginning();
		
		assertThat(candidate.orientation(), is(Orientation.APPEND_TO_BEGINNING));
	}
	
	@Test public void 
	should_register_append_to_end_of_the_text() {
		candidate.appendToEnd();
		
		assertThat(candidate.orientation(), is(Orientation.APPEND_TO_END));
	}
}
