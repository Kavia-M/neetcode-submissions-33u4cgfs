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

        int ans = 0;
        int i =0, j=heights.size()-1;
//        if(heights[i] < heights[j]) {
            // i is minimum
            for(int ii=i; ii<j; ii++) {
                if(heights[ii] - heights[i] > (ii-i)) {
                    i=ii;
                    cout<<i<<'\n';
                }
            }
//        }
//        else {
            for(int jj=j; jj>i; jj--) {
                if(heights[jj] - heights[j] > (j-jj)) 
                    j=jj;
            }
//        }
        return (j-i)*(min(heights[i], heights[j]));
    }
};
