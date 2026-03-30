class Solution_sort {
public:
    bool checkInclusion(string s1, string s2) {
        sort(s1.begin(), s1.end());
        int n1 = s1.size();
        int n2 = s2.size();
        for(int i=0; (i+n1)<=n2; i++) {
            string sub = s2.substr(i, n1);
            sort(sub.begin(), sub.end());
            cout<<s1<<' '<<sub<<endl;
            if (s1==sub)
                return true;
        }
        return false;
    }
};

class Solution_map {
public:
    bool checkInclusion(string s1, string s2) {
        unordered_map<char, int> s1_freq;
        
        int n1 = s1.size();
        int n2 = s2.size();
        
        for(int i=0; i<n1; i++) {
            s1_freq[s1[i]]++;
        }

        for(int i=0; (i+n1)<=n2; i++) {
            unordered_map<char, int> sub_freq;
            for(int j=i; j<(i+n1); j++) {
                sub_freq[s2[j]]++;
            }
            if (s1_freq==sub_freq)
                return true;
        }
        return false;
    }
};

class Solution_Wrong {
public:
    void printFreq(vector<int> &vec) {
        for(auto i : vec) {
            cout<<i<<' ';
        }
        cout<<endl;
    }
    bool checkInclusion(string s1, string s2) {
        vector<int> freq(26,0);
        
        int n1 = s1.size();
        int n2 = s2.size();
        unordered_set<int> indices;

        if(n1>n2) 
            return false;

        for(int i=0; i<n1; i++) {
            freq[s1[i] - 'a']++;
        }
        printFreq(freq);
        int sum = n1;
        int i = 0;
        int j = i+n1; // exclusive
        for(int k=i; k<j; k++) {
            if(freq[s2[k] - 'a'] > 0) {
                freq[s2[k] - 'a']--;
                sum--;
                indices.insert(k);
            }
            else {
                freq[s2[k] - 'a']++;
                sum++;
            }
            printFreq(freq);
            cout<<sum<<endl;
            if(sum==0) {
                return true;
            }
        }
        for(int i=0; j<n2; i++, j++) {
            if(indices.find(i) != indices.end()) {
                freq[s2[i] - 'a']++;
                sum++;
            }
            else {
                freq[s2[i] - 'a']--;
                if(sum>0)
                    sum--;
            }

            if(freq[s2[j] - 'a'] > 0) {
                freq[s2[j] - 'a']--;
                sum--;
                indices.insert(j);
            }
            else {
                freq[s2[j] - 'a']++;
                sum++;
            }
            cout<<sum<<endl;
            if(sum==0) {
                return true;
            }
        }
        return false;
    }
};

class Solution {
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