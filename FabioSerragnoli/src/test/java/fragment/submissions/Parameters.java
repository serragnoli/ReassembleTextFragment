package fragment.submissions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fragment.submissions.FabioSerragnoli.Fragment;
import fragment.submissions.FabioSerragnoli.Fragments;

public class Parameters {

	static final String O_DRACONIA_TEXT = "O draconia";
	static final String CONIAN_DEVIL_TEXT = "conian devil! Oh la";
	static final String H_LAME_SA_TEXT = "h lame sa";
	static final String SAINT_TEXT = "saint!";
	static final String O_DRACONIAN_COMPLETE_TEXT = "O draconia;conian devil! Oh la;h lame sa;saint!";
	static final String DUPLICATED_FRAGMENTS =  O_DRACONIAN_COMPLETE_TEXT + ";" + O_DRACONIAN_COMPLETE_TEXT;
	static final Fragments O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS = new Fragments(O_DRACONIAN_COMPLETE_TEXT);
	static final Set<Fragment> O_DRACONIA_AND_CONIA_EVIL_OH_LA_FRAGMENTS;
	static final List<Fragment> LIST_OF_TWO_DISTINCT_FRAGMENTS;
	static final Fragments O_DRACONIA = new Fragments(O_DRACONIA_TEXT);
	static final Fragments CONIAN_DEVIL = new Fragments(CONIAN_DEVIL_TEXT);
	static final Fragments H_LAME_SA = new Fragments(H_LAME_SA_TEXT);
	static final Fragments SAINT = new Fragments(SAINT_TEXT);
	
	static {
		O_DRACONIA_AND_CONIA_EVIL_OH_LA_FRAGMENTS = new HashSet<>();
		O_DRACONIA_AND_CONIA_EVIL_OH_LA_FRAGMENTS.add(new Fragment("O draconia"));
		O_DRACONIA_AND_CONIA_EVIL_OH_LA_FRAGMENTS.add(new Fragment("conian devil! Oh la"));
		
		LIST_OF_TWO_DISTINCT_FRAGMENTS = null; //new ArrayList<>(O_DRACONIA_AND_CONIA_EVIL_FRAGMENTS);
	}
	
	static Fragment fragmentWith(String text) {
		return new Fragment(text);
	}
	
	static Fragments fragmentsWith(String text, String... otherTexts) {
		StringBuilder builder = new StringBuilder(text); 
		for (String other : otherTexts) {
			builder.append(";");
			builder.append(other);
		}
		return new Fragments(builder.toString());
	}
}
