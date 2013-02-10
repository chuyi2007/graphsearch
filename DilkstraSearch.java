import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class ScorePair extends Pair implements Comparable<ScorePair>{
	//ScorePair parent;
	int val;
	public ScorePair(int x, int y, ScorePair parent, int val) {
		super(x, y, parent);
		this.val = val;
		// TODO Auto-generated constructor stub
	}
	
	public int compareTo(ScorePair p){
		if(this.val >= p.val)
			return 1;
		else
			return -1;
	}
}

public class DijkstraSearch {
	public static void main(String[] args){
		char[][] map = {
				{'*', '*', '4', '*', '2'},
				{'1', '9', '3', '*', '3'},
				{'1', '*', '9', '9', '4'},
				{'2', '*', '4', '7', '5'},
				{'3', '9', '9', '*', '1'},
				{'4', '8', '1', '*', '1'},
				{'5', '7', '1', '*', '1'},
				{'3', '7', '1', '1', '1'}};
		ScorePair S = new ScorePair(3, 0, null, map[3][0]);
		ScorePair E = new ScorePair(0, 4, null, map[0][4]);
		//reversed order
		List<? extends Pair> shortestPath = Dijkstra(map, S, E);
		System.out.println("Smallest Step number is: " + shortestPath.size());
		printPath(shortestPath, map.length, map[0].length);
	}

	public static void printPath(List<? extends Pair> path, int m, int n){
		for(int i = 0; i < m; ++i){
			for(int j = 0; j < n; ++j){
				boolean flag = false;
				for(int k = 0; k < path.size(); ++k){
					if(i == path.get(k).x && j == path.get(k).y){
						flag = true;
						path.remove(k);
						break;
					}
				}
				if(flag)
					System.out.print(" . ");
				else
					System.out.print("   ");
			}
			System.out.println();
		}
	}
	public static boolean checkEqual(Pair p1, Pair p2){
		return p1.x == p2.x && p1.y == p2.y;
	}
	
	public static void printWalked(boolean[][] used){
		//System.out.println();
		for(int i = 0; i < used.length; ++i){
			for(int j = 0; j < used[i].length; ++j){
				if(used[i][j])
					System.out.print(" . ");
				else
					System.out.print("   ");
			}
			System.out.println();
		}
		System.out.println("----------------");
	}
	public static List<ScorePair> Dijkstra(char[][] map, ScorePair S, ScorePair E){
		//int curLevel = 0;
		Queue<ScorePair> q = new PriorityQueue<ScorePair>();
		int m = map.length, n = map[0].length;
		boolean[][] used = new boolean[m][n];
		q.offer(S);
		//int curCount = 1;
		//int nextCount = 0;
		while(q.size() != 0){
			ScorePair cur = q.poll();
			used[cur.x][cur.y] = true;
			//--curCount;
			if(checkEqual(cur, E)){
				List<ScorePair> st = new ArrayList<ScorePair>();
				while(cur != null){
					st.add(cur);
					cur = (ScorePair)cur.parent;
				}
				return st;
			}
			if(cur.x + 1 < m && !used[cur.x + 1][cur.y] && map[cur.x + 1][cur.y] != '*'){
				//++nextCount;
				q.offer(new ScorePair(cur.x + 1, cur.y, cur, map[cur.x + 1][cur.y] - 48));
			}
			
			if(cur.x - 1 >= 0 && !used[cur.x - 1][cur.y] && map[cur.x - 1][cur.y] != '*'){
				//++nextCount;
				q.offer(new ScorePair(cur.x - 1, cur.y, cur, map[cur.x - 1][cur.y] - 48));
			}
			
			if(cur.y + 1 < n && !used[cur.x][cur.y + 1] && map[cur.x][cur.y + 1] != '*'){
				//++nextCount;
				q.offer(new ScorePair(cur.x, cur.y + 1, cur, map[cur.x][cur.y + 1] - 48));
			}
			
			if(cur.y - 1 >= 0 && !used[cur.x][cur.y - 1] && map[cur.x][cur.y - 1] != '*'){
				//++nextCount;
				q.offer(new ScorePair(cur.x, cur.y - 1, cur, map[cur.x][cur.y - 1] - 48));
			}
			//if(curCount == 0){
			//	curCount = nextCount;
			//	nextCount = 0;
				//++curLevel;
			//}
			printWalked(used);
		}
		return null;
	}
}