class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Long> st = new ArrayDeque<>();

        long num1, num2;
        for(String token : tokens) {
            try {
                st.push((long) Integer.parseInt(token));
            } catch(NumberFormatException e) {
                // from jaav 7 we can pass string in switch being non null
                // we assume 2 numbers inside stack, no empty error
                // num1 is deep inside
                num2 = st.pop();
                num1 = st.pop();
                switch(token) { // break is important
                    case "+" : st.push(num1 + num2); break;
                    case "-" : st.push(num1 - num2); break;
                    case "*" : st.push(num1 * num2); break;
                    case "/" : st.push(num1 / num2); // normally it is integer divisioin
                    // integer division is truncate towards 0. -2.33 => -2
                    // 2.33 => 2
                }
            } 

        }
        // Long object cannot be dorectly converted into int primitive type
        return (int)(long)st.pop();
        

    }
}
