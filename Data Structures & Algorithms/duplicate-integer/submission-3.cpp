#include <bits/stdc++.h>

class Solution {
public:
    bool hasDuplicate(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        for (int n : nums)
            cout << n << " ";
        cout << endl;
        for(int i=0 ; i<nums.size(); i++) {
            for(int j=0; j<nums.size(); j++) {
                if((nums[i] == nums[j]) && (i!=j))
                    return true;
            }
        }
        return false;
    }
};