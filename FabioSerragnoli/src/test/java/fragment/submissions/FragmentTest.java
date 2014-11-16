package fragment.submissions;

import static fragment.submissions.Parameters.CONIAN_DEVIL;
import static fragment.submissions.Parameters.ONE_FRAGMENT;
import static fragment.submissions.Parameters.O_DRACONIA;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Fragment;

@SuppressWarnings("boxing")
public class FragmentTest {
	
	private Fragment fragment;

	@Test public void 
	should_be_equals() {
		fragment = new Fragment(ONE_FRAGMENT);
		
		Fragment duplicated = new Fragment(ONE_FRAGMENT);
		
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
	should_acknowledge_next_character() {
		assertThat(O_DRACONIA.hasNextCharacter(), is(true));
	}
	
	@Test public void 
	should_return_false_when_last_character_is_consumed() {
		fragment = new Fragment("a");
		
		fragment.nextCharacter();
		
		assertThat(fragment.hasNextCharacter(), is(false));
	}
	
	@Test public void 
	should_return_next_character() {
		assertThat(O_DRACONIA.nextCharacter(), is('O'));
	}
	
	@Test public void 
	should_return_first_character() {
		assertThat(CONIAN_DEVIL.firstCharacter(), is('c'));
	}
}
