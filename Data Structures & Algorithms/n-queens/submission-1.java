class Solution {
    
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
