class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        // O(n log n)
        sort(nums.begin(), nums.end());

        int ans = 0;
        for(int i=1; i<nums.size(); i++) {
            if(nums[i-1] == nums[i]) 
                continue;
            if(nums[i-1] == nums[i]-1)
                ans++;
            else
                ans = 0;
        }
        return ans;
    }
};
