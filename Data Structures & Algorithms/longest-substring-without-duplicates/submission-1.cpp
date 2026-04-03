class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        unordered_map<char, int> index_map;
        int i = 0, j = 1, n = s.size();
        index_map[s[i]] = i;
        // window is from i, just before j, end j of window is not included
        int ans = j-i;
        while(i < j && j < n) {
            auto it = index_map.find(s[j]);
            if(it!=index_map.end()) {
                // the next letter is already in the window
                i = (it->second)+1;
            }
            // add j to window
            // we can safely move j one in any case
            // right side is excuted first and then left side,
            // so increment will be last
            index_map[s[j++]] = j;
            ans = max(ans, (j-i));
        }
        return ans;
    }
};
