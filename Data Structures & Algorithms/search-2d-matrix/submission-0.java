class Solution {
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
