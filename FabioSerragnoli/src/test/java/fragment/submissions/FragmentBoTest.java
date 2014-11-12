package fragment.submissions;

import static fragment.submissions.Parameters.DUPLICATED_FRAGMENTS;
import static fragment.submissions.Parameters.VALID_FRAGMENTS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.FragmentBO;

@SuppressWarnings("boxing")
public class FragmentBoTest {
	private FragmentBO fragmentBO;
	
	@Before public void
	setup() {
		fragmentBO = new FragmentBO();
	}
	
	@Test public void 
	should_wrap_fragments() {
		Set<Fragment> fragments = fragmentBO.extractFrom(VALID_FRAGMENTS);
		
		assertThat(fragments.size(), is(4));
	}
	
	@Test public void 
	should_ignore_duplicated_fragments() {
		Set<Fragment> fragments = fragmentBO.extractFrom(DUPLICATED_FRAGMENTS);
		
		assertThat(fragments.size(), is(4));
	}
}
