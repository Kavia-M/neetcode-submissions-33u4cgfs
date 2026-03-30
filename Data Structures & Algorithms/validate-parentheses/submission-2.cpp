/*
Vector push_back O(n)

Although some individual operations may take O(n), the average cost per operation over a sequence of operations is O(1).

That’s the clean definition.

⚠ Important

Amortized ≠ Average case

Average case → probability based

Amortized → mathematical accounting over sequence

Very different.

*/

class Solution {
public:
    bool isValid(string s) {
        stack<char> st;

        unordered_map<char, char> closeToOpen = {
            {')', '('},
            {'}', '{'},
            {']', '['}
        };

        for(char ch : s) {
            // if it is present as a key count gives one. 
            // non zero is true in if
            // closeToOpen.find(ch)!=closeToOpen.end()
            if(closeToOpen.count(ch)) {
                // non empty and top is open brachet
                if(!st.empty() && st.top()==closeToOpen[ch]) {
                    st.pop();
                }
                else
                    return false;
            }
            else { 
                // this is not closeing bracket
                st.push(ch);
            }
        }
        if(!st.empty()) {
            return false;
        }
        return true;
    }

    
};
