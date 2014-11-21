package fragment.submissions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.DefragmentBO;
import fragment.submissions.FabioSerragnoli.Fragments;
import fragment.submissions.FabioSerragnoli.HandlersChain;
import fragment.submissions.FabioSerragnoli.HandlersFactory;


public class DefragmentBoTest {

	private DefragmentBO defragmentBO;
	private HandlersFactory handlerFactory;
	private HandlersChain candidateHandler;
	private Fragments fragments;

	@Before
	public void
	setup() {
		candidateHandler = mock(HandlersChain.class);
		fragments = mock(Fragments.class);
		handlerFactory = mock(HandlersFactory.class);
		defragmentBO = new DefragmentBO(handlerFactory);
		
		when(handlerFactory.createHandlers()).thenReturn(candidateHandler);
	}
	
	@Test public void 
	should_invoke_handler_factory() {
		defragmentBO.defragment(fragments);
		
		verify(handlerFactory).createHandlers();
	}
	
	@Test public void 
	should_invoke_fragments() {
		defragmentBO.defragment(fragments);
		
		verify(fragments).defragmentWith(candidateHandler);
	}
}
