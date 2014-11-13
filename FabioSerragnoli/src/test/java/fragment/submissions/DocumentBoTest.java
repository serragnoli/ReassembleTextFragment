package fragment.submissions;

import static fragment.submissions.Parameters.TWO_DISTINCT_FRAGMENTS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.TrackerFactory;
import fragment.submissions.FabioSerragnoli.Document;
import fragment.submissions.FabioSerragnoli.DocumentBO;

public class DocumentBoTest {
	
	private DocumentBO documentBO;
	private TrackerFactory candidateFactory;
	
	@Before
	public void
	setup() {
		candidateFactory = mock(TrackerFactory.class);
		documentBO = new DocumentBO(candidateFactory);
	}
	
	@Test public void 
	should_return_document() {
		Document document = documentBO.defragment(TWO_DISTINCT_FRAGMENTS);
		
		assertThat(document, is(notNullValue()));
	}
}
