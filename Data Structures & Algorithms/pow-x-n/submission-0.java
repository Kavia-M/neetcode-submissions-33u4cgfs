// BRUTE FORCE USING ITERATION
class Solution {
    public double myPow(double x, int n) {
        double ans = 1; // auto converts to double
        /*
        Java this is widening conversion:
        byte → short → char (via int) → int → long → float → double
        automatically happen
        */
        
        // for -ve powers. x power -n ==> (1/x) power +n 
        if(n<0) {
            x = 1/x;
            n = -1*n; // or n = -n;
        }
        // 0 based means <n or 1 based means <=n
        for(int i=1; i<=n; i++) {
            ans *= x;
        }
        return ans;
    }
}
