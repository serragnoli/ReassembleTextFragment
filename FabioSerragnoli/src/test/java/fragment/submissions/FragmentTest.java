package fragment.submissions;

import static fragment.submissions.Parameters.CONIAN_DEVIL_TEXT;
import static fragment.submissions.Parameters.H_LAME_SA_TEXT;
import static fragment.submissions.Parameters.O_DRACONIA_TEXT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Fragment;

@SuppressWarnings("boxing")
public class FragmentTest {
	
	private Fragment fragment;

	@Test public void 
	should_be_equals() {
		fragment = new Fragment(O_DRACONIA_TEXT);
		
		Fragment duplicated = new Fragment(O_DRACONIA_TEXT);
		
		assertThat(fragment, is(equalTo(duplicated)));
	}
	
	@Test public void 
	best_candidate_should_score_zero_when_starts_with_only_first_letter() {
		Fragment base = new Fragment("ABCD");
		Fragment candidate = new Fragment("AXYZ");
		
		base.startsWith(candidate);
		
		assertThat(base.bestCandidate().score().value(), is(0));
	}
	
	@Test public void 
	should_score_five_when_starts_with_the_first_five_letters() {
		Fragment base = new Fragment("Fabio Serragnoli");
		Fragment candidate = new Fragment("Fabio");
		
		base.startsWith(candidate);
		
		assertThat(base.bestCandidate().score().value(), is(5));
	}
	
	@Test public void 
	should_merge_suffix_best_candidate() {
		Fragment base = new Fragment(O_DRACONIA_TEXT);
		Fragment bestCandidate = new Fragment(CONIAN_DEVIL_TEXT);
		base.endsWith(bestCandidate);
		
		base.mergeBestCandidate();
		
		assertThat(base.value(), is("O draconian devil! Oh la"));
	}
	
	@Test public void 
	should_merge_preffix_best_candidate() {
		Fragment base = new Fragment(H_LAME_SA_TEXT);
		Fragment bestCandidate = new Fragment(CONIAN_DEVIL_TEXT);
		base.startsWith(bestCandidate);
		
		base.mergeBestCandidate();
		
		assertThat(base.value(), is("conian devil! Oh lame sa"));		
	}
}