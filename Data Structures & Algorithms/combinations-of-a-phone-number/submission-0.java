/*

Map.of() is a static factory method introduced in Java 9 that provides a convenient and concise way to create immutable (unmodifiable) maps. 
Key Characteristics
Immutability: The resulting map cannot be modified (no adding, removing, or updating elements).
Capacity Limit: It is overloaded to support between 0 and 10 key-value pairs. For more than 10 entries, you must use Map.ofEntries().
No Nulls: It does not allow null keys or null values; attempting to use them throws a NullPointerException.
Unique Keys: It throws an IllegalArgumentException if duplicate keys are provided during creation.
Type Safety: It provides type safety for both keys and values. 
*/

class Solution {
    Map<Character, List<Character>> map;
    List<String> ans;
    public List<String> letterCombinations(String digits) {
        // return type of Map.of is unmodifiable (upto 10 entries allowed)
        map = Map.of(
            '2', List.of('a', 'b', 'c'),
            '3', List.of('d', 'e', 'f'),
            '4', List.of('g', 'h', 'i'),
            '5', List.of('j', 'k', 'l'),
            '6', List.of('m', 'n', 'o'),
            '7', List.of('p', 'q', 'r', 's'),
            '8', List.of('t', 'u', 'v'),
            '9', List.of('w', 'x', 'y', 'z')
        );

        ans = new ArrayList<>();

        if(digits == null || digits.isEmpty()) return ans;

        addLetterCombinations("", digits, 0); 

        return ans;

    }

    private void addLetterCombinations(String combination, String digits, int i) {
        if(i==digits.length()) {
            ans.add(combination);
            return;
        }

        // fill i th digit and move
        char digit = digits.charAt(i);
        if(digit<'2' || digit>'9') return;
        List<Character> letters = map.getOrDefault(digit, new ArrayList<>());
        for(char letter : letters) {
           addLetterCombinations(combination + letter, digits, i+1);
        }
    }
}
