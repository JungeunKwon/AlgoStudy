import java.util.*;



public class 길찾기게임_2019_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] nodeinfo = { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 },
				{ 2, 2 } };

		int result[][] = solution(nodeinfo);
		for (int i = 0; i < result.length; i++) {
			System.out.println(Arrays.toString(result[i]));
		}
	}

	public static int idx;

	public static int[][] solution(int[][] nodeinfo) {
		int[][] answer = {};
		PriorityQueue<Node> q = new PriorityQueue();
		Node root = null;
		for (int i = 0; i < nodeinfo.length; i++) {
			q.offer(new Node(null, null, null, i + 1, nodeinfo[i][0], nodeinfo[i][1]));
		}
		int size = q.size();
		root = q.poll();
		while(!q.isEmpty()) {
			InsertNode(q.poll(), root);
		}
		answer = new int[2][size];
		idx = 0;
		PreOrder(root, answer[0]);
		idx = 0;
		PostOrder(root, answer[1]);
		return answer;
	}

	private static void PostOrder(Node root, int[] is) {
		if (root.left != null) {
			PostOrder(root.left, is);
		}
		if (root.right != null) {
			PostOrder(root.right, is);
		}
		is[idx] = root.num;
		idx++;
	}

	private static void PreOrder(Node root, int[] is) {
		is[idx] = root.num;
		idx++;
		if (root.left != null) {

			PreOrder(root.left, is);

		}
		if (root.right != null) {
			PreOrder(root.right, is);
		}
	}

	private static void InsertNode(Node node, Node root) {
		if (node.x < root.x) {
			if (root.left == null) {
				root.left = node;
				node.parent = root;
			} else
				InsertNode(node, root.left);
		} else {
			if (root.right == null) {
				root.right = node;
				node.parent = root;
			} else
				InsertNode(node, root.right);
		}
	}

	public static class Node implements Comparable<Node> {
		Node parent;
		Node left;
		Node right;
		int num;
		int x;
		int y;

		@Override
		public String toString() {
			return "Node [parent=" + parent + ", left=" + left + ", right=" + right + ", num=" + num + ", x=" + x
					+ ", y=" + y + "]";
		}

		public Node(Node parent, Node left, Node right, int num, int x, int y) {
			super();
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.num = num;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			if (this.y == o.y)
				return this.x - o.x;
			return o.y - this.y;
		}

	}
}