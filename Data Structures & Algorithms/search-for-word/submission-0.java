class Solution {
    int n1, n2, n3;
    public boolean exist(char[][] board, String word) {
        n1 = board.length;
        if(n1 == 0) return false;
        n2 = board[0].length;
        n3 = word.length();

        return check("", board, word, 0, 0);
    }

    private boolean check(String ans, char[][] board, String word, int i, int j) {
        if(i>=n1) return false;
        if(j>=n2) return false;

        if(ans.length() == n3) {
            if(ans.equals(word)) {
                System.out.println(ans);
                return true;
            }
            return false;
        }

        // like DFS, left child is moving down and right child is moving right side
        // TAKE it or not take it

        // 4 choices
        return (
            check(ans + board[i][j], board, word, i+1, j) ||
            check(ans + board[i][j], board, word, i, j+1) ||
            check("", board, word, i+1, j) ||
            check("", board, word, i, j+1)
        );
    }
}
