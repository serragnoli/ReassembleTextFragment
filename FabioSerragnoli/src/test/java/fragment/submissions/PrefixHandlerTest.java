package fragment.submissions;

import static fragment.submissions.Parameters.CONIAN_DEVIL_TEXT;
import static fragment.submissions.Parameters.H_LAME_SA_TEXT;
import static fragment.submissions.Parameters.O_DRACONIA_TEXT;
import static fragment.submissions.Parameters.SAINT_TEXT;
import static fragment.submissions.Parameters.fragmentWith;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.HandlersChain;
import fragment.submissions.FabioSerragnoli.Affix;
import fragment.submissions.FabioSerragnoli.PrefixHandler;
import fragment.submissions.FabioSerragnoli.Score;

@SuppressWarnings("boxing")
public class PrefixHandlerTest {
	
	private PrefixHandler handler;
	private List<Fragment> evaluated;
	private HandlersChain next;

	@Before public void 
	setup() {
		handler = new PrefixHandler();
		next = mock(HandlersChain.class);
		evaluated = new ArrayList<>();
		
		handler.add(next);
	}
	
	@Test public void 
	should_invoke_next_after_processing() {
		Fragment saint = fragmentWith(SAINT_TEXT);
		Fragment candidate = fragmentWith(H_LAME_SA_TEXT);
		
		handler.process(saint, asList(candidate), evaluated);
		
		Mockito.verify(next, atMost(1)).process(saint, asList(candidate), evaluated);
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
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		assertThat(result.affix(), is(Affix.PREFIX));
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
	should_score_one() {
		Fragment oDraconia = fragmentWith(O_DRACONIA_TEXT);
		Fragment candidate = fragmentWith(CONIAN_DEVIL_TEXT);
		
		handler.process(oDraconia, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		Score score = result.score();
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_score_zero_sample_2() {
		Fragment hLameSa = fragmentWith(H_LAME_SA_TEXT);
		Fragment candidate = fragmentWith(SAINT_TEXT);
		
		handler.process(hLameSa, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		Score score = result.score();
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_score_zero_sample_3() {
		Fragment conianDevil = fragmentWith(CONIAN_DEVIL_TEXT);
		Fragment candidate = fragmentWith(H_LAME_SA_TEXT);
		
		handler.process(conianDevil, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		Score score = result.score();
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_score_four() {
		Fragment hLameSa = fragmentWith(H_LAME_SA_TEXT);
		Fragment candidate = fragmentWith(CONIAN_DEVIL_TEXT);
		
		handler.process(hLameSa, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		Score score = result.score();
		assertThat(score.value(), is(4));
	}
	
	@Test public void 
	should_score_zero() {
		Fragment aToF = new Fragment("ABCDEF");
		Fragment candidate = new Fragment("DEFG");
		
		handler.process(aToF, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		Score score = result.score();
		assertThat(score.value(), is(0));
	}

	@Test public void 
	should_score_three() {
		Fragment aToF = new Fragment("ABCDEF");
		Fragment candidate = new Fragment("XYZABC");
		
		handler.process(aToF, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		Score score = result.score();
		assertThat(score.value(), is(3));
	}
	
	@Test public void 
	should_score_zero_sample_1() {
		Fragment aToF = new Fragment("ABCDEF");
		Fragment candidate = new Fragment("BCDE");
		
		handler.process(aToF, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		Score score = result.score();
		assertThat(score.value(), is(0));
	}

	@Test public void 
	should_score_zero_sample_4() {
		Fragment aToF = new Fragment("ABCDEF");
		Fragment candidate = new Fragment("XCDEZ");
		
		handler.process(aToF, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		Score score = result.score();
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_score_zero_sample_5() {
		Fragment carnivore = new Fragment("Carnivore");
		Fragment herbivore = new Fragment("Herbivore");
		
		handler.process(carnivore, asList(herbivore), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(herbivore));
		Score score = result.score();
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_score_zero_sample_6() {
		Fragment mango = new Fragment("mango");
		Fragment jango = new Fragment("super-jango");
		
		handler.process(mango, asList(jango), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(jango));
		Score score = result.score();
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_score_zero_sample_7() {
		Fragment mango = new Fragment("mango");
		Fragment jango = new Fragment("super-jango");
		
		handler.process(jango, asList(mango), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(mango));
		Score score = result.score();
		assertThat(score.value(), is(0));
	}
}
