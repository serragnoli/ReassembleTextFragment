package fragment.submissions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import fragment.submissions.FabioSerragnoli.Fragment;

public class FabioSerragnoli {

	public static void main(String[] args) {
		CandidateFactory factory = new CandidateFactory();
		CandidateHandlerFactory handlerFactory = new CandidateHandlerFactory();
		FragmentBO fragmentBO = new FragmentBO();
		DefragmentBO defragmentBO = new DefragmentBO(factory, handlerFactory);
		DocumentBO documentBO = new DocumentBO();
		ReassembleFragments reassembleFragments = new ReassembleFragments(fragmentBO, defragmentBO, documentBO);

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

	interface ApplicationService {
		// Created to help document the code
	}

	interface DomainService {
		// Created to help document the code
	}

	interface RootAggregate {
		// Created to help document the code
	}

	interface Entity {
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
		private DefragmentBO defragmentBO;
		private DocumentBO documentBO;

		ReassembleFragments(FragmentBO fragmentBO, DefragmentBO defragmentBO, DocumentBO documentBO) {
			this.fragmentBO = fragmentBO;
			this.defragmentBO = defragmentBO;
			this.documentBO = documentBO;
		}

		Document reassemble(String textFragments) {
			Set<Fragment> wrappedFragments = fragmentBO.extractFrom(textFragments);
			DefragmentedTextBuffer text = defragmentBO.defragment(wrappedFragments);
			Document document = documentBO.create(text);

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

	static class DefragmentBO implements DomainService, RootAggregate {

		private CandidateFactory factory;
		private CandidateHandlerFactory handlerFactory;

		DefragmentBO(CandidateFactory factory, CandidateHandlerFactory handlerFactory) {
			this.factory = factory;
			this.handlerFactory = handlerFactory;
		}

		DefragmentedTextBuffer defragment(Set<Fragment> fragments) {
			Iterator<Fragment> it = fragments.iterator();
			Fragment base = it.next();
			it.remove();

			CandidateHandler chain = handlerFactory.createHandlers();
			DefragmentedTextBuffer buffer = new DefragmentedTextBuffer();
			do {
				List<Candidate> candidates = factory.createCandidates(fragments);

				for (int i = 0; i < 2; i++) {
					chain.process(base, candidates);
					// buffer.appendBestMatch();
				}
			} while (!fragments.isEmpty());

			return buffer;
		}
	}

	static class CandidateHandlerFactory implements Factory {

		CandidateHandler createHandlers() {
			CandidateHandler prefixHandler = new PrefixHandler();
			CandidateHandler suffixHandler = new SuffixHandler();

			prefixHandler.add(suffixHandler);

			return prefixHandler;
		}
	}

	static interface CandidateHandler {
		void process(Fragment base, List<Candidate> candidates);

		void add(CandidateHandler next);

		CandidateHandler next();
	}

	static class PrefixHandler implements CandidateHandler {

		private CandidateHandler next;

		@Override
		public void process(Fragment base, List<Candidate> candidates) {
			for (Candidate candidate : candidates) {
				while (candidate.hasNextCharacter()) {
					if (base.firstCharacter() == candidate.nextCharacter()) {
						candidate.increaseScore();
						candidate.appendToBeginning();
						while (candidate.hasNextCharacter() && candidate.hasNextCharacter()) {
							if (base.nextCharacter() == candidate.nextCharacter()) {
//								candidate.score();
							}
						}
					}
				}
			}
		}

		@Override
		public CandidateHandler next() {
			return next;
		}

		@Override
		public void add(CandidateHandler next) {
			this.next = next;
		}
	}

	static class SuffixHandler implements CandidateHandler {

		private CandidateHandler next;

		@Override
		public void process(Fragment base, List<Candidate> candidates) {
		}

		@Override
		public CandidateHandler next() {
			return next;
		}

		@Override
		public void add(CandidateHandler nextHandler) {
			this.next = nextHandler;
		}
	}

	static class DocumentBO implements DomainService, RootAggregate {

		Document create(DefragmentedTextBuffer text) {
			Document document = new Document(text);

			return document;
		}
	}

	static class DefragmentedTextBuffer {

		String value() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	static class Document implements ValueObject {

		private Content content;

		Document(DefragmentedTextBuffer content) {
			this.content = new Content(content);
		}

		String content() {
			return content.value();
		}
	}

	static class Content implements ValueObject {

		private String value;

		Content(DefragmentedTextBuffer content) {
			this.value = content.value();
		}

		String value() {
			return value;
		}
	}

	static class Candidate {

		private Fragment current;
		private Fragment bestMatch;
		private Score score;
		private Orientation orientation;

		Candidate(Fragment fragment) {
			this.current = fragment;
		}

		void increaseScore() {
			score = score.increase();
		}
		
		Score score() {
			return score;
		}

		public void appendToBeginning() {
			orientation = Orientation.APPEND_TO_BEGINNING;
		}

		Fragment current() {
			return current;
		}

		Fragment bestMatch() {
			return null;
		}

		Orientation orientation() {
			return orientation;
		}

		boolean hasNextCharacter() {
			return null == current ? false : current.hasNextCharacter();
		}

		char nextCharacter() {
			if (null == current) {
				throw new IllegalStateException("This Candidate has no value");
			}
			return current.nextCharacter();
		}

		void appendToEnd() {
			orientation = Orientation.APPEND_TO_END;
		}
	}

	static class Score implements ValueObject {

		private int value;
		
		Score() {
			this.value = 0;
		}
		
		Score(int initial) {
			this.value = initial;
		}

		public Score increase() {
			return new Score(value+1);
		}

		int value() {
			return value;
		}

	}

	static enum Orientation implements ValueObject{
		APPEND_TO_END, APPEND_TO_BEGINNING
	}

	static class CandidateFactory implements Factory {

		List<Candidate> createCandidates(Set<Fragment> fragments) {
			if (null == fragments) {
				return new ArrayList<>();
			}

			List<Candidate> candidate = new ArrayList<>(fragments.size());
			for (Fragment fragment : fragments) {
				candidate.add(new Candidate(fragment));
			}

			return candidate;
		}
	}

	static class Fragment {

		private String value;
		private int lastAccessedCharacter = 0;

		Fragment(String fragmentText) {
			this.value = fragmentText;
		}

		String value() {
			return value;
		}

		boolean hasNextCharacter() {
			return null == value ? false : lastAccessedCharacter < value.length();
		}

		public char nextCharacter() {
			return value.charAt(lastAccessedCharacter++);
		}

		char firstCharacter() {
			return value.charAt(0);
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
