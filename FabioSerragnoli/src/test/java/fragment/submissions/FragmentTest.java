package fragment.submissions;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Fragment;

public class FragmentTest {
	
	private static final String TEXT_1 = "My Fragment";
	
	@Test public void 
	should_be_equals() {
		Fragment fragment = new Fragment(TEXT_1);
		
		Fragment duplicated = new Fragment(TEXT_1);
		
		assertThat(fragment, is(equalTo(duplicated)));
	}
}
