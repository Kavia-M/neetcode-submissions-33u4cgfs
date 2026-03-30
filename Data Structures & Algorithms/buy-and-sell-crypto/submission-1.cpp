class Solution_brute_force {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        int ans = 0;
        for(int i = 0; i<n; i++) {
            for(int j = i+1; j<n; j++) {
                ans = max(ans, prices[j] - prices[i]);
            }
        }
        return ans;
    }
};

/*
Slidign window - window size can be same or changing.
Here we keep one end of the window same and the size of it is increasing

Fixing i as selling price
This is a sliding window, but the starting of the window is always same
Hint 4 makes sense

each time sliding window, we compute minimum of the window, so this minimum
is the Buy price.
*/
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        // sell is prices[i]
        // buy is min of index 0 to i-1
        int n = prices.size();
        int ans = 0;
        int buy = prices[0];
        for(int i = 1; i<n; i++) {
            buy = (prices[i-1] < buy) ? prices[i-1] : buy;
            ans = ((prices[i] - buy) > ans) ? (prices[i] - buy) : ans;
        }
        return ans;
    }
};

