class Solution {
public:
    int maxArea(vector<int>& heights) {
        // Consider the bars are placed unit distance apart
        int ans = 0;
        for(int i =0; i<heights.size(); i++) {
            for(int j = i+1; j<heights.size(); j++) {
                ans = max(ans, (j-i)*(min(heights[i], heights[j])));
            }
        }
        return ans;
    }
};
