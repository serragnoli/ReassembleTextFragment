package fragment.submissions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FabioSerragnoli {

	public static void main(String[] args) {
		FragmentBO fragmentBO = new FragmentBO();
		DefragmentBO defragmentBO = new DefragmentBO();
		ReassembleFragments reassembleFragments = new ReassembleFragments(fragmentBO, defragmentBO);

		try (BufferedReader in = new BufferedReader(new FileReader(args[0]))) {
			String fragmentProblem;
			while ((fragmentProblem = in.readLine()) != null) {
				String reassembled = reassembleFragments.reassemble(fragmentProblem);
				System.out.println(reassembled);
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

	interface ValueObject {
		// Created to help document the code
	}

	static class ReassembleFragments implements ApplicationService {

		private FragmentBO fragmentBO;
		private DefragmentBO defragmentBO;

		ReassembleFragments(FragmentBO fragmentBO, DefragmentBO defragmentBO) {
			this.fragmentBO = fragmentBO;
			this.defragmentBO = defragmentBO;
		}

		String reassemble(String textFragments) {
			Set<Fragment> wrappedFragments = fragmentBO.extractFrom(textFragments);
			defragmentBO.defragment(wrappedFragments);

			return "Blah";
		}
	}

	static class FragmentBO implements DomainService {

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

	static class DefragmentBO implements DomainService {

		String defragment(Set<Fragment> wrappedFragments) {
			String reassembled = null;
			
			Iterator<Fragment> it = wrappedFragments.iterator();
			reassembled = it.next().value();
			if(it.hasNext()) {
				reassembled += it.next().value();
			}
			
			return reassembled;
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
