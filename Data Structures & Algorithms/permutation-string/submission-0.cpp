class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        sort(s1.begin(), s1.end());
        int n1 = s1.size();
        int n2 = s2.size();
        for(int i=0; (i+n1)<n2; i++) {
            string sub = s2.substr(i, n1);
            sort(sub.begin(), sub.end());
            if (s1==sub)
                return true;
        }
        return false;
    }
};
