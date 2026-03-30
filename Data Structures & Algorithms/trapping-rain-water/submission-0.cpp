class Solution {
public:
    int trap(vector<int>& height) {
        // water will be stored in particular vertical space only
        // if heights of any bars before and after bars are higher than this one
        int n = height.size();

        vector<int> tallest_before(n, 0);
        vector<int> tallest_after(n, 0);
        vector<int> storage(n, 0);


        for(int i =0; i<n; i++) {
            for(int j = 0; j<=i; j++) {
                tallest_before[i] = max(tallest_before[i], height[j]);
            }

            for(int j = i; j<n; j++) {
                tallest_after[i] = max(tallest_after[i], height[j]);
            }

            storage[i] = abs(min(tallest_before[i], tallest_after[i]) - height[i]);
        }

        int ans = 0;
        for(int i =0; i<n; i++) {
            ans += storage[i];
        }

        return ans;
    }
};
