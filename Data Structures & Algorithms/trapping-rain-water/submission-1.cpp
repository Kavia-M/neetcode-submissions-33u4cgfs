class Solution_bruteForce {
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


class Solution {
public:
    int trap(vector<int>& height) {
        // water will be stored in particular vertical space only
        // if heights of any bars before and after bars are higher than this one
        int n = height.size();

        vector<int> tallest_before(n, 0);
        vector<int> tallest_after(n, 0);
        vector<int> storage(n, 0);

        // Precompute without nested for loop
        int prefix_max  = 0;
        for(int i =0; i<n; i++) {
            prefix_max = max(prefix_max, height[i]);
            tallest_before[i] = prefix_max;
        }

        int suffix_max = 0;
        for(int i=n-1; i>=0; i--) {
            suffix_max = max(suffix_max, height[i]);
            tallest_after[i] = suffix_max;
        }

        for(int i =0; i<n; i++) {
            storage[i] = min(tallest_before[i], tallest_after[i]) - height[i];
            cout<<storage[i] << '\n';
        }

        int ans = 0;
        for(int i =0; i<n; i++) {
            ans += storage[i];
        }

        return ans;
    }
};
