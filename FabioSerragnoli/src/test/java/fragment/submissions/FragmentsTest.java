package fragment.submissions;

import static fragment.submissions.Parameters.O_DRACONIAN_COMPLETE_TEXT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.DefragmentedText;
import fragment.submissions.FabioSerragnoli.Fragments;
import fragment.submissions.FabioSerragnoli.HandlersChain;
import fragment.submissions.FabioSerragnoli.HandlersFactory;

public class FragmentsTest {

	private Fragments fragments;
	private HandlersChain chain;
	
	@Before public void 
	setup() {
		HandlersFactory factory = new HandlersFactory();
		chain = factory.createHandlers();
		
		fragments = new Fragments(O_DRACONIAN_COMPLETE_TEXT);
	}
	
	@Test public void 
	should_return_text_object() {
		DefragmentedText text = fragments.defragmentWith(chain);
		
		assertThat(text, is(notNullValue()));
	}
	
	@Test public void 
	should_return_full_text() {
		DefragmentedText text = fragments.defragmentWith(chain);
		
		assertThat(text.value(), is("O draconian devil! Oh lame saint!"));
	}
}
