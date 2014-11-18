package fragment.submissions;

import static fragment.submissions.Parameters.H_LAME_SA_TEXT;
import static fragment.submissions.Parameters.SAINT_TEXT;
import static fragment.submissions.Parameters.fragmentWith;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.Score;
import fragment.submissions.FabioSerragnoli.SuffixHandler;

public class SuffixHandlerTest {

	private SuffixHandler handler;
	private List<Fragment> evaluated;
	
	@Before public void
	setup() {
		handler = new SuffixHandler();
		evaluated = new ArrayList<>();
	}
	
	@Test public void 
	should_score_two_sample_1() {
		Fragment saint = fragmentWith(SAINT_TEXT);
		Fragment candidate = fragmentWith(H_LAME_SA_TEXT);
		
		handler.process(saint, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		Score score = result.score();
		assertThat(score.value(), is(0));
	}
}
