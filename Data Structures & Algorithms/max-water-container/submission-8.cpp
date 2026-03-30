class Solution {
public:
    int maxArea(vector<int>& heights) {
        // Consider the bars are placed unit distance apart
        
        /*
        // Brute Force
        int ans = 0;
        for(int i =0; i<heights.size(); i++) {
            for(int j = i+1; j<heights.size(); j++) {
                ans = max(ans, (j-i)*(min(heights[i], heights[j])));
            }
        }
        return ans;

        */

        int i =0, j=heights.size()-1;
        int ans = (j-i)*(min(heights[i], heights[j]));

        // while(i<j) {
        //     if(heights[i] < heights[j]) {
        //         // heights [i] is shorter 
        //         for(int ii=i; ii<j; ii++) {
        // }
//        if(heights[i] < heights[j]) {
            // i is minimum
            for(int ii=i; ii<j; ii++) {
                if(heights[ii] > heights[i]) {
                    i = ii;
                    ans = max(ans, (j-i)*(min(heights[i], heights[j])));
                }
            }
//        }
//        else {
            for(int jj=j; jj>i; jj--) {
                if(heights[jj] > heights[j]) {
                    j=jj;
                    ans = max(ans, (j-i)*(min(heights[i], heights[j])));
                }
            }
//        }
        return ans;
    }
};
