class Solution {
public:
    bool isPalindrome(string s) {
        // no need <= because = character is always equal, trivial
        for(int i=0, j=s.length()-1; i<j; i++, j--) {
            while(!(('A'<=s[i] && s[i]<= 'Z') ||  ('a'<=s[i] && s[i]<= 'z') || ('0'<=s[i] && s[i]<= '9'))) {
                cout << "skipping i " << i << " " << s[i] << "\n"; 
                i++;
            }

            while(!(('A'<=s[j] && s[j]<= 'Z') ||  ('a'<=s[j] && s[j]<= 'z') || ('0'<=s[j] && s[j]<= '9'))) {
                cout << "skipping j " << j << " " << s[j] << "\n"; j--;
            }
            
            if(i>j) 
                break;
            
            if(tolower(s[i]) != tolower(s[j])) {
                cout << "i and j " << i <<" " << j << "----" << s[i] << s[j] << "\n"; return false;
            }
        }

        return true;
    }
};
// "No lemon, no melon"