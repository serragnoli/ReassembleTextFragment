package fragment.submissions;

import static fragment.submissions.Parameters.AM_AL_TEXT;
import static fragment.submissions.Parameters.UNT_UT_LABORE_ET_DOLORE_TEXT;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Affix;
import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.InfixHandler;

public class InfixHandlerTest {
	private InfixHandler handler;
	private List<Fragment> evaluated;

	@Before
	public void setup() {
		handler = new InfixHandler();
		evaluated = new ArrayList<>();
	}
	
	@Test public void 
	should_return_infix_when_base_is_longer_than_candidate() {
		Fragment amAl = new Fragment(UNT_UT_LABORE_ET_DOLORE_TEXT);
		Fragment candidate = new Fragment(AM_AL_TEXT);

		handler.process(amAl, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		assertThat(result.affix(), is(Affix.INFIX));
	}
	
	@Test public void 
	should_return_infix_when_case_is_sorter_than_candidate() {
		Fragment amAl = new Fragment(AM_AL_TEXT);
		Fragment candidate = new Fragment(UNT_UT_LABORE_ET_DOLORE_TEXT);

		handler.process(amAl, asList(candidate), evaluated);
		
		Fragment result = evaluated.get(evaluated.indexOf(candidate));
		assertThat(result.affix(), is(Affix.INFIX));
	}
}
