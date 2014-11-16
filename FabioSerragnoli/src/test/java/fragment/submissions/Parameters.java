package fragment.submissions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fragment.submissions.FabioSerragnoli.Fragment;

public class Parameters {

	static final String O_DRACONIA_TEXT = "O draconia";
	static final String CONIAN_DEVIL_TEXT = "conian devil";
	static final String VALID_FRAGMENTS = "O draconia;conian devil! Oh la;h lame sa;saint!";
	static final String DUPLICATED_FRAGMENTS =  VALID_FRAGMENTS + ";" + VALID_FRAGMENTS;
	static final Set<Fragment> IDENTICAL_O_DRACONIA_FRAGMENTS;
	static final Set<Fragment> O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS;
	static final Set<Fragment> O_DRACONIA_AND_CONIA_EVIL_OH_LA_FRAGMENTS;
	static final List<Fragment> LIST_OF_TWO_DISTINCT_FRAGMENTS;
	static final Fragment O_DRACONIA = new Fragment("O draconia");
	static final Fragment CONIAN_DEVIL = new Fragment("conian devil! Oh la");
	static final Fragment H_LAME_SA = new Fragment("h lame sa");
	static final Fragment SAINT = new Fragment("saint!");
	
	static {
		IDENTICAL_O_DRACONIA_FRAGMENTS = new HashSet<>();
		IDENTICAL_O_DRACONIA_FRAGMENTS.add(new Fragment("O draconia"));
		IDENTICAL_O_DRACONIA_FRAGMENTS.add(new Fragment("O draconia"));
		
		O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS = new HashSet<>();
		O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS.add(new Fragment(O_DRACONIA_TEXT));
		O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS.add(new Fragment(CONIAN_DEVIL_TEXT));
		
		O_DRACONIA_AND_CONIA_EVIL_OH_LA_FRAGMENTS = new HashSet<>();
		O_DRACONIA_AND_CONIA_EVIL_OH_LA_FRAGMENTS.add(new Fragment("O draconia"));
		O_DRACONIA_AND_CONIA_EVIL_OH_LA_FRAGMENTS.add(new Fragment("conian devil! Oh la"));
		
		LIST_OF_TWO_DISTINCT_FRAGMENTS = new ArrayList<>(O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS);
	}
}
