class Solution {
public:
    int characterReplacement(string s, int k) {
        // answer is in worst case equal to k only
        int n = s.size(), ans = k;
        for(int i = 0; i < n; i++) {
            vector<int> freq(26, 0);
            int max_freq = 1;

            for(int j = i; j < n; j++) {
                int letter = s[j] - 'A';
                freq[letter]++;
                max_freq = max(max_freq, freq[letter]);
                if(j-i+1 - max_freq <= k)    
                    ans = max(ans, (j-i+1));
            }
        }
        return ans;
    }
};
