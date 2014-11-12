package fragment.submissions;

import static fragment.submissions.Parameters.VALID_FRAGMENTS;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;

import fragment.submissions.FabioSerragnoli.FragmentBO;
import fragment.submissions.FabioSerragnoli.ReassembleFragments;

public class FabioSerragnoliApplicationServiceTest {
	private FragmentBO fragmentBO = Mockito.mock(FragmentBO.class);

	@Test public void 
	should_invoke_fragment_domain_concept() {
		ReassembleFragments reassembleFragments = new ReassembleFragments(fragmentBO);
		
		reassembleFragments.reassemble(VALID_FRAGMENTS);
		
		verify(fragmentBO).extractFrom(VALID_FRAGMENTS);
	}
}
