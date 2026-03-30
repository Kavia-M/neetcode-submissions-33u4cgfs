class Solution {
public:
    bool isAnagram(string s, string t) {
        // unordered_map<char, int> count1;
        // for (char c : s) {
        //     count1[c]++;
        // }
        // unordered_map<char, int> count2;
        // for (char c : t) {
        //     count2[c]++;
        // }
        // if(count1==count2)
        //     return true;
        // return false; 

        int count[26] = {0};

        for(char c : s) {
            count[int(c)-97]++;
        }
        for(char c : t) {
            count[int(c)-97]--;
        }

        for(int cnt : count) {
            if(cnt!=0) {
                return false;
            }
        }
        return true;
    }
};
