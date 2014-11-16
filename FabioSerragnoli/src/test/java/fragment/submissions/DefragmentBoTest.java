package fragment.submissions;

import static fragment.submissions.Parameters.O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fragment.submissions.FabioSerragnoli.Candidate;
import fragment.submissions.FabioSerragnoli.CandidateFactory;
import fragment.submissions.FabioSerragnoli.CandidateHandler;
import fragment.submissions.FabioSerragnoli.CandidateHandlerFactory;
import fragment.submissions.FabioSerragnoli.DefragmentBO;
import fragment.submissions.FabioSerragnoli.DefragmentedTextBuffer;
import fragment.submissions.FabioSerragnoli.Fragment;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.atLeastOnce;


public class DefragmentBoTest {

	private DefragmentBO defragmentBO;
	private CandidateFactory candidateFactory;
	private CandidateHandlerFactory handlerFactory;
	private CandidateHandler candidateHandler;
	private ArrayList<Candidate> listOfCandidates;

	@Before
	public void
	setup() {
		listOfCandidates = new ArrayList<Candidate>();
		candidateFactory = mock(CandidateFactory.class);
		candidateHandler = mock(CandidateHandler.class);
		handlerFactory = mock(CandidateHandlerFactory.class);
		defragmentBO = new DefragmentBO(candidateFactory, handlerFactory);
		
		when(handlerFactory.createHandlers()).thenReturn(candidateHandler);
		when(candidateFactory.createCandidates(anySetOf(Fragment.class))).thenReturn(listOfCandidates);
	}
	
	@Test public void 
	should_invoke_candidate_factory() {
		defragmentBO.defragment(anySetOf(Fragment.class));
		
		verify(candidateFactory).createCandidates(anySetOf(Fragment.class));
	}
	
	@Test public void 
	should_return_defragmented_text() {
		DefragmentedTextBuffer text = defragmentBO.defragment(O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS);
		
		assertThat(text, is(notNullValue()));
	}
	
	@Test public void 
	should_defragment_invoke_handler() {
		defragmentBO.defragment(O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS);
		
		verify(candidateHandler, atLeastOnce()).process(any(Fragment.class), anyListOf(Candidate.class));
	}

	@Test public void 
	should_merge_two_overlapping_pieces_of_text() {
		DefragmentedTextBuffer text = defragmentBO.defragment(O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS);
		
		assertThat(text.value(), is("O draconian devil! Oh la"));
	}
}
