package fragment.submissions;

import static fragment.submissions.Parameters.O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.HandlersChain;
import fragment.submissions.FabioSerragnoli.DefragmentBO;
import fragment.submissions.FabioSerragnoli.DefragmentedText;
import fragment.submissions.FabioSerragnoli.HandlersFactory;


public class DefragmentBoTest {

	private DefragmentBO defragmentBO;
	private HandlersFactory handlerFactory;
	private HandlersChain candidateHandler;

	@Before
	public void
	setup() {
		candidateHandler = mock(HandlersChain.class);
		handlerFactory = mock(HandlersFactory.class);
		defragmentBO = new DefragmentBO(handlerFactory);
		
		when(handlerFactory.createHandlers()).thenReturn(candidateHandler);
	}
	
	@Test public void 
	should_return_defragmented_text() {
		DefragmentedText text = defragmentBO.defragment(O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS);
		
		assertThat(text, is(notNullValue()));
	}
	
	@Ignore("This test doesn't belong here")
	@Test public void 
	should_merge_two_overlapping_pieces_of_text() {
		DefragmentedText text = defragmentBO.defragment(O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS);
		
		assertThat(text.value(), is("O draconian devil! Oh la"));
	}
}
