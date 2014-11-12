package fragment.submissions;

import static fragment.submissions.Parameters.TWO_DISTINCT_FRAGMENTS;
import static fragment.submissions.Parameters.TWO_IDENTICAL_FRAGMENTS;
import static fragment.submissions.Parameters.TWO_OVERLAPPING_FRAGMENTS;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.DefragmentBO;
import fragment.submissions.FabioSerragnoli.Fragment;

public class DefragmentBoTest {
	
	private DefragmentBO defragmentBO;

	@Before
	public void
	setup() {
		defragmentBO = new DefragmentBO();
	}
		
	@Test public void 
	should_defragment_two_identical_pieces_of_texts() {		
		String reassembled = defragmentBO.defragment(TWO_IDENTICAL_FRAGMENTS);
		
		assertThat(reassembled, is(TWO_IDENTICAL_FRAGMENTS.iterator().next().value()));
	}
	
	@Test public void 
	should_defragment_two_distinct_pieces_of_text() {
		String reassembled = defragmentBO.defragment(TWO_DISTINCT_FRAGMENTS);
		
		Iterator<Fragment> it = TWO_DISTINCT_FRAGMENTS.iterator();
		assertThat(reassembled, is(it.next().value() + it.next().value()));
	}
	
	@Test public void 
	should_merge_two_overlapping_pieces_of_text() {
		String reassembled = defragmentBO.defragment(TWO_OVERLAPPING_FRAGMENTS);
		
		assertThat(reassembled, is("O draconian devil! Oh la"));
	}
}
