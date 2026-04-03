class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        // O(n log n)
        sort(nums.begin(), nums.end());

        int ans = 1;
        int max_ans = ans;

        for(int i=1; i<nums.size(); i++) {
            if(nums[i-1] == nums[i]) 
                continue;
            if(nums[i-1] == nums[i]-1)
                ans++;
            else {
                if(ans > max_ans) {
                    max_ans = ans;
                }
                ans = 1;
            }
        }
        if(ans > max_ans) {
            max_ans = ans;
        }
        return max_ans;
    }
};
