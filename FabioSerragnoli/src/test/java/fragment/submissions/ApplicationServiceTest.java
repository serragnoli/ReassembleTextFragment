package fragment.submissions;

import static fragment.submissions.Parameters.O_DRACONIAN_COMPLETE_TEXT;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.DefragmentBO;
import fragment.submissions.FabioSerragnoli.DefragmentedText;
import fragment.submissions.FabioSerragnoli.DocumentBO;
import fragment.submissions.FabioSerragnoli.FragmentBO;
import fragment.submissions.FabioSerragnoli.Fragments;
import fragment.submissions.FabioSerragnoli.ReassembleFragments;

public class ApplicationServiceTest {
	private FragmentBO fragmentBO;
	private DefragmentBO defragmentBO;
	private DocumentBO documentBO;
	private ReassembleFragments reassembleFragments;

	@Before public void
	setup() {
		fragmentBO = mock(FragmentBO.class);
		defragmentBO = mock(DefragmentBO.class);
		documentBO = mock(DocumentBO.class);
		reassembleFragments = new ReassembleFragments(fragmentBO, defragmentBO, documentBO);
		
		reassembleFragments.reassemble(O_DRACONIAN_COMPLETE_TEXT);
	}
	
	@Test public void 
	should_invoke_fragment_domain_concept() {
		verify(fragmentBO).extractFrom(O_DRACONIAN_COMPLETE_TEXT);
	}
	
	@Test public void 
	should_invoke_defragment_domain_concept() {
		verify(defragmentBO).defragment(any(Fragments.class));
	}
	
	@Test public void 
	should_invoke_document_domain_concept() {
		verify(documentBO).create(any(DefragmentedText.class));
	}
}
