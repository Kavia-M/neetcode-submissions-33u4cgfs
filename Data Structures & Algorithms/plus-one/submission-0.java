class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1; // At starting we are going to add 1
        // one element in digits can be 0 to 9 only
        // no need to worry about overflow

        for(int i = digits.length-1; i>=0; i--) {
            int ans = digits[i] + carry;
            digits[i] = ans % 10;
            carry = ans / 10;
        }

        if(carry==0) {
            return digits;
        }
        
        int[] new_digits = new int[digits.length + 1];
        new_digits[0] = carry;

        // Copy existing elements to the new array starting from index 1
        System.arraycopy(digits, 0, new_digits, 1, digits.length);

        return new_digits;
    }
}
