package fragment.submissions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fragment.submissions.FabioSerragnoli.Fragment;

public class FabioSerragnoli {

	public static void main(String[] args) {
		TrackerFactory factory = new TrackerFactory();
		DocumentBO defragmentBO = new DocumentBO(factory);
		FragmentBO fragmentBO = new FragmentBO();
		ReassembleFragments reassembleFragments = new ReassembleFragments(fragmentBO, defragmentBO);

		try (BufferedReader in = new BufferedReader(new FileReader(args[0]))) {
			String fragmentProblem;
			while ((fragmentProblem = in.readLine()) != null) {
				Document document = reassembleFragments.reassemble(fragmentProblem);
				System.out.println(document.content());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	interface RootAggregate {
		// Created to help document the code
	}

	interface Entity {
		// Created to help document the code
	}

	interface ApplicationService {
		// Created to help document the code
	}

	interface DomainService {
		// Created to help document the code
	}

	interface Factory {
		// Created to help documento the code
	}

	interface ValueObject {
		// Created to help document the code
	}

	static class ReassembleFragments implements ApplicationService {

		private FragmentBO fragmentBO;
		private DocumentBO defragmentBO;

		ReassembleFragments(FragmentBO fragmentBO, DocumentBO defragmentBO) {
			this.fragmentBO = fragmentBO;
			this.defragmentBO = defragmentBO;
		}

		Document reassemble(String textFragments) {
			Set<Fragment> wrappedFragments = fragmentBO.extractFrom(textFragments);
			Document document = defragmentBO.defragment(wrappedFragments);

			return document;
		}
	}

	static class FragmentBO implements DomainService, RootAggregate {

		Set<Fragment> extractFrom(String fragmentsLine) {
			String[] fragments = fragmentsLine.split(";");

			Set<Fragment> wrapped = new HashSet<>();
			for (String fragmentText : fragments) {
				Fragment fragment = new Fragment(fragmentText);
				wrapped.add(fragment);
			}
			return wrapped;
		}
	}

	static class DocumentBO implements DomainService, RootAggregate {

		private TrackerFactory factory;

		DocumentBO(TrackerFactory factory) {
			this.factory = factory;
		}

		Document defragment(Set<Fragment> wrappedFragments) {
			Document document = new Document(factory);
			document.probe(wrappedFragments);

			return document;
		}
	}

	static class Document implements Entity {

		private TrackerFactory factory;
		private Content content;

		Document(TrackerFactory factory) {
			this.factory = factory;
		}

		void probe(Set<Fragment> fragments) {
			List<Tracker> trackers = factory.createTrackers(fragments);
			Tracker base = trackers.get(0);
			content = new Content(base.current());
			
			for (Tracker tracker : trackers) {
				base.evaluate(tracker);
			}
		}

		String content() {
			return content.value();
		}
	}

	static class Content implements ValueObject {

		private String value;

		Content(Fragment fragment) {
			this.value = fragment.value();
		}

		String value() {
			return value;
		}
	}

	static class Tracker {

		private Fragment current;
		private Fragment bestMatch;
		private Score score;
		private Orientation orientation;

		Tracker(Fragment fragment) {
			this.current = fragment;
		}

		void evaluate(Tracker match) {
			for(int i = 0; i < current.value.length(); i++) {
				for(int y = 0; y < match.current.value.length(); y++) {
					
				}
			}
		}

		Fragment current() {
			return current;
		}

		public Fragment bestMatch() {
			return null;
		}
	}
	
	static class Score implements ValueObject {
		
	}
	
	static enum Orientation {
		APPEND_TO_END, APPEND_TO_BEGINNING
	}

	static class TrackerFactory implements Factory {

		public List<Tracker> createTrackers(Set<Fragment> fragments) {
			if (null == fragments) {
				return new ArrayList<>();
			}

			List<Tracker> trackers = new ArrayList<>(fragments.size());
			for (Fragment fragment : fragments) {
				trackers.add(new Tracker(fragment));
			}

			return trackers;
		}
	}

	static class Fragment implements ValueObject {

		private String value;

		Fragment(String fragmentText) {
			this.value = fragmentText;
		}

		String value() {
			return value;
		}

		@Override
		public int hashCode() {
			return value.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof Fragment)) {
				return false;
			}

			Fragment otherFragment = (Fragment) obj;

			return value.equals(otherFragment.value);
		}

		@Override
		public String toString() {
			return new StringBuilder("Fragment: ").append(value)
													.toString();
		}
	}
}
