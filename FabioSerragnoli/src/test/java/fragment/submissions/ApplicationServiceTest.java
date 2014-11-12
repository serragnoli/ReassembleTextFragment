package fragment.submissions;

import static fragment.submissions.Parameters.VALID_FRAGMENTS;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fragment.submissions.FabioSerragnoli.DefragmentBO;
import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.FragmentBO;
import fragment.submissions.FabioSerragnoli.ReassembleFragments;

public class ApplicationServiceTest {
	private FragmentBO fragmentBO = Mockito.mock(FragmentBO.class);
	private DefragmentBO defragmentBO = mock(DefragmentBO.class);
	private ReassembleFragments reassembleFragments;

	@Before public void
	setup() {
		reassembleFragments = new ReassembleFragments(fragmentBO, defragmentBO);
		
		reassembleFragments.reassemble(VALID_FRAGMENTS);
	}
	
	@Test public void 
	should_invoke_fragment_domain_concept() {
		verify(fragmentBO).extractFrom(VALID_FRAGMENTS);
	}
	
	@Test public void 
	should_invoke_defragment_concept() {
		verify(defragmentBO).defragment(anySetOf(Fragment.class));
	}
}
