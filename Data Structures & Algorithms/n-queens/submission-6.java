class Solution_First_my_sol {
    
    private List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<String> board = new ArrayList<>(n); // though this capacity is not strict, it may increase if we add more dynamically

        if(n<0) return ans; // == 0 means we need [[]] as ans. one board with no rows inside ans
        
        IntStream.range(0, n).forEach(i -> {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            String result = new String(chars);
            board.add(result);
        });
        fillBoard(board, 0);
        return ans;
    }

    private void fillBoard(List<String> board, int rowNum) {
        if(rowNum == board.size()) {
            ans.add(new ArrayList<>(board));
            return;    
        }

        for(int colNum = 0; colNum<board.get(0).length(); colNum++) {
            if(isValidIndexForQueen(rowNum, colNum, board)) {
                String original = board.get(rowNum);
                // substring(int beginIndex, int endIndex) ending excluded. ending not given means till last
                String updated = original.substring(0, colNum) + 'Q' + original.substring(colNum + 1); // 'Q' converted to "Q" by java
                board.set(rowNum, updated);
            
                fillBoard(board, rowNum + 1);

                board.set(rowNum, original);
            }
        }
    }

    private boolean isValidIndexForQueen(int i, int j, List<String> board) {
        // since we are filling from top to bottom each row, no need to check for row, no need to check in down directions
        
        if(!isNotoutOfBound(i, j, board)) return false;
        
        return checkUp(i, j, board) && checkUpLeft(i, j, board) && checkUpRight(i, j, board);
    }

    private boolean isNotoutOfBound(int i, int j, List<String> board) {
        if(board == null) return false;
        int rows = board.size();
        if(rows==0) return false;

        int cols = board.get(0).length();

        if(i<0 || i>=rows) return false;
        if(j<0 || j>=cols) return false;
        
        return true;
    }

    private boolean checkUp(int i, int j, List<String> board) {
        if(!isNotoutOfBound(i, j, board)) return false;
        for(int rowInd = 0; rowInd<i; rowInd++) {
            if(board.get(rowInd).charAt(j) == 'Q') return false;
        }
        return true;
    }

    private boolean checkUpLeft(int i, int j, List<String> board) {
        if(!isNotoutOfBound(i, j, board)) return false;
        for(int rowInd = i-1, colInd = j-1; isNotoutOfBound(rowInd, colInd, board) ; rowInd--, colInd--) {
            if(board.get(rowInd).charAt(colInd) == 'Q') {
                return false;
            }
        }
        return true;
    }

    private boolean checkUpRight(int i, int j, List<String> board) {
        if(!isNotoutOfBound(i, j, board)) return false;
        for(int rowInd = i-1, colInd = j+1; isNotoutOfBound(rowInd, colInd, board) ; rowInd--, colInd++) {
            if(board.get(rowInd).charAt(colInd) == 'Q') return false;
        }
        return true;
    }
}


// ----------------------------------------------------------------------------------------------------------------------

// Seeign striver's video
class Solution_Striver {
    
    private List<List<String>> ans = new ArrayList<>();
    private List<String> board;

    public List<List<String>> solveNQueens(int n) {
        if(n<0) return ans; // == 0 means we need 1 board with no rows inside ans

        List<String> board1 = new ArrayList<>();

        String row = "";
        for(int i = 0; i<n; i++) row += '.';

        for(int i=0; i<n; i++) board1.add(row);

        this.board = board1;

        fillBoard(0);

        return ans;
    }

    private void fillBoard(int i) {
        if(i==board.size()) {
            ans.add(new ArrayList<>(board)); // if we simply do ans.add(board); all rows are backtracked and we get ... ... only in answer dots
            return;
        }

        for(int j=0; j<board.get(0).length(); j++) {
            if(check(i,j)) {
                char[] rowArray = board.get(i).toCharArray();
                rowArray[j] = 'Q';
                board.set(i, new String(rowArray));
                fillBoard(i+1);

                rowArray[j] = '.';
                board.set(i, new String(rowArray)); // otherwise we will end up adding more than 1 Q in same row as we are not making row checking in check fucntion also
            }
        }
    }

