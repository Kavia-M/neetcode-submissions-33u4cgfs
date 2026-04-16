class Solution_First_my_sol {
    
    private List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<String> board = new ArrayList<>(n); // though this capacity is not strict, it may increase if we add more dynamically

        if(n<=0) return ans;
        
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

class Solution {
    
    private List<List<String>> ans = new ArrayList<>();
    private List<String> board;

    public List<List<String>> solveNQueens(int n) {
        if(n<=0) return ans;

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
