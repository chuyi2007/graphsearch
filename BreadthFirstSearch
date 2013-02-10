import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Pair{
	int x, y;
	Pair parent = null;
	public Pair(int x, int y, Pair parent){
		this.x = x;
		this.y = y;
		this.parent = parent;
	}
}
public class BreadthFirstSearch {
	public static void main(String[] args){
		char[][] map = {
				{'*', '*', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', '*', ' '},
				{' ', '*', ' ', '*', ' '},
				{' ', ' ', ' ', '*', ' '},
				{' ', ' ', ' ', '*', ' '},
				{' ', ' ', ' ', '*', ' '},
				{' ', ' ', ' ', ' ', ' '}};
		Pair S = new Pair(2, 0, null);
		Pair E = new Pair(7, 3, null);
		//reversed order
		List<Pair> shortestPath = BFS(map, S, E);
		System.out.println("Smallest Step number is: " + shortestPath.size());
		printPath(shortestPath, map.length, map[0].length);
	}
	
	public static void printPath(List<Pair> path, int m, int n){
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
		System.out.println();
		for(int i = 0; i < used.length; ++i){
			for(int j = 0; j < used[i].length; ++j){
				if(used[i][j])
					System.out.print(" . ");
				else
					System.out.print("   ");
			}
			System.out.println();
		}
		System.out.print("----------------");
	}
	public static List<Pair> BFS(char[][] map, Pair S, Pair E){
		//int curLevel = 0;
		Queue<Pair> q = new LinkedList<Pair>();
		int m = map.length, n = map[0].length;
		boolean[][] used = new boolean[m][n];
		q.offer(S);
		int curCount = 1;
		int nextCount = 0;
		while(q.size() != 0){
			Pair cur = q.poll();
			used[cur.x][cur.y] = true;
			--curCount;
			if(checkEqual(cur, E)){
				List<Pair> st = new ArrayList<Pair>();
				while(cur != null){
					st.add(cur);
					cur = cur.parent;
				}
				return st;
			}
			if(cur.x + 1 < m && !used[cur.x + 1][cur.y] && map[cur.x + 1][cur.y] != '*'){
				++nextCount;
				q.offer(new Pair(cur.x + 1, cur.y, cur));
			}
			
			if(cur.x - 1 >= 0 && !used[cur.x - 1][cur.y] && map[cur.x - 1][cur.y] != '*'){
				++nextCount;
				q.offer(new Pair(cur.x - 1, cur.y, cur));
			}
			
			if(cur.y + 1 < n && !used[cur.x][cur.y + 1] && map[cur.x][cur.y + 1] != '*'){
				++nextCount;
				q.offer(new Pair(cur.x, cur.y + 1, cur));
			}
			
			if(cur.y - 1 >= 0 && !used[cur.x][cur.y - 1] && map[cur.x][cur.y - 1] != '*'){
				++nextCount;
				q.offer(new Pair(cur.x, cur.y - 1, cur));
			}
			
			if(curCount == 0){
				curCount = nextCount;
				nextCount = 0;
				//++curLevel;
			}
			printWalked(used);
		}
		return null;
	}
}