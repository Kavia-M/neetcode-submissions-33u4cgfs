class Solution_Recurrsion {
    public int climbStairs(int n) {
        if(n==0) return 1;
        if(n==1) return 1;

        return climbStairs(n-1) + climbStairs(n-2);
    }
}

// Memoization
class Solution {
    private int countWaysToReachTop(int n, int[] dp) {
        if(n==0) return dp[n] = 1;
        if(n==1) return dp[n] = 1;

        if(dp[n]!=-1) return dp[n];

        return dp[n] = countWaysToReachTop(n-1, dp) + countWaysToReachTop(n-2, dp);
    }

    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return countWaysToReachTop(n, dp);
    }
}
