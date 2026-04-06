/*
 * MATH POWER RULES (LAWS OF EXPONENTS)
 * ------------------------------------
 * 1. Product Rule:          a^m * a^n = a^(m+n)
 * 2. Quotient Rule:         a^m / a^n = a^(m-n)
 * 3. Power of a Power:      (a^m)^n = a^(mn)
 * 4. Power of a Product:    (ab)^n = a^n * b^n
 * 5. Power of a Quotient:   (a/b)^n = a^n / b^n
 * 6. Zero Exponent:         a^0 = 1  (where a != 0)
 * 7. Negative Exponent:     a^(-n) = 1 / a^n
 * 8. Fractional Exponent:   a^(m/n) = n-th root of (a^m)
 */

// BRUTE FORCE USING ITERATION
class Solution_BRUTE_FORCE_ITERATION {
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


// BRUTE FORCE RECURRSION
class Solution_BRUTE_FORCE_RECURSION {
    public double myPow(double x, int n) {
        // base case
        if(n==0) return 1;

        // for -ve powers
        if(n<0) {
            x = 1/x;
            n = -n;
        }

        return x * myPow(x, n-1);
    }
}

//  Binary Exponentiation - ITERATIVE
// maths: (x^n1)^n2 = x^(n1*n2)
// so we make use of squares
// if n is odd means we make ans = x * new_expression, so we take out one x, so new_expression becomes even
// if n is even or made even by above step, ans = (x^2)^(n/2) .. this exponentially reduces iteration or recusion giving log n (base 2) time complexity
class Solution_EXPONENTIATION_ITERATIVE {
    public double myPow(double x, int n) {
        double ans = 1; // ans is DOUBLE , very IMPORTANT
        if(n<0) {
            x=1/x;
            n=-n;
        }

        // n==0 means we break and ans is already 1
        while(n>0) {
            if(n%2==1) {
                // odd means 
                // ans = x * new_expression... so multiply ans with x.
                ans *= x; // new expression will eventually multiplied with answer when n boils down to 1 after consecutive divide by 2
                // n--; is not needed. divide by 2 and integer conversion takes care of it
            }
            // DO NOT DO ELSE, the below is for all

            x = x*x; // x is squared and built big. eventually n will be 1 and this big x will be multiplied with ans
            n /= 2; // if n is odd means also we get n/2 correctly, the last extra odd becomes point 5 and when converted to int it is ignored. eg. 7/2 = 3 which is (7-1)/2
        }
        return ans;
    }
}

//  Binary Exponentiation - RECURSIVE
class Solution_EXPONENTIATION_RECURSIVE {
    public double myPow(double x, int n) {
        // base case
        if(n==0) return 1;

        // -ve powers
        if(n<0) {
            x = 1/x;
            n = -n;
        }

        if(n%2==1) {
            // ODD
            return x * myPow(x*x, n/2);
        }

        return myPow(x*x, n/2);
    }
}


/*
Minimum Value: Integer.MIN_VALUE is -2,147,483,648

Maximum Value: Integer.MAX_VALUE is 2,147,483,647 

if n is Integer.MIN_VALUE, the -1 * -2,147,483,648 = +2,147,483,648 cannot be stored in integer
so convert n into long initially and use it
*/
class Solution {
    public double myPow(double x, int n) {
        double ans = 1; // ans is DOUBLE , very IMPORTANT
        long long_n = n;
        if(long_n<0) {
            x=1/x;
            long_n = -long_n;
        }

        // n==0 means we break and ans is already 1
        while(long_n>0) {
            if(long_n%2==1) {
                ans *= x;
            }
            // DO NOT DO ELSE, the below is for all

            x = x*x;
            long_n /= 2;
        }
        return ans;
    }
}
