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

        for(char ch : s) {
            if(ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            }
            else {
                if(st.empty())
                    return false;
                if(ch == ')') {
                    if(st.top() != '(') 
                        return false;
                    st.pop();
                }
                if(ch == '}') {
                    if(st.top() != '{') 
                        return false;
                    st.pop();
                }
                if(ch == ']') {
                    if(st.top() != '[') 
                        return false;
                    st.pop();
                }                                
            }
        }
        if(!st.empty())
            return false;
        return true;
    }

    
};
