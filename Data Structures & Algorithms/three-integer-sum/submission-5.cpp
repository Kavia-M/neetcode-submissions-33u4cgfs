class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        // O(n logn) sorting
        sort(nums.begin(), nums.end());

        int sum=0, target=0;

        vector<int> triplet(3);
        vector<vector<int>> ans;
        
        // for(int i = 0, j = nums.size() - 1; i<j; i++, j--)  {
        //     sum = nums[i] + nums[j];
        //     for(int k=i+1; k<j; k++) {
        //         if(i!=k && j!=k && nums[k]==-sum) {
        //             triplet[0] = nums[i];
        //             triplet[1] = nums[j];
        //             triplet[2] = nums[k];
        //             ans.push_back(triplet);
        //         }
        //     }
        // }

        // unordered_set<vector<int>> ans_set;

        for(int i=0; i<nums.size(); i++) {
            if (nums[i] > 0) break;
            if(i>0 && nums[i]==nums[i-1]) continue;
            int j=i+1, k=nums.size()-1;
            
            while(j<k) {
                sum = nums[j] + nums[k];
                target = -nums[i];
                if(sum==target) {
                    triplet[0] = nums[i];
                    triplet[1] = nums[j];
                    triplet[2] = nums[k];
                    ans.push_back(triplet);
                    j++;
                    k--;
                    
                }

                if(sum<target) 
                    j++;
                else if(sum>target)
                    k--;
                while (j< k && nums[j] == nums[j - 1]) 
                        j++;
            }
        }
        return ans;
    }
};
