class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int i=0, j=numbers.size()-1;
        int sum = 0;
        vector<int> ans(2); // [0,0]
        while(i<j) {
            sum = numbers[i] + numbers[j];
            if(sum==target) {
                ans[0] = numbers[i]; // push_back will insert additional to existing [0,0]
                ans[1] = numbers[j];
                return ans;
            }
            if(sum<target) 
                i++;
            else // if(sum>target)
                j--;
        }
        return ans;
    }
};
