class Solution_brute_force {
public:
    int longestConsecutive(vector<int>& nums) {
        // Longest seq not possible in empty array
        if (nums.empty()) return 0;

        // O(n log n)
        sort(nums.begin(), nums.end());

        // non empty arrays
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


class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        // Longest seq not possible in empty array
        if (nums.empty()) return 0;

        unordered_set<int> nums_set(nums.begin(), nums.end());

        unordered_set<int> start_of_sequences;

        for(auto &num : nums_set) {
            if(nums_set.find(num-1) != nums_set.end())
                start_of_sequences.insert(num);
        }

        // non empty arrays
        int ans = 1;
        int max_ans = ans;

        // outer loop O(n)
        for(auto &start_num : start_of_sequences) {
            int num = start_num;
            ans = 1;

            while(nums_set.find(num++) != nums_set.end()) {
                ans++;
            }
            max_ans = max(max_ans, ans);
        }
        max_ans = max(max_ans, ans);

        return max_ans;
    }
};
