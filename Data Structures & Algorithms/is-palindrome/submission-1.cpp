class Solution {
public:
    bool isPalindrome(string s) {
        // no need <= because = character is always equal, trivial
        for(int i=0, j=s.length()-1; i<j; i++, j--) {
            if(!(('A'<=s[i] && s[i]<= 'Z') ||  ('a'<=s[i] && s[i]<= 'z') || ('0'<=s[i] && s[i]<= '9'))) 
                i++;

            if(!(('A'<=s[j] && s[j]<= 'Z') ||  ('a'<=s[j] && s[j]<= 'z') || ('0'<=s[j] && s[j]<= '9'))) 
               j--;
            
            if(i>j) 
                break;
            
            if(tolower(s[i]) != tolower(s[j])) 
                return false;
        }

        return true;
    }
};