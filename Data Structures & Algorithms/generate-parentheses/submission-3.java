class Solution_Striver {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generate("", ans, 0, 0, n);
        return ans;
    }
    private void generate(String s, List<String> ans, int open, int close, int n) {
        // base condition
        if(open > n) {
            return;
        }

        if(open+close == 2*n) {
            ans.add(s);
            return;
        }

        if(open > close) 
            generate(s+")", ans, open, close+1, n);
        
        generate(s+"(", ans, ++open, close, n);
    }
}

// -------------------------------------------------------

// Given solution 1 - brute force
// generate all paranthesis and check at last, check just before adding to ans
class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        addParenthesis("", n);
        return ans;
    }

    private boolean check(String p) {
        int balance = 0;
        for(int i=0; i<p.length(); i++) {
            if(p.charAt(i) == '(') balance++;
            else if(p.charAt(i)==')') balance--;
            else return false;

            if(balance<0) return false;
        }
        return balance==0; // if there is extra (  this will check
    }

    private void addParenthesis(String s, int n) {
        if(s.length() == 2*n) {
            if(check(s)) ans.add(s);
            return;
        }
        
        // if we send s+'(' no need to backtrack
        addParenthesis(s+'(', n);
        addParenthesis(s+')', n);
    }
}