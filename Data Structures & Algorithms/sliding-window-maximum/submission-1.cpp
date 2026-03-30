class Solution_brute_force_1 {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> ans;
        for(int i=0, j=k ; j<=nums.size() ; i++, j++) { // <= used since k j is exclusive end of window
            int maximum = INT_MIN;
            for(int x=i; x<j; x++) {
                maximum = max(maximum, nums[x]);
            }
            ans.push_back(maximum);
        }
        return ans;
    }
};

class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> ans;
        // k <= size of nums already given in condition
        for(int i=0, j=k-1 ; j<nums.size() ; i++, j++) { // j is inclusive index
            int maximum = INT_MIN;
            for(int x=i; x<=j; x++) {
                maximum = max(maximum, nums[x]);
            }
            ans.push_back(maximum);
        }
        return ans;
    }
};
