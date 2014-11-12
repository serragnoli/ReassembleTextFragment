package fragment.submissions;

import java.util.HashSet;
import java.util.Set;

import fragment.submissions.FabioSerragnoli.Fragment;

public class Parameters {

	static final String VALID_FRAGMENTS = "O draconia;conian devil! Oh la;h lame sa;saint!";
	static final String DUPLICATED_FRAGMENTS =  VALID_FRAGMENTS + ";" + VALID_FRAGMENTS;
	static final Set<Fragment> TWO_IDENTICAL_FRAGMENTS;
	static final Set<Fragment> TWO_DISTINCT_FRAGMENTS;
	static final Set<Fragment> TWO_OVERLAPPING_FRAGMENTS;
	
	static {
		TWO_IDENTICAL_FRAGMENTS = new HashSet<>();
		TWO_IDENTICAL_FRAGMENTS.add(new Fragment("O draconia"));
		TWO_IDENTICAL_FRAGMENTS.add(new Fragment("O draconia"));
		
		TWO_DISTINCT_FRAGMENTS = new HashSet<>();
		TWO_DISTINCT_FRAGMENTS.add(new Fragment("O draconian"));
		TWO_DISTINCT_FRAGMENTS.add(new Fragment(" devil!"));
		
		TWO_OVERLAPPING_FRAGMENTS = new HashSet<>();
		TWO_OVERLAPPING_FRAGMENTS.add(new Fragment("O draconia"));
		TWO_OVERLAPPING_FRAGMENTS.add(new Fragment("conian devil! Oh la"));
	}
}
