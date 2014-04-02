package DesignExcercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameOfLife {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean[][] grid = {{false, false, false, false, false},
			{false, false, true, false, false},
			{false, false, true, false, false},
			{false, false, true, false, false},
			{false, false, false, false, false}};
		
		int m = grid.length, n = grid[0].length;
		Set<GameOfLifePair> pairSet = new HashSet<GameOfLifePair>();
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (grid[i][j]) {
					pairSet.add(new GameOfLifePair(i, j));
				}
			}
		}
		print(pairSet);
		for (int i = 0; i < 5; ++i) {
			pairSet = gameOfLife(pairSet);
			print(pairSet);
		}
	}
	
	public static void print(Set<GameOfLifePair> pairSet) {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				if (pairSet.contains(new GameOfLifePair(i, j))) {
					System.out.print("*");
				}
				else 
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static List<GameOfLifePair> getNeighbors(GameOfLifePair p) {
		List<GameOfLifePair> pairList = new ArrayList<GameOfLifePair>();
		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				if (i != 0 || j != 0) {
					pairList.add(new GameOfLifePair(p.x - i, p.y - j));
				}
			}
		}
		return pairList;
	}
	public static Set<GameOfLifePair> gameOfLife(Set<GameOfLifePair> pairSet) {
		
		Set<GameOfLifePair> nextGen = new HashSet<GameOfLifePair>();
		for (GameOfLifePair p : pairSet) {
			List<GameOfLifePair> neighbors = getNeighbors(p);
			int count1 = 0;
			for (GameOfLifePair ne : neighbors) {
				if (pairSet.contains(ne)) {
					++count1;
				}
				int count2 = 0;
				for (GameOfLifePair x : getNeighbors(ne)) {
					if (pairSet.contains(x)) {
						++count2;
					}
				}
				if (count2 == 3) {
					nextGen.add(ne);
				}
			}
			if (!(count1 != 2 && count1 != 3)) {
				nextGen.add(p);
			}
		}
		return nextGen;
	}
}

class GameOfLifePair {
	int x;
	int y;
	public GameOfLifePair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameOfLifePair other = (GameOfLifePair) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
