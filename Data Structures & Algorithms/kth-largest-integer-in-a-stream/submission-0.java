class KthLargest {
    private int k;
    private List<Integer> list;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.list = Arrays.stream(nums)
                  .boxed()
                  .collect(Collectors.toList());
        list.sort(Comparator.reverseOrder()); //O(nlogn)
    }
    
    public int add(int val) {
        list.add(val);
        Collections.sort(list, Comparator.reverseOrder());
        return list.get(k-1);
    }
}
