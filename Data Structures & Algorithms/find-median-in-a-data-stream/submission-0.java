class MedianFinder {
    private List<Integer> arr;
    public MedianFinder() {
        arr = new ArrayList<>();
    }
    
    public void addNum(int num) {
        arr.add(num);
    }
    
    public double findMedian() {
        int n = arr.size();
        if(n%2==0) {
            return (arr.get(n/2) + arr.get(n/2 - 1) ) / (double) 2;
        }
        return arr.get(n/2);
    }
}
