// because the path can go left side or upside also like zig zag also (but not diagonally)
class Solution_WRONG {
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

// From STRIVER's explanation without seeing his code
class Solution_Striver {
    public boolean exist(char[][] board, String word) {
        if(board == null) {
            if(word == null) return true;
            return false;
        }
        if(board.length==0) {
            if(word.length() == 0) return true;
            return false;
        }
        // start where the 1st character match
        for(int i = 0; i<board.length; i++) {
            for(int j =0; j<board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(check(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // check if k index element in word match and proceed
    private boolean check(char[][] board, String word, int i, int j, int k) {
        // check the true cases 1st because at the boarder of matrix check also it may be matched already
        // if the kth index is '\0' end of string it means we already matched the whole string
        
        // In Java, strings are  not null-terminated with a \0
        // No Sentinel Character: Java does not use a special character to mark the end of a string. The \0 character (Unicode \u0000) is treated as a regular, non-printable character and can exist anywhere within a Java string without terminating it.

        if(k == word.length()) {
            return true;
        }

        // no need >= or <= since we move one step at a time
        if(i==board.length || i==-1) return false;
        if(j==board[0].length || j==-1) return false;

        // if this cell is already visited return false as we cannot reuse it
        if(board[i][j] == '\0') return false;

        // check if current character match
        if(board[i][j] != word.charAt(k)) return false;

        // we need to ensure not moving back to already used cell in this current recurrsion or else we would be in infinite loop possible
        char ch = board[i][j];
        board[i][j] = '\0';

        boolean ans = (check(board, word, i+1, j, k+1) ||
                        check(board, word, i-1, j, k+1) ||
                        check(board, word, i, j+1, k+1) ||
                        check(board, word, i, j-1, k+1)); // do not change both i and j as that would lead to diagonal search

        // backtrack the masking
        board[i][j] = ch;

        return ans;   
    }
}

// -----------------------------------------------------------------------
// Given solutuion 1 

class Solution_backtracking_hashset {
    // 1. Set of Arrays: Set<int[]>
    // You can declare a Set that holds integer arrays, but it is generally not recommended because arrays in Java do not override hashCode() or equals(). This means two different array objects with the same content will be treated as unique by a HashSet. 

    Set<Pair<Integer, Integer>> path = new HashSet<>(); 
    public boolean exist(char[][] board, String word) {
        if(board == null) {
            if(word == null) return true;
            return false;
        }
        if(board.length==0) {
            if(word.length() == 0) return true;
            return false;
        }
        if(word == null || word.length()==0) {
            // this may exist in any non empty board
            return true;
        }

        // check starting at each and every cell
        for(int r = 0; r<board.length; r++)
            for(int c=0; c<board[r].length; c++)
                if(dfs(board, word, r, c, 0)) return true;
        
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int i) {
        if(i==word.length()) {
            // we already checked all characters in word
            return true;
        }

        // r or c out of bound  OR
        // this cell already visited and stored in path set  OR
        // this cell content not matching the given index i in word
        if(r<0 || c<0 || r>=board.length || c>=board[0].length
            || path.contains(new Pair<>(r,c)) || board[r][c] != word.charAt(i))
            return false;
        
        // very important, or else we get into infinite loop
        path.add(new Pair<>(r, c));
        // we already checked, now this not board[r][c] != word.charAt(i)

        // move only Up down left right, not diagonally
        // but always move to next character in the word
        boolean ans = dfs(board, word, r+1, c, i+1) ||
                      dfs(board, word, r-1, c, i+1) ||
                      dfs(board, word, r, c+1, i+1) ||
                      dfs(board, word, r, c-1, i+1);

        // backtrack hashset to explore other paths by the caller func in recurrsion
        // To ensure set.remove(new Pair(x, y)) works, the Pair class must override equals() and hashCode(). This allows the Set to identify objects by their data values rather than their memory address.
        path.remove(new Pair<>(r, c));

        return ans;
    }
}

// in exist func  for loop. m times, m is no. of cells in board
// each call to dfs from exists function, run for atmost n cells in board where n is number of characters in word
// for these n recursive calls, 4 possible decisions. 4-nary tree formed with height of n. after n it will return true or false
// time complexity O(m *  4^n)
// space complexity O(n) recurrsive stack space, O(n) for hashset at most elements in it, no space complexity for output

// -------------------------------------------------------------------------------------------------

// GIVEN SOLUTION 2. backtracking with visited array (or matrix)
// apart from given solution, here we try with straight array for visited
// index is straight 1D array is (r*board[0].length + c)
class Solution {
    public boolean exist(char[][] board, String word) {
        if(board == null) {
            if(word == null) return true;
            return false;
        }
        if(board.length==0) {
            if(word.length() == 0) return true;
            return false;
        }
        if(word == null || word.length()==0) {
            // this may exist in any non empty board
            return true;
        }

        boolean[] visited = new boolean[board.length * board[0].length]; // all elements are false by default, primitive datatype array

        for(int r=0; r<board.length; r++) {
            for(int c=0; c<board[0].length; c++) {
                if(dfs(board, word, visited, r, c, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, boolean[] visited, int r, int c, int i) {
        if(i==word.length()) return true;

        if(r<0 || c<0 || r==board.length || c==board[0].length || 
            visited[r*board[0].length + c] || board[r][c]!=word.charAt(i))
            return false;
        
        visited[r*board[0].length + c] = true;

        boolean ans = dfs(board, word, visited, r+1, c, i+1) ||
                      dfs(board, word, visited, r-1, c, i+1) ||
                      dfs(board, word, visited, r, c+1, i+1) ||
                      dfs(board, word, visited, r, c-1, i+1);

        visited[r*board[0].length + c] = false;

        return ans;
    }
}

