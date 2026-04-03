class Solution_last_question {
public:
    void printFreq(vector<int> &vec) {
        for(auto i : vec) {
            cout<<i<<' ';
        }
        cout<<endl;
    }
    bool checkInclusion(string s1, string s2) {
       
        int n1 = s1.size();
        int n2 = s2.size();
        vector<int> s1_freq(26,0), s2_freq(26,0);

        if(n1>n2) 
            return false;

        for(int i=0; i<n1; i++) {
            s1_freq[s1[i] - 'a']++;
        }

        int matches = 0;
        
        for(int k=0; k<n1; k++)
            s2_freq[s2[k] - 'a']++;
        
        for(int c=0; c<26; c++)
            if(s1_freq[c] == s2_freq[c])
                matches++;

        // for safety in below loop check condition we check n1>n2 return
        for(int i=0, j=n1; j<n2; i++, j++) {
            // this is the i which is being removed
            // this is the j which is being added
            
            // always check matches = =26
            if(matches == 26)
                return true;
            
            if(s2_freq[s2[i] - 'a'] == s1_freq[s2[i] - 'a']) {
                // already matched, so match will reduce now
                // s2_freq[s2[i] - 'a']--;
                matches--;
            }
            // decrement in either if else condition
            s2_freq[s2[i] - 'a']--;
            if(s2_freq[s2[i] - 'a'] == s1_freq[s2[i] - 'a']) {
                // this will execute only if the 1st if of similar one is false
                // if that 'if' was true, the above decrement would now result in !=
                matches++;
            }


            // j addition
            // similar to i
            
            if(s2_freq[s2[j] - 'a'] == s1_freq[s2[j] - 'a']) {
                // already matched, so match will reduce now
                // s2_freq[s2[i] - 'a']--;
                matches--;
            }
            // increment in either if else condition
            s2_freq[s2[j] - 'a']++;
            if(s2_freq[s2[j] - 'a'] == s1_freq[s2[j] - 'a']) {
                // this will execute only if the 1st if of similar one is false
                // if that 'if' was true, the above increment would now result in !=
                matches++;
            }
        }

        // check at last for last one
        if(matches==26)
            return true;
        
        return false;
    }
};

class Solution {
public:
    int get_index(char a) {
        // A is 65
        // a is 97
        if(a - 'A' > 26) {
            // small characters
            return 26 + (a-'a');
        }
        return a-'A';
    }
    int get_matches(vector<int>& s_freq, vector<int>& t_freq) {
        int matches = 0;
        for(int c=0; c<52; c++) {
            if(s_freq[c] >= t_freq[c]) {
                matches++;
            }
        }
        return matches;
    }
    void update_start_end(int& start, int& end, int i, int j) {
        int length = end-start+1;
        if(length > (j-i+1)) {
            start = i;
            end = j;
        }
    }
    string minWindow(string s, string t) {
        vector<int> t_freq(52,0);

        int n1 = s.size(), n2 = t.size();
        
        if(n2>n1) {
            return "";
        }

        int start=-1, end=n1; // end is 1 more than last index

        for(int ind = 0; ind<n2; ind++) {
            t_freq[get_index(t[ind])]++;
        }
        int i=0, j=0;
        for(i=0; i<=n1-n2; i++) {
            vector<int> s_freq(52,0);
            for(j=i; j<i+n2; j++) {
                s_freq[get_index(s[j])]++;
            }
            int matches = get_matches(s_freq, t_freq);
            if(matches == 52) {
                update_start_end(start, end, i, j);
            //    cout<< start<< ' ' << end << endl;
            }
                
            for(j=i+n2; j<n1; j++) {
                cout<<"j  " << j << endl;
                bool dont_update = false;
                if(s_freq[get_index(s[j])] >= t_freq[get_index(s[j])]) {
                    dont_update = true;
                }
                s_freq[get_index(s[j])]++;
                if(s_freq[get_index(s[j])] >= t_freq[get_index(s[j])] && !dont_update) {
                    matches++;
                }

                if(matches == 52) {
                    update_start_end(start, end, i, j);
                    cout<< start<< ' ' << end << endl;

                }
            }
        }
        // cout<< "last" << i << ' ' << j <<endl;
        // if(get_matches(s_freq, t_freq) == 52) {
        //             update_start_end(start, end, i, j);
        //             cout<< start<< ' ' << end << endl;

        //         }        
        if(start==-1 && end==n1) {
            return "";
        }

        string ans = "";

        for(int ind = start; ind<=end; ind++) {
            ans+=s[ind];
        }
        return ans;
    }
};

// class Solution_2 {
// public:

//     int get_index(char a) {
//         // A is 65
//         // a is 97
//         if(a - 'A' > 26) {
//             // small characters
//             return 26 + (a-'a');
//         }
//         return a-'A';
//     }
//     string minWindow(string s, string t) {
//         vector<int> s_freq(52,0), t_freq(52,0);

//         int n1 = s.size(), n2 = t.size();

//         if(n2>n1) {
//             return "";
//         }

//         // t
//         for(int i=0; i<n2; i++) {
//             t_freq[get_index(t[i])]++;
//         }

//         // this will be increased if an alphabet freq in s is >= that in t
//         // should be equal to 52 
//         int matches = 0;

//         for(int k=0; k<n2; k++)
//             s_freq[get_index(t[k])]++;
        
//         for(int c=0; c<56; c++)
//             if(s_freq[c] >= t_freq[c])
//                 matches++;

//         // for safety in below loop check condition we check n2>n1 return
//         // increment will be done inside 
//         for(int i=0, j=n2; j<n1;) {
//             // this is the i which is being removed
//             // this is the j which is being added
            
//             // always check matches = =26
//             if(matches == 26)
//                 return true;
            
//             if(s2_freq[s2[i] - 'a'] == s1_freq[s2[i] - 'a']) {
//                 // already matched, so match will reduce now
//                 // s2_freq[s2[i] - 'a']--;
//                 matches--;
//             }
//             // decrement in either if else condition
//             s2_freq[s2[i] - 'a']--;
//             if(s2_freq[s2[i] - 'a'] == s1_freq[s2[i] - 'a']) {
//                 // this will execute only if the 1st if of similar one is false
//                 // if that 'if' was true, the above decrement would now result in !=
//                 matches++;
//             }


//             // j addition
//             // similar to i
            
//             if(s2_freq[s2[j] - 'a'] == s1_freq[s2[j] - 'a']) {
//                 // already matched, so match will reduce now
//                 // s2_freq[s2[i] - 'a']--;
//                 matches--;
//             }
//             // increment in either if else condition
//             s2_freq[s2[j] - 'a']++;
//             if(s2_freq[s2[j] - 'a'] == s1_freq[s2[j] - 'a']) {
//                 // this will execute only if the 1st if of similar one is false
//                 // if that 'if' was true, the above increment would now result in !=
//                 matches++;
//             }
//         }

//     }
// };
