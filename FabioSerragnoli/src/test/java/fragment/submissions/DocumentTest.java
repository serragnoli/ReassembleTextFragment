package fragment.submissions;

import static fragment.submissions.Parameters.TWO_DISTINCT_FRAGMENTS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Document;
import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.TrackerFactory;

public class DocumentTest {

	private Document document;
	private TrackerFactory factory;
	
	@Before public void
	setup() {
		factory = mock(TrackerFactory.class);
		document = new Document(factory);
	}
	
	@Test public void 
	should_invoke_factory_when_probing() {
		document.probe(TWO_DISTINCT_FRAGMENTS);
		
		verify(factory).createTrackers(TWO_DISTINCT_FRAGMENTS);
	}
	
	@Test public void 
	should_defragment_two_distinct_fragments() {
		document.probe(TWO_DISTINCT_FRAGMENTS);
		
		Iterator<Fragment> it = TWO_DISTINCT_FRAGMENTS.iterator();
		assertThat(document.content(), is(it.next().value() + it.next().value()));
	}

	@Test public void 
	should_merge_two_overlapping_pieces_of_text() {
		document.probe(TWO_DISTINCT_FRAGMENTS);
		
		assertThat(document.content(), is("O draconian devil! Oh la"));
	}
}
