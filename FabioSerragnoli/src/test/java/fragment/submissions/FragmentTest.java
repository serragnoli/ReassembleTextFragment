package fragment.submissions;

import static fragment.submissions.Parameters.CONIAN_DEVIL;
import static fragment.submissions.Parameters.O_DRACONIA;
import static fragment.submissions.Parameters.O_DRACONIA_TEXT;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Fragment;

@SuppressWarnings("boxing")
public class FragmentTest {
	
	private Fragment fragment;

	@Test public void 
	should_be_equals() {
		fragment = new Fragment(O_DRACONIA_TEXT);
		
		Fragment duplicated = new Fragment(O_DRACONIA_TEXT);
		
		assertThat(fragment, is(equalTo(duplicated)));
	}
	
	@Test public void 
	should_return_false_when_fragment_has_no_value() {
		fragment = new Fragment(null);
		
		assertThat(fragment.hasNextCharacter(), is(false));
	}
	
	@Test public void 
	should_return_false_when_fragment_is_empty() {
		fragment = new Fragment("");
		
		assertThat(fragment.hasNextCharacter(), is(false));
	}
	
	@Test public void 
	should_return_false_when_last_character_is_consumed() {
		fragment = new Fragment("a");
		
		fragment.nextCharacter();
		
		assertThat(fragment.hasNextCharacter(), is(false));
	}
	
	@Test public void 
	should_return_first_character() {
		assertThat(CONIAN_DEVIL.fragments().get(0).firstCharacter(), is('c'));
	}
	
	@Test public void 
	should_score_one_when_starts_with_only_first_letter() {
		Fragment base = new Fragment("ABCD");
		Fragment candidate = new Fragment("AXYZ");
		
		base.startsWith(candidate);
		
		assertThat(base.bestCandidate().score().value(), is(1));
	}
	
	@Test public void 
	should_score_five_when_starts_with_the_first_five_letters() {
		Fragment base = new Fragment("Fabio Serragnoli");
		Fragment candidate = new Fragment("Fabio");
		
		base.startsWith(candidate);
		
		assertThat(base.bestCandidate().score().value(), is(5));
	}
}














