// Striver's solution
class Solution_Striver {
    List<List<String>> ans = new ArrayList<>();
    public List<List<String>> partition(String s) {
        addPartition(new ArrayList<>(), s, 0);
        return ans;
    }

    private boolean isPalindrome(String s, int i, int j) {
        for(; i<j; i++, j--) {
            // <= not needed as == means always true, trivial
            if(s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    private void addPartition(List<String> list, String s, int i) {
        if(i == s.length()) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for(int j=i; j<s.length(); j++) {
            if(isPalindrome(s, i, j)) {
                // substring method is end exlusive
                list.add(s.substring(i, j+1));
                addPartition(list, s, j+1);
                list.remove(list.size() - 1);
            }
        }
    }
}

// take it or not take it method
class Solution {
    List<List<String>> ans = new ArrayList<>();
    public List<List<String>> partition(String s) {
        addPartition(new ArrayList<>(), s, 0, 0);
        return ans;
    }

    private boolean isPalindrome(String s, int i, int j) {
        for(; i<j; i++, j--) {
            // <= not needed as == means always true, trivial
            if(s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    private void addPartition(List<String> list, String s, int start, int i) {
        if(i == s.length()) {
            if(start==i)
                ans.add(new ArrayList<>(list));
            return;
        }

        // take it means, add this index to list, keep start as it is. move i to next index
        if(isPalindrome(s, start, i)) {
            // substring method is end exlusive
            list.add(s.substring(start, i+1));
            addPartition(list, s, i+1, i+1);
            list.remove(list.size() - 1);
        }
        addPartition(list, s, start, i+1);

        // not take it in existing list means. this will become new start

      //  addPartition(list, s, i, i+1);
    }
}