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
class Solution_BruteForce {
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
// 2n slots to be filled. each has 2 choices ( or )
// time complexity ( 2n * (2^(2n)) )

//--------------------------------------------------------

// Given solution 2 - backtracking
// StringBuilder gives modifyable string
class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        StringBuilder s = new StringBuilder();
        backtrack(0, 0, s, n);
        return ans;
    }

    private void backtrack(int open, int close, StringBuilder s, int n) {
        // base condition
        if(open == close && open == n) {
            ans.add(s.toString());
            return;
        }

        if(open<n) {
            // + cannot be used in string builder
            // append returns reference to current strignbuilder after appending
            backtrack(open+1, close, s.append('('), n);
            s.deleteCharAt(s.length()-1);
        }

        if(close < open) { // still we can add ) until it becomes == to open
            backtrack(open, close+1, s.append(')'), n);
            s.deleteCharAt(s.length()-1);
        }
    }
}