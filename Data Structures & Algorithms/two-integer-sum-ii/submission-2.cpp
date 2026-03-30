class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int i=0, j=numbers.size()-1;
        int sum = 0;
        vector<int> ans(2); // [0,0]
        while(i<j) {
            sum = numbers[i] + numbers[j];
            if(sum==target) {
                // 1 based index is expected
                ans[0] = i+1; // push_back will insert additional to existing [0,0]
                ans[1] = j+1;
                return ans;
            }
            if(sum<target) 
                i++;
            else // if(sum>target) // >= does not apply here because in case of == it returns
                j--;
        }
        return ans;
    }
};
