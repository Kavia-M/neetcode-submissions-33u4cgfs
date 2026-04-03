class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        unordered_map<char, int> index_map;
        // edge cases, side 0, 1
        // size 1 is taken care in while stmt j 1 is not less than n 1
        int i = 0, n = s.size();
        int j = (s.empty()) ? 0 : 1;
        index_map[s[i]] = i;
        // window is from i, just before j, end j of window is not included
        int ans = j-i;
        while(i < j && j < n) {
            auto it = index_map.find(s[j]);
            if(it!=index_map.end()) {
                // the next letter is already in the window
                cout << it -> first;
                i = (it->second)+1;
            }
            // add j to window
            // we can safely move j one in any case
            // right side is excuted first and then left side,
            // so increment will be last
            cout << " i " << i << " j " << j << endl;
            index_map[s[j++]] = j;
            ans = max(ans, (j-i));
        }
        // last j is not inclusive index, so we need to do one computation in last
        //ans = max(ans, (j-i));
        return ans;
    }
};
