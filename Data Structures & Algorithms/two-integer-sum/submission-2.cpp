class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        // vector<int> a;
        // for(int i=0; i<nums.size(); i++) {
        //     for(int j=0; j<nums.size(); j++) {
        //         if(j!=i && nums[i]+nums[j]==target) {
        //             a.push_back(i);
        //             a.push_back(j);
        //             return a;
        //         }
        //     }
        // }
        // return a;

        // ----- hint 2
        unordered_map<int, int> mp;
        //vector<int> result;
        for(int i=0; i<nums.size(); i++) {
            if (mp.find(target-nums[i]) != mp.end()) {
                //result = {mp[target-nums[i]], i};
                return {mp[target-nums[i]], i}; 
            }
            else {
                // add to the map
                mp[nums[i]]=i;
            }
        }
        return result;
    }
};
