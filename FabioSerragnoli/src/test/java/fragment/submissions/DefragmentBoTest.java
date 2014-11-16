package fragment.submissions;

import static fragment.submissions.Parameters.O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.CandidateFactory;
import fragment.submissions.FabioSerragnoli.CandidateHandlerFactory;
import fragment.submissions.FabioSerragnoli.DefragmentBO;
import fragment.submissions.FabioSerragnoli.DefragmentedTextBuffer;
import fragment.submissions.FabioSerragnoli.Fragment;

public class DefragmentBoTest {

	private DefragmentBO defragmentBO;
	private CandidateFactory factory;
	private CandidateHandlerFactory handlerFactory;

	@Before
	public void
	setup() {
		factory = mock(CandidateFactory.class);
		handlerFactory = mock(CandidateHandlerFactory.class);
		defragmentBO = new DefragmentBO(factory, handlerFactory);
	}
	
	@Test public void 
	should_invoke_candidate_factory() {
		defragmentBO.defragment(anySetOf(Fragment.class));
		
		verify(factory).createCandidates(anySetOf(Fragment.class));
	}
	
	@Test public void 
	should_return_defragmented_text() {
		DefragmentedTextBuffer text = defragmentBO.defragment(O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS);
		
		assertThat(text, is(notNullValue()));
	}
	
	@Test public void 
	should_defragment_two_distinct_fragments() {
		DefragmentedTextBuffer text = defragmentBO.defragment(O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS);
		
		Iterator<Fragment> it = O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS.iterator();
		assertThat(text.value(), is(it.next().value() + it.next().value()));
	}

	@Test public void 
	should_merge_two_overlapping_pieces_of_text() {
		DefragmentedTextBuffer text = defragmentBO.defragment(O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS);
		
		assertThat(text.value(), is("O draconian devil! Oh la"));
	}
}
