class Solution_one_pass {
    private int getIndex(int i, int j, int n) {
        // both are zero based
        return (i*n) + j;
    }
    /*
        ind = (i*n) + j;
        // i row number
        i = ind/n
        // j column number
        j = ind%n
    */
    private int[] getOriginal(int ind, int n) {
        return new int[]{ind/n, ind%n};
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix.length > 0 ? matrix[0].length : 0;
        // l is start and r is end
        int l = getIndex(0,0, n);
        int r = getIndex(m-1, n-1, n);

        while(l<=r) {
            // c means centre
            int c = l + (r-l) / 2;
            int num = matrix[getOriginal(c,n)[0]][getOriginal(c,n)[1]];

            if(num == target) {
                return true;
            }
            else if(num < target) {
                l = c + 1;
            }
            else {
                r = c - 1;
            }
        }

        return false;
    }  
}


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix.length > 0 ? matrix[0].length : 0;
        // l is start and r is end
        int l_row = 0;
        int r_row = m-1;
        int c_row = -1;
        while(l_row<=r_row) {
            // c means centre
            System.out.println("l_row " + l_row + " r_row " + r_row);
            c_row = l_row + (r_row - l_row) / 2;

            if(target > matrix[c_row][n-1]) {
                l_row = c_row + 1;
            }
            else if(target < matrix[c_row][0]) {
                r_row = c_row - 1;
            }
            else {
                break;
            }
        }
        // we have checked that last conmputing c_row is always within the range
        // so we need to use l r check only
        // System.out.println(c_row);
        // if(c_row < 0 || c_row >= m) 
        //     return false;
        if(l_row > r_row) 
            return false; // if the above loop break inbetween or while condition stopped it
        int l = 0, r = n-1;
        while(l<=r) {
            int c = l + (r-l) / 2;
            System.out.println("C_ROW" + c_row);
            System.out.println(matrix[c_row][c]);
            if(matrix[c_row][c] == target) {
                return true;
            }
            else if(target < matrix[c_row][c]) {
                r = c - 1;
            }
            else {
                l = c + 1;
            }

        }
        return false;
    }  
}
