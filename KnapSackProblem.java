public class KnapsackProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] w = {15, 10, 12, 25, 17};
		int[] v = {10, 15, 30, 50, 40};
		int n = w.length;
		int W = 50;
		
		int[][] dp = new int[n + 1][W + 1];
		for(int i = 1; i <= n; ++i){
			for(int j = 1; j <= W; ++j){
				if(j >= w[i - 1])
					dp[i][j] = Math.max(dp[i - 1][j], 
							dp[i - 1][j - w[i - 1]] + v[i - 1]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		System.out.println("Max value is: " + dp[n][W]);
	}

}