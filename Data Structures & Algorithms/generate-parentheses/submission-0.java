class Solution {
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
