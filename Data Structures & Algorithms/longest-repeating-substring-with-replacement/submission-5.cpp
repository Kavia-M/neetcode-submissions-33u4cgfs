class Solution_bruteForce {
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

class Solution {
public:
    int characterReplacement(string s, int k) {
        // answer is in worst case equal to k only
        int n = s.size(), ans = k;
        int i = 0, j = 0;
        vector<int> freq(26, 0);
        
        int max_freq=1;
        int letter, max_letter;

        while(i<=j && j < n) {
            letter = s[j] - 'A';
            freq[letter]++;
            if(freq[letter] > max_freq) {
                max_freq = freq[letter];
                max_letter = letter;
            }
            if(j-i+1 - max_freq <= k)  {
                // we can still move forward
                ans = max(ans, (j-i+1));
                j++;
            }
            else {
                freq[(s[i] - 'A')]--;
                if((s[i] - 'A') == max_letter) {
                    max_freq--;
                }
                i++;
                j++;
            }
        }

        return ans;
    }
};
