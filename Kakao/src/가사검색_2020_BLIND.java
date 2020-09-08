import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 가사검색_2020_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String words[] = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
		String queries[] = { "fro??", "????o", "fr???", "fro???", "pro?" };
		int[] result = solution(words, queries);
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];
		Trie[] trie = new Trie[10001];
		for (int i = 0; i < words.length; i++) {
			int length = words[i].length();
			if (trie[length] == null)
				trie[length] = new Trie();
			trie[length].insert(words[i]);
		}
		for (int i = 0; i < queries.length; i++) {
			int length = queries[i].length();
			if (trie[length] == null)
				answer[i] = 0;
			else
				answer[i] = trie[length].getCount(queries[i]);
		}
		return answer;
	}

	public static class Trie {
		private TrieNode rootNode;
		private TrieNode backNode;

		Trie() {
			rootNode = new TrieNode();
			backNode = new TrieNode();
		}

		void insert(String word) {
			insertFront(word);
			insertBack(word);

		}

		private void insertBack(String word) {
			TrieNode thisNode = this.rootNode;
			for (int i = 0; i < word.length(); i++) {
				thisNode.count++;
				thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
			}
			thisNode.setLastChar(true);
		}

		private void insertFront(String word) {
			TrieNode thisNode = this.backNode;
			for (int i = word.length() - 1; i >= 0; i--) {
				thisNode.count++;
				thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());

			}
			thisNode.setLastChar(true);
		}

		public int getCount(String query) {
			if (query.charAt(0) == '?')
				return getCountFromBack(query);
			else
				return getCountFromFront(query);
		}

		private int getCountFromFront(String query) {
			TrieNode thisNode = this.rootNode;
			for (int i = 0; i < query.length(); i++) {
				if (query.charAt(i) == '?')
					break;
				if (!thisNode.childNodes.containsKey(query.charAt(i)))
					return 0;
				thisNode = thisNode.childNodes.get(query.charAt(i));
			}
			return thisNode.count;
		}

		private int getCountFromBack(String query) {
			TrieNode thisNode = this.backNode;
			for (int i = query.length() - 1; i >= 0; i--) {
				if (query.charAt(i) == '?')
					break;
				if (!thisNode.childNodes.containsKey(query.charAt(i)))
					return 0;
				thisNode = thisNode.childNodes.get(query.charAt(i));
			}
			return thisNode.count;
		}

		boolean contains(String word) {
			TrieNode thisNode = this.rootNode;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				TrieNode node = thisNode.getChildNodes().get(c);
				if (node == null)
					return false;
				thisNode = node;
			}
			return thisNode.isLastChar();
		}
	}

	public static class TrieNode {
		private Map<Character, TrieNode> childNodes;
		private boolean isLastChar;
		int count = 0;

		public TrieNode() {
			super();
			this.childNodes = new HashMap<>();
			this.isLastChar = false;
			this.count = 0;
		}

		public Map<Character, TrieNode> getChildNodes() {
			return childNodes;
		}

		public void setLastChar(boolean isLastChar) {
			this.isLastChar = isLastChar;
		}

		public boolean isLastChar() {
			return isLastChar;
		}

	}
}