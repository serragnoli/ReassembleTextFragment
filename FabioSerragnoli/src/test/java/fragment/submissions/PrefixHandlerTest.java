package fragment.submissions;

import static fragment.submissions.Parameters.CONIAN_DEVIL_TEXT;
import static fragment.submissions.Parameters.H_LAME_SA_TEXT;
import static fragment.submissions.Parameters.O_DRACONIA_TEXT;
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
import fragment.submissions.FabioSerragnoli.Orientation;
import fragment.submissions.FabioSerragnoli.PrefixHandler;
import fragment.submissions.FabioSerragnoli.Score;

@SuppressWarnings("boxing")
public class PrefixHandlerTest {
	
	private PrefixHandler handler;
	private List<Fragment> evaluated;

	@Before public void 
	setup() {
		handler = new PrefixHandler();
		evaluated = new ArrayList<>();
	}
	
	@Test public void 
	should_score_two_sample_1() {
		Fragment saint = fragmentWith(SAINT_TEXT);
		Fragment candidate = fragmentWith(H_LAME_SA_TEXT);
		
		handler.process(saint, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		Score score = result.score();
		assertThat(score.value(), is(2));
	}
	
	@Test public void 
	should_have_append_strategy_prefix() {
		Fragment saint = fragmentWith(SAINT_TEXT);
		Fragment candidate = fragmentWith(H_LAME_SA_TEXT);
		
		handler.process(saint, asList(candidate), evaluated);
		
		assertThat(candidate.orientation(), is(Orientation.PREFIX));
	}
	
	@Test public void 
	should_score_five() {
		Fragment candidate = fragmentWith(O_DRACONIA_TEXT);
		Fragment conianDevil = fragmentWith(CONIAN_DEVIL_TEXT);
		
		handler.process(conianDevil, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		Score score = result.score();
		assertThat(score.value(), is(5));
	}
	
	@Test public void 
	should_score_zero_sample_1() {
		Fragment oDraconia = fragmentWith(O_DRACONIA_TEXT);
		Fragment candidate = fragmentWith(CONIAN_DEVIL_TEXT);
		
		handler.process(oDraconia, asList(candidate), evaluated);
		
		Score score = oDraconia.score();
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_score_zero_sample_2() {
		Fragment hLameSa = fragmentWith(H_LAME_SA_TEXT);
		Fragment candidate = fragmentWith(SAINT_TEXT);
		
		handler.process(hLameSa, asList(candidate), evaluated);
		
		Score score = hLameSa.score();
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_score_zero_sample_3() {
		Fragment conianDevil = fragmentWith(CONIAN_DEVIL_TEXT);
		Fragment candidate = fragmentWith(H_LAME_SA_TEXT);
		
		handler.process(conianDevil, asList(candidate), evaluated);
		
		Score score = conianDevil.score();
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_score_zero_sample_4() {
		Fragment hLameSa = fragmentWith(H_LAME_SA_TEXT);
		Fragment candidate = fragmentWith(CONIAN_DEVIL_TEXT);
		
		handler.process(hLameSa, asList(candidate), evaluated);
		
		Score score = hLameSa.score();		
		assertThat(score.value(), is(0));
	}
}









