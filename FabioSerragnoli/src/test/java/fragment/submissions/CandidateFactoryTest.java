package fragment.submissions;

import static fragment.submissions.Parameters.O_DRACONIA_AND_CONIA_EVIL_OH_LA_FRAGMENTS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Candidate;
import fragment.submissions.FabioSerragnoli.CandidateFactory;
import fragment.submissions.FabioSerragnoli.Fragment;

@SuppressWarnings("boxing")
public class CandidateFactoryTest {

	private CandidateFactory factory;
	
	@Before public void
	setup() {
		factory = new CandidateFactory();		
	}

	@Test public void 
	should_create_empty_collection_when_fragments_are_null() {
		List<Candidate> candidates = factory.createCandidates(null);
		
		assertTrue(candidates.isEmpty());
	}
	
	@Test public void 
	should_create_empty_collection_when_fragments_are_empty() {
		List<Candidate> candidates = factory.createCandidates(new HashSet<Fragment>());
		
		assertTrue(candidates.isEmpty());
	}
	
	@Test public void 
	should_create_collection_of_candidates_with_the_same_length_as_the_fragments_collection() {
		List<Candidate> candidates = factory.createCandidates(O_DRACONIA_AND_CONIA_EVIL_OH_LA_FRAGMENTS);
		
		assertThat(candidates.size(), is(O_DRACONIA_AND_CONIA_EVIL_OH_LA_FRAGMENTS.size()));
	}
}
