package fragment.submissions;

import static fragment.submissions.Parameters.O_DRACONIA;
import static fragment.submissions.Parameters.CONIAN_DEVIL;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Tracker;

public class TrackerTest {

	private Tracker base;

	@Before public void
	setup() {		
		base = new Tracker(O_DRACONIA);
	}
	
	@Test public void 
	should_record_fragment() {
		assertThat(base.current(), is(O_DRACONIA));
	}

	@Test public void 
	should_become_favourite_when_it_is_the_first_to_match() {
		assertThat(base.bestMatch(), is(CONIAN_DEVIL));
	}
}
