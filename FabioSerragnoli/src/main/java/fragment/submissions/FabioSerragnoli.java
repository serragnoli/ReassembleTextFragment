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
		// Created to help document the code
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

	static class DocumentBO implements DomainService, RootAggregate {

		Document create(DefragmentedText text) {
			Document document = new Document(text);

			return document;
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

	static class DefragmentedText implements ValueObject {

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

	static class Fragments implements Entity {

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
			} while (fragments.iterator()
								.hasNext());

			return new DefragmentedText(base.value());
		}

		int size() {
			return fragments.size();
		}

		List<Fragment> fragments() {
			return fragments;
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

		@Deprecated
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

		void zero() {
			value = 0;
		}
	}

	static enum Affix implements ValueObject {
		NONE, PREFIX, INFIX, SUFFIX;
	}

	static class Fragment implements Comparable<Fragment> {

		private String value;
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

			if (Affix.PREFIX == affix) {
				concatValue = value.substring(0, charsToIgnoreFromTheEnd);
				concatValue = concatValue.concat(base);
			}

			if (Affix.SUFFIX == affix) {
				concatValue = value.substring(charsToIgnoreFromTheStart, value.length());
				concatValue = base.concat(concatValue);
			}

			if (Affix.INFIX == affix) {
				concatValue = base;
			}

			return concatValue;
		}

		Fragment(Fragment fragment) {
			this(fragment.value());
		}

		void startsWith(Fragment candidate) {
			int positionFirstMatch = 0;
			boolean matched = false;

			final StringBuilder candidateValue = new StringBuilder(candidate.value());
			for (int i = 0; i < candidateValue.length(); i++) {
				int counter = matched ? positionFirstMatch : i;
				if (value.startsWith(candidateValue.substring(counter, i + 1))) {
					if (!matched) {
						matched = true;
						positionFirstMatch = i;
					}
					candidate.turnTo(Affix.PREFIX);
					candidate.increaseScore();
					candidate.recordEnd(counter);
					this.recordBest(candidate);
				} else {
					matched = false;
					candidate.turnTo(Affix.NONE);
					candidate.zeroScore();
				}
			}
		}

		private void recordEnd(int charsFromTheEnd) {
			this.charsToIgnoreFromTheEnd = charsFromTheEnd;
		}

		void endsWith(Fragment candidate) {
			int positionFirstMatch = 0;
			boolean matched = false;

			final StringBuilder candidateValue = new StringBuilder(candidate.value());
			for (int i = candidateValue.length(); i > 0; i--) {
				int counter = matched ? positionFirstMatch : i;
				if (value.endsWith(candidateValue.substring(i - 1, counter))) {
					if (!matched) {
						matched = true;
						positionFirstMatch = i;
					}
					candidate.turnTo(Affix.SUFFIX);
					candidate.increaseScore();
					candidate.recordFirstCharsToIgnoreAtBeginning(counter);
					recordBest(candidate);
				} else {
					matched = false;
					candidate.turnTo(Affix.NONE);
					candidate.zeroScore();
				}
			}
		}

		void infixedBy(Fragment candidate) {
			String stripped = value.substring(1, value.length() - 1);

			if (stripped.contains(candidate.value())) {
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

		void increaseScore() {
			score.increase();
		}

		private void zeroScore() {
			score.zero();
		}

		Score score() {
			return score;
		}

		Fragment bestCandidate() {
			return bestCandidate;
		}

		@Override
		public int compareTo(Fragment other) {
			return other.score()
						.value() - score.value();
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
	}
}
