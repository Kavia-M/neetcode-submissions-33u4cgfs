class Solution {
public:
    bool isAnagram(string s, string t) {
 
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

    string getFreqArr(string s) {
        vector<int> freq(26,0);
        for(char c : s) {
            freq[int(c)-97]++;
        }

        string key;
        for (int f : freq) {
            key += "#" + to_string(f);
        }

        return key;
    }
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> anagrams;

        for(string s : strs) {
            string freq = getFreqArr(s);
            anagrams[freq].push_back(s);
        }

        vector<vector<string>> ans;
        for (auto &vec : anagrams) {
            ans.push_back(vec.second);
        }

        return ans;
    }
};