    private boolean check(int i, int j) {
        int row = i;
        int leftCol = j;
        int rightCol = j;

        while(row>=0) {
            // check top
            if(board.get(row).charAt(j) == 'Q') return false;

            // check left side upper diagonal
            if(leftCol >= 0) {
                if(board.get(row).charAt(leftCol) == 'Q') return false;
                leftCol--;
            }

            // check right side upper diagonal
            if(rightCol < board.get(0).length()) {
                if(board.get(row).charAt(rightCol) == 'Q') return false;
                rightCol++;
            }

            row--;
        }
        return true;
    }
}
// time complexity O(n!) because 1st queen in 1st column can be placed in n columns (n choices)
// 2nd queen n-1 choices (at max but in reality less than it because of diagonal check)
// n* n-1 * n-2... 1. last queen 1 choice.
// time - O(n!)

// space complexity (auxilury) - O(n) for recursion tree height, after row reaches n it is added to answer and returned
// space n^2 for output

// ---------------------------------------------------------------

// Given solution 1
class Solution_given_solution_1 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        if(n<0) return ans; // n==0 means [[]] as ans. 1 board with no rows inside ans
        char[][] board = new char[n][n];
        
        // fill empty board
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(0, board, ans);
        return ans;
    }

    private void backtrack(int r, char[][] board, List<List<String>> ans) {
        if(r==board.length) {
            List<String> board_copy = new ArrayList<>();
            for(char[] row : board) {
                board_copy.add(new String(row)); // or String.valueOf(row);
            }
            ans.add(board_copy);
            return;
        }
        
        // for this row, check every column whichever is safe
        // this is no special not take it scenario for this given r. for a given r we should always fill in atleast 1 column and backtrack
        for(int c=0; c<board[0].length; c++) { // or upto board.length
            if(isSafe(r,c,board)) {
                board[r][c] = 'Q';
                backtrack(r+1, board, ans);
                board[r][c] = '.'; 
            }
            // else check for next column via c++ in next iteration
        }
    }

    private boolean isSafe(int r, int c, char[][] board) {
        // top (no need to check current row)
        for(int i=r-1; i>=0; i--) 
            if(board[i][c]=='Q') return false;
        
        // top left diagonal
        for(int i=r-1, j=c-1; (i>=0 && j>=0); i--, j--)
            if(board[i][j]=='Q') return false;
        
        // top right diagonal
        for(int i=r-1, j=c+1; (i>=0 && j<board[0].length); i--, j++) // here board.length == board[0].length
            if(board[i][j]=='Q') return false;
        
        return true;
    }
}

// ------------------------------------------------------------------------\

// GIVEN SOLUTION 2
// Hashset
// we maintain 3 hashsets and not only one because. one queen's +ve diagonal col+row may be another queen's column. a cell can be attacked by 2 queens at a time. 
// while backtracking we should not accidentally remove the another's queen's column by removing this queen's +ve diagonal
// but at a time no 2 queens can share SAME col. or SAME +ve diag or same -ve diag. if so they will be atacking each other.
// (row+col) and (row-col) can effectively track full of that diagonal. no need to save every cell attacked by the queen
class Solution {
    Set<Integer> col = new HashSet<>();
    Set<Integer> posDiag = new HashSet<>();
    Set<Integer> negDiag = new HashSet<>();
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if(n<0) return ans;
        char[][] board = new char[n][n];

        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                board[i][j] = '.';

        backtrack(0, board);
        return ans;
    }

    private void backtrack(int r, char[][] board) {
        if(r==board.length) {
            List<String> board_copy = new ArrayList<>();
            for(char[] row : board) {
                board_copy.add(new String(row));
            }
            ans.add(board_copy);
            return;
        }

        for(int c=0; c<board[0].length; c++) {
            // WE CAN TRY with c-r also. we just need difference, r-c and c-r both works
            if(col.contains(c) || posDiag.contains(r+c) || negDiag.contains(r-c))
                continue; // go and check next column
            
            // if not in any set, then we can add queen and recurse and backtrack

            col.add(c);
            // If 2 numbers increase at same rate, their difference is maintained. 
            // if one number increase, one decrease at same rate their sum is maintained
            posDiag.add(r+c); // this is the secondary diagonal. here row increases column DECREASES. so that increased row and decreased column MATCH up with R+C
            negDiag.add(r-c); // this is the MAIN PRIMARY diagonal. here row and column both increases. So the difference R-C between row can column is maintained. r+1 - c+1 == r-c

            // make this cell as Queen
            board[r][c] = 'Q';

            backtrack(r+1, board);

            // backtrack 
            col.remove(c);
            posDiag.remove(r+c);
            negDiag.remove(r-c);
            // backtrack this cell state to dot .
            board[r][c] = '.';
        }
    }
}