package fragment.submissions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class FabioSerragnoli {

	public static void main(String[] args) {
		FragmentBO fragmentBO = new FragmentBO();
		ReassembleFragments reassembleFragments = new ReassembleFragments(fragmentBO);

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

		ReassembleFragments(FragmentBO fragmentBO) {
			this.fragmentBO = fragmentBO;
		}

		public String reassemble(String textFragments) {
			fragmentBO.extractFrom(textFragments);

			return "Blah";
		}
	}

	static class FragmentBO implements DomainService {

		Set<Fragment> extractFrom(String fragmentsLine) {
			String[] fragments = fragmentsLine.split(";");
			
			Set<Fragment> wrapped = new HashSet<>();
			for(String fragmentText : fragments) {
				Fragment fragment = new Fragment(fragmentText);
				wrapped.add(fragment);
			}
			
			return wrapped;
		}
	}

	static class Fragment implements ValueObject {

		private String value;
		
		Fragment(String fragmentText) {
			this.value = fragmentText;
		}

		@Override
		public int hashCode() {
			return value.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			}
			
			if(!(obj instanceof Fragment)) {
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
