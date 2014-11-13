package fragment.submissions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Content;
import fragment.submissions.FabioSerragnoli.Fragment;

public class ContentTest {

	@Test public void 
	should_record_content() {
		Fragment fragment = new Fragment("TEST");
		Content content = new Content(fragment);
		
		assertThat(content.value(), is(fragment.value()));
	}
}
