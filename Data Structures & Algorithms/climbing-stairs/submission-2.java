class Solution_Recurrsion {
    public int climbStairs(int n) {
        if(n==0) return 1;
        if(n==1) return 1;

        return climbStairs(n-1) + climbStairs(n-2);
    }
}

// Memoization
class Solution_Memoization {
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

// Tabulation
class Solution {
    private int countWaysToReachTop(int n, int[] dp) {
        if(n==0) return dp[n] = 1;
        if(n==1) return dp[n] = 1;

        if(dp[n]!=-1) return dp[n];

        return dp[n] = countWaysToReachTop(n-1, dp) + countWaysToReachTop(n-2, dp);
    }

    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
