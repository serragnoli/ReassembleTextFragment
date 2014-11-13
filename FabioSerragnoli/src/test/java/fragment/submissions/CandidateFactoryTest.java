package fragment.submissions;

import static fragment.submissions.Parameters.TWO_OVERLAPPING_FRAGMENTS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Tracker;
import fragment.submissions.FabioSerragnoli.TrackerFactory;
import fragment.submissions.FabioSerragnoli.Fragment;

@SuppressWarnings("boxing")
public class CandidateFactoryTest {

	private TrackerFactory factory;
	
	@Before public void
	setup() {
		factory = new TrackerFactory();		
	}

	@Test public void 
	should_create_empty_collection_when_fragments_are_null() {
		List<Tracker> candidates = factory.createTrackers(null);
		
		assertTrue(candidates.isEmpty());
	}
	
	@Test public void 
	should_create_empty_collection_when_fragments_are_empty() {
		List<Tracker> candidates = factory.createTrackers(new HashSet<Fragment>());
		
		assertTrue(candidates.isEmpty());
	}
	
	@Test public void 
	should_create_collection_of_candidates_with_the_same_length_as_the_fragments_collection() {
		List<Tracker> candidates = factory.createTrackers(TWO_OVERLAPPING_FRAGMENTS);
		
		assertThat(candidates.size(), is(TWO_OVERLAPPING_FRAGMENTS.size()));
	}
}
