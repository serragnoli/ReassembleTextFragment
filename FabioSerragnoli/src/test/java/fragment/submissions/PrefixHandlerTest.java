package fragment.submissions;

import static fragment.submissions.FabioSerragnoli.Orientation.PREFIX;
import static fragment.submissions.Parameters.CONIAN_DEVIL;
import static fragment.submissions.Parameters.H_LAME_SA;
import static fragment.submissions.Parameters.O_DRACONIA;
import static fragment.submissions.Parameters.SAINT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.PrefixHandler;
import fragment.submissions.FabioSerragnoli.Score;

@SuppressWarnings("boxing")
public class PrefixHandlerTest {
	
	private PrefixHandler handler;

	@Before public void 
	setup() {
		handler = new PrefixHandler();
	}
	
	@Test public void 
	should_register_as_APPEND_TO_BEGINNING_for_CONAN_DEVIL_as_base() {
		Fragment oDraconia = O_DRACONIA.fragments().get(0);
		Fragment conianDevil = CONIAN_DEVIL.fragments().get(0);
		handler.process(conianDevil, O_DRACONIA.fragments());
		
		assertThat(oDraconia.orientation(), is(PREFIX));
	}
	
	@Test public void 
	should_have_score_of_five_for_CONIAN_DEVIL_as_base() {
		Fragment oDraconia = O_DRACONIA.fragments().get(0);
		Fragment conianDevil = CONIAN_DEVIL.fragments().get(0);
		handler.process(conianDevil, O_DRACONIA.fragments());
		
		Score score = oDraconia.score();
		
		assertThat(score.value(), is(5));
	}
	
	@Test public void 
	should_have_score_of_one_for_O_DRACONIA_as_base() {
		Fragment oDraconia = CONIAN_DEVIL.fragments().get(0);
		handler.process(oDraconia, CONIAN_DEVIL.fragments());
		
		Score score = oDraconia.score();
		
		assertThat(score.value(), is(1));
	}
	
	@Test public void 
	should_have_score_of_four_for_H_LAME_SA_as_base() {
		Fragment hLameSa = H_LAME_SA.fragments().get(0);
		handler.process(hLameSa, CONIAN_DEVIL.fragments());
		
		Score score = hLameSa.score();
		
		assertThat(score.value(), is(4));
	}
	
	@Test public void 
	should_have_score_of_zero_for_CONIAN_DEVIL_as_base() {
		Fragment conianDevil = CONIAN_DEVIL.fragments().get(0);
		handler.process(conianDevil, H_LAME_SA.fragments());
		
		Score score = conianDevil.score();
		
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_have_score_of_zero_for_H_LAME_SA_as_base() {
		Fragment hLameSa = H_LAME_SA.fragments().get(0);
		handler.process(hLameSa, SAINT.fragments());
		
		Score score = hLameSa.score();
		
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_have_score_of_two_for_SAINT_as_base() {
		Fragment saint = SAINT.fragments().get(0);
		handler.process(saint, H_LAME_SA.fragments());
		
		Score score = saint.score();
		
		assertThat(score.value(), is(2));
	}
}









