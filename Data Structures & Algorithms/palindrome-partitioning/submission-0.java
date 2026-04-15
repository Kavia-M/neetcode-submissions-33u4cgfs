// Striver's solution
class Solution {
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
