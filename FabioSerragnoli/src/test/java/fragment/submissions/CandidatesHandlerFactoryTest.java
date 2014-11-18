package fragment.submissions;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.HandlersChain;
import fragment.submissions.FabioSerragnoli.HandlersFactory;
import fragment.submissions.FabioSerragnoli.PrefixHandler;
import fragment.submissions.FabioSerragnoli.SuffixHandler;

public class CandidatesHandlerFactoryTest {
	
	private HandlersFactory factory;
	private HandlersChain handler;

	@Before public void 
	setup() {
		factory = new HandlersFactory();
		handler = factory.createHandlers();
	}
	
	@Test public void 
	should_create_prefix_handler_first() {
		assertThat(handler, is(instanceOf(PrefixHandler.class)));
	}
	
	@Test public void 
	should_create_suffix_handler_second() {
		assertThat(handler.next(), is(instanceOf(SuffixHandler.class)));
	}
}
