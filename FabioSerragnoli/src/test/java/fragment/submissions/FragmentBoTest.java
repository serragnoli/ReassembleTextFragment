package fragment.submissions;

import static fragment.submissions.Parameters.O_DRACONIAN_COMPLETE_TEXT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.FragmentBO;
import fragment.submissions.FabioSerragnoli.Fragments;

@SuppressWarnings("boxing")
public class FragmentBoTest {
	private FragmentBO fragmentBO;
	
	@Before public void
	setup() {
		fragmentBO = new FragmentBO();
	}
	
	@Test public void 
	should_wrap_fragments() {
		Fragments fragments = fragmentBO.extractFrom(O_DRACONIAN_COMPLETE_TEXT);
		
		assertThat(fragments.size(), is(4));
	}
}
