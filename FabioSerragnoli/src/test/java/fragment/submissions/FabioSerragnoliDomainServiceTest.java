package fragment.submissions;

import static fragment.submissions.Parameters.VALID_FRAGMENTS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.FragmentBO;

@SuppressWarnings("boxing")
public class FabioSerragnoliDomainServiceTest {
	
	@Test public void 
	should_wrap_fragments() {
		FragmentBO fragmentBO = new FragmentBO();
		
		Set<Fragment> fragments = fragmentBO.extractFrom(VALID_FRAGMENTS);
		
		assertThat(fragments.size(), is(4));
	}
}
