package fragment.submissions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fragment.submissions.FabioSerragnoli.Score;

@SuppressWarnings("boxing")
public class ScoreTest {

	private Score score;
	
	@Before public void 
	setup() {
		score = new Score();
	}
	
	@Test public void 
	should_create_score_at_zero() {
		assertThat(score.value(), is(0));
	}
	
	@Test public void 
	should_increase_score_by_one() {
		score = score.increase();
		
		assertThat(score.value(), is(1));
	}
}
