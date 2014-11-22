package fragment.submissions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FabioSerragnoli {

	public static void main(String[] args) {
		HandlersFactory handlerFactory = new HandlersFactory();
		FragmentBO fragmentBO = new FragmentBO();
		DefragmentBO defragmentBO = new DefragmentBO(handlerFactory);
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
			Fragments fragments = fragmentBO.extractFrom(textFragments);
			DefragmentedText text = defragmentBO.defragment(fragments);
			Document document = documentBO.create(text);

			return document;
		}
	}

	static class FragmentBO implements DomainService, RootAggregate {

		Fragments extractFrom(String fragmentsLine) {
			Fragments fragments = new Fragments(fragmentsLine);

			return fragments;
		}
	}

	static class DefragmentBO implements DomainService, RootAggregate {

		private HandlersFactory handlerFactory;

		DefragmentBO(HandlersFactory handlersFactory) {
			this.handlerFactory = handlersFactory;
		}

		DefragmentedText defragment(Fragments fragments) {
			HandlersChain chain = handlerFactory.createHandlers();

			DefragmentedText text = fragments.defragmentWith(chain);

			return text;
		}
	}

	static class HandlersFactory implements Factory {

		HandlersChain createHandlers() {
			HandlersChain prefixHandler = new PrefixHandler();
			HandlersChain suffixHandler = new SuffixHandler();
			HandlersChain infixHandler = new InfixHandler();

			prefixHandler.add(suffixHandler);
			suffixHandler.add(infixHandler);

			return prefixHandler;
		}
	}

	static interface HandlersChain {
		void process(Fragment base, List<Fragment> fragments, List<Fragment> evaluated);

		void add(HandlersChain next);

		HandlersChain next();
	}

	static class PrefixHandler implements HandlersChain {

		private HandlersChain next;

		@Override
		public void process(Fragment base, List<Fragment> fragments, List<Fragment> evaluated) {
			for (Fragment fragment : fragments) {
				Fragment candidate = new Fragment(fragment);
				base.startsWith(candidate);
				evaluated.add(candidate);
			}
			next.process(base, fragments, evaluated);
		}

		@Override
		public HandlersChain next() {
			return next;
		}

		@Override
		public void add(HandlersChain nextHandler) {
			this.next = nextHandler;
		}
	}

	static class SuffixHandler implements HandlersChain {

		private HandlersChain next;

		@Override
		public void process(Fragment base, List<Fragment> fragments, List<Fragment> evaluated) {
			for (Fragment fragment : fragments) {
				Fragment candidate = new Fragment(fragment);
				base.endsWith(candidate);
				evaluated.add(candidate);
			}
			next.process(base, fragments, evaluated);
		}

		@Override
		public HandlersChain next() {
			return next;
		}

		@Override
		public void add(HandlersChain nextHandler) {
			this.next = nextHandler;
		}
	}
	
	static class InfixHandler implements HandlersChain {
		
		private HandlersChain next;

		@Override
		public void process(Fragment base, List<Fragment> fragments, List<Fragment> evaluated) {
			for (Fragment fragment : fragments) {
				Fragment candidate = new Fragment(fragment);
				base.infixedBy(candidate);
			}
		}
		
		@Override
		public void add(HandlersChain nextHandler) {
			this.next = nextHandler;
		}
		
		@Override
		public HandlersChain next() {
			return next;
		}
	}

	static class DocumentBO implements DomainService, RootAggregate {

		Document create(DefragmentedText text) {
			Document document = new Document(text);

			return document;
		}
	}

	static class DefragmentedText {

		private String value;

		DefragmentedText(String text) {
			this.value = text;
		}
		
		String value() {
			return value;
		}
	}

	static class Document implements ValueObject {

		private Content content;

		Document(DefragmentedText content) {
			this.content = new Content(content);
		}

		String content() {
			return content.value();
		}
	}

	static class Content implements ValueObject {

		private String value;

		Content(DefragmentedText content) {
			this.value = content.value();
		}

		String value() {
			return value;
		}
	}

	static class Fragments {

		private List<Fragment> fragments;
		private Fragment base;

		Fragments(String lineOfFragments) {
			String[] splitFragments = lineOfFragments.split(";");

			fragments = new ArrayList<>();
			for (String fragmentText : splitFragments) {
				Fragment fragment = new Fragment(fragmentText);
				fragments.add(fragment);
			}
		}

		DefragmentedText defragmentWith(HandlersChain chain) {
			base = fragments.remove(0);

			do {
				List<Fragment> evaluated = new ArrayList<>(fragments.size());
				chain.process(base, fragments, evaluated);

				base.mergeBestCandidate();
				
				Fragment bestCandidate = base.clearBestCandidate();
				fragments.remove(bestCandidate);
			} while (fragments.iterator().hasNext());
			
			return new DefragmentedText(base.value());
		}

		Fragment current() {
			return base;
		}

		Fragment bestMatch() {
			return null;
		}

		boolean hasNextCharacter() {
			return null == base ? false : base.hasNextCharacter();
		}

		char nextCharacter() {
			if (null == base) {
				throw new IllegalStateException("This Candidate has no value");
			}
			return base.nextCharacter();
		}

		int size() {
			return fragments.size();
		}

		List<Fragment> fragments() {
			return fragments;
		}

		@Override
		public String toString() {
			return new StringBuilder("Fragments[").append("base:")
													.append(base)
													.append(" fragments: ")
													.append(fragments)
													.toString();
		}
	}

	static class Score {

		private int value;

		Score() {
			this.value = 0;
		}

		void increase() {
			value++;
		}

		void decrease() {
			if (value > 0) {
				value--;
			}
		}

		int value() {
			return value;
		}

		boolean worseThan(Score score) {
			return this.value < score.value();
		}

		@Override
		public String toString() {
			return "Score: value " + value;
		}
	}

	static enum Affix implements ValueObject {
		NONE, PREFIX, INFIX, SUFFIX ;
	}

	static class Fragment implements Comparable<Fragment> {

		private String value;
		private int lastAccessedCharacter;
		private Score score = new Score();
		private Affix affix = Affix.NONE;
		private Fragment bestCandidate;
		private int charsToIgnoreFromTheEnd;
		private int charsToIgnoreFromTheStart;

		Fragment(String fragmentText) {
			this.value = fragmentText;
		}

		Fragment clearBestCandidate() {
			Fragment current = bestCandidate;
			
			bestCandidate = null;
			
			return current;
		}

		void mergeBestCandidate() {
			value = bestCandidate.concat(value);
		}

		private String concat(String base) {
			String concatValue = "";
			
			if(Affix.PREFIX == affix) {
				concatValue = value.substring(0, charsToIgnoreFromTheEnd);
				concatValue = concatValue.concat(base);
			}
			
			if(Affix.SUFFIX == affix) {
				concatValue = value.substring(charsToIgnoreFromTheStart, value.length());
				concatValue = base.concat(concatValue);
			}
			
			if(Affix.INFIX == affix) {
				concatValue = base;
			}
			
			return concatValue;
		}

		Fragment(Fragment fragment) {
			this(fragment.value());
		}

		void startsWith(Fragment candidate) {
			int locFirstMatch = 0;
			boolean matched = false;

			for (int i = 0; i < candidate.value().length(); i++) {
				int counter = matched ? locFirstMatch : i;
				if (value.startsWith(candidate.value().substring(counter, i + 1))) {
					if (!matched) {
						matched = true;
						locFirstMatch = i;
					}
					candidate.turnTo(Affix.PREFIX);
					candidate.increaseScore();
					candidate.recordEnd(counter);
					recordBest(candidate);
				} else {
					matched = false;
					candidate.turnTo(Affix.INFIX);
					candidate.decreaseScore();
				}
			}
		}

		private void recordEnd(int charsFromTheEnd) {
			this.charsToIgnoreFromTheEnd = charsFromTheEnd;
		}

		void endsWith(Fragment candidate) {
			int locFirstMatch = 0;
			boolean matched = false;

			for (int i = candidate.value().length(); i > 0; i--) {
				int counter = matched ? locFirstMatch : i;
				if (value.endsWith(candidate.value().substring(i - 1, counter))) {
					if (!matched) {
						matched = true;
						locFirstMatch = i;
					}
					candidate.turnTo(Affix.SUFFIX);
					candidate.increaseScore();
					candidate.recordFirstCharsToIgnoreAtBeginning(counter);
					recordBest(candidate);
				} else {
					matched = false;
					candidate.turnTo(Affix.INFIX);
					candidate.decreaseScore();
				}
			}
		}
		
		void infixedBy(Fragment candidate) {
			StringBuilder sb = new StringBuilder(value);
			
			String stripped = sb.substring(1, value.length() - 1);
			
			if(stripped.contains(candidate.value())) {
				candidate.turnTo(Affix.INFIX);
				recordBest(candidate);
			}
		}

		private void turnTo(Affix newAffix) {
			this.affix = newAffix;
		}

		private void recordFirstCharsToIgnoreAtBeginning(int charsToIgnoreAtBeginning) {
			this.charsToIgnoreFromTheStart = charsToIgnoreAtBeginning;
		}

		void recordBest(Fragment candidate) {
			if (null == bestCandidate || bestCandidate.worseThan(candidate)) {
				bestCandidate = candidate;
			}
		}

		private boolean worseThan(Fragment otherCandidate) {
			return this.score.worseThan(otherCandidate.score());
		}

		String value() {
			return value;
		}

		Affix affix() {
			return affix;
		}

		char firstCharacter() {
			return value.charAt(0);
		}

		boolean hasNextCharacter() {
			return null == value ? false : lastAccessedCharacter < value.length();
		}

		void appendToEnd() {
			affix = Affix.SUFFIX;
		}

		char nextCharacter() {
			moveIndexWhenItHasNotMoved();
			return value.charAt(lastAccessedCharacter++);
		}

		void increaseScore() {
			score.increase();
		}

		private void decreaseScore() {
			score.decrease();
		}

		Score score() {
			return score;
		}

		void appendToBeginning() {
			affix = Affix.PREFIX;
		}

		private void moveIndexWhenItHasNotMoved() {
			if (0 == lastAccessedCharacter && 2 <= value.length()) {
				lastAccessedCharacter = 1;
			}
		}

		Fragment bestCandidate() {
			return bestCandidate;
		}
		
		@Override
		public int compareTo(Fragment other) {
			return other.score().value() - score.value();
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
			return new StringBuilder("Fragment: ").append(value).toString();
		}
	}
}
