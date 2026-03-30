class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        int n = nums.size();
        vector<int> left_product(n);
        vector<int> right_product(n);
        vector<int> ans(n);

        left_product[0] = nums[0];
        right_product[n-1] = nums[n-1];

        for(int i=1; i < n ; i++) {
            left_product[i] =  left_product[i-1] * nums[i];
            right_product[n-i-1] = right_product[n-i] * nums[n-i-1];
        }

        ans[0] = right_product[1];
        ans[n-1] = left_product[n-2];

        for(int i=1; i<n-1; i++) {
            ans[i] = left_product[i-1] * right_product[i+1];
        }
        return ans;
    }
};
