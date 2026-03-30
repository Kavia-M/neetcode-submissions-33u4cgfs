class Solution {
    public boolean isValid(String s) {
        // hash map is undordered 
        // tree map is ordered in java
        Stack<Character> st = new Stack<>();
        Map<Character, Character> closeToOpen = new HashMap<>();
        closeToOpen.put(')', '(');
        closeToOpen.put('}', '{');
        closeToOpen.put(']', '[');

        for(char ch : s.toCharArray()) {
            if(closeToOpen.containsKey(ch)) {
                if(!st.isEmpty() && st.peek() == closeToOpen.get(ch)) {
                    st.pop();
                }
                else return false;

            }
            else if (closeToOpen.containsValue(ch)) {
                st.push(ch);
            }
            else {
                System.out.println("Wrong input");
                return false;
            }

        }
        return st.empty();
    }
}
